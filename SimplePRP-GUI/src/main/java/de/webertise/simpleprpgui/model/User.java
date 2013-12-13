package de.webertise.simpleprpgui.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import de.webertise.simpleprpgui.model.general.AbstractEntityObject;

@Entity
@Table(name = "PRP_USER", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }), @UniqueConstraint(columnNames = { "email" }) })
public class User extends AbstractEntityObject implements UserDetails {

    private static final long serialVersionUID = 1L;

    // *******************************************************
    // * Constants
    // *******************************************************
    public final static String RELATION_TYPE_MEMBER = "member";
    public final static String RELATION_TYPE_PRJMGR = "prjmgr";
    public final static String RELATION_TYPE_ADMIN = "admin";

    // *******************************************************
    // * Specific Entity Properties
    // *******************************************************
    @Column(name = "USERNAME", nullable = false, length = 20)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    private String firstName;

    @Column(name = "LASTNAME", length = 100)
    private String lastName;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "FAILED_LOGIN_ATTEMPTS")
    private int failedLoginAttempts;

    @Column(nullable = false, name = "ACCOUNT_NON_EXPIRED")
    private boolean accountNonExpired = true;

    @Column(nullable = false, name = "ACCOUNT_NON_LOCKED")
    private boolean accountNonLocked = true;

    @Column(nullable = false, name = "CREDENTIALS_NON_EXPIRED")
    private boolean credentialsNonExpired = true;

    @Column(nullable = false, name = "ENABLED")
    private boolean enabled = true;

    @Column(nullable = false, name = "SALT", length = 100)
    private String salt;

    // *******************************************************
    // * Relationships
    // *******************************************************
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PRP_USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
    private Collection<Role> authorities;

    // *******************************************************
    // * Transient fields
    // *******************************************************
    @Transient
    private String plainPassword;

    // *******************************************************
    // * Constructors
    // *******************************************************
    public User() {
        this.setChangedAt(new Date());
        this.setCreatedAt(new Date());
        this.setChangedBy("anonymous");
        this.setCreatedBy("anonymous");
        this.setAccountNonExpired(true);
        this.setAccountNonLocked(true);
        this.setCredentialsNonExpired(true);
        this.setEnabled(true);
        this.setPassword("");
    }

    // *******************************************************
    // * Getter & Setter
    // *******************************************************

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the failedLoginAttempts
     */
    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    /**
     * @param failedLoginAttempts
     *            the failedLoginAttempts to set
     */
    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    /**
     * @return the accountNonExpired
     */
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /**
     * @param accountNonExpired
     *            the accountNonExpired to set
     */
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    /**
     * @return the accountNonLocked
     */
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * @param accountNonLocked
     *            the accountNonLocked to set
     */
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    /**
     * @return the credentialsNonExpired
     */
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    /**
     * @param credentialsNonExpired
     *            the credentialsNonExpired to set
     */
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     *            the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return the plainPassword
     */
    public String getPlainPassword() {
        return plainPassword;
    }

    /**
     * @param plainPassword
     *            the plainPassword to set
     */
    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        for (Role role : this.authorities) {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }
        return roles;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    // *******************************************************
    // * General Methods
    // *******************************************************

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.accountNonExpired ? 1231 : 1237);
        result = prime * result + (this.accountNonLocked ? 1231 : 1237);
        result = prime * result + (this.credentialsNonExpired ? 1231 : 1237);
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + (this.enabled ? 1231 : 1237);
        result = prime * result + this.failedLoginAttempts;
        result = prime * result + ((this.firstName == null) ? 0 : this.firstName.hashCode());
        result = prime * result + ((this.lastName == null) ? 0 : this.lastName.hashCode());
        result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (this.accountNonExpired != other.accountNonExpired)
            return false;
        if (this.accountNonLocked != other.accountNonLocked)
            return false;
        if (this.credentialsNonExpired != other.credentialsNonExpired)
            return false;
        if (this.email == null) {
            if (other.email != null)
                return false;
        } else if (!this.email.equals(other.email))
            return false;
        if (this.enabled != other.enabled)
            return false;
        if (this.failedLoginAttempts != other.failedLoginAttempts)
            return false;
        if (this.firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!this.firstName.equals(other.firstName))
            return false;
        if (this.lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!this.lastName.equals(other.lastName))
            return false;
        if (this.username == null) {
            if (other.username != null)
                return false;
        } else if (!this.username.equals(other.username))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [username=" + this.username + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + ", failedLoginAttempts=" + this.failedLoginAttempts
                + ", accountNonExpired=" + this.accountNonExpired + ", accountNonLocked=" + this.accountNonLocked + ", credentialsNonExpired=" + this.credentialsNonExpired + ", enabled="
                + this.enabled + ", toString()=" + super.toString() + "]";
    }

}
