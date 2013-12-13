package de.webertise.simpleprpgui.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import de.webertise.simpleprpgui.model.User;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ShaPasswordEncoder shaPwdEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        logger.debug("Username/Password: {}/{}", username, password);

        User user = (User) userDetailsService.loadUserByUsername(username);

        // check error - User not found
        if (user == null) {
            logger.debug("User not found!");
            throw new BadCredentialsException("login.error.badCredentials");
        } else {
            // create salt and password for inserted or updated user
            ShaPasswordEncoder pwdEnc = new ShaPasswordEncoder();
            String shaPwd = pwdEnc.encodePassword(password, user.getSalt());

            // check error - password wrong
            logger.debug("Encoded Pwd: {}", shaPwd);
            if (!shaPwd.equals(user.getPassword())) {
                logger.debug("Password wrong!");
                throw new BadCredentialsException("login.error.badCredentials");
            }

            // check expiration status
            if (!user.isAccountNonExpired()) {
                logger.debug("Account expired!");
                throw new AccountExpiredException("login.error.expired");
            }

            // check lock status
            if (!user.isAccountNonLocked()) {
                logger.debug("Account locked!");
                throw new LockedException("login.error.locked");
            }

            // check lock status
            if (!user.isCredentialsNonExpired()) {
                logger.debug("Credentials expired!");
                throw new CredentialsExpiredException("login.error.credentialsExpired");
            }

            // check enabled
            if (!user.isEnabled()) {
                logger.debug("Account disabled!");
                throw new DisabledException("login.error.disabled");
            }
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, authorities);
        logger.debug("Is authenticated: {}", token.isAuthenticated());

        if (!token.isAuthenticated()) {
            throw new AuthenticationServiceException("login.error.general");
        }

        return token;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

    public ShaPasswordEncoder getShaPwdEncoder() {
        return shaPwdEncoder;
    }

    public void setShaPwdEncoder(ShaPasswordEncoder shaPwdEncoder) {
        this.shaPwdEncoder = shaPwdEncoder;
    }

    /**
     * @return the userService
     */
    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    /**
     * @param userService
     *            the userService to set
     */
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}