package de.webertise.simpleprpgui.controller;

import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "/internal/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session, Principal principal) {
		logger.debug("Reached AuthController.home");
		if (principal!=null) {
			session.setAttribute("principal", principal.getName());
			logger.debug("Reached AuthController.home - Principal: " + principal.getName());
			return "home";
		} else {
			logger.debug("Reached AuthController.home - No principal ");
			return "login";
		}
	}

    @RequestMapping(value = "/public/login", method = RequestMethod.GET)
    public String login(Locale locale, Model model, WebRequest wReq) {
		logger.debug("Reached AuthController.login");

		Exception lastException = (Exception) wReq.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, WebRequest.SCOPE_SESSION);
		if (lastException != null) {
			if (lastException instanceof SessionAuthenticationException) {
				model.addAttribute("loginLastError", "login.error.maxSessionsExceeded");
			} else {
				model.addAttribute("loginLastError", "login.error.general");
			}
		} else {
			model.addAttribute("loginLastError", "login.error.general");
		}
		
    	return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Locale locale, Model model, HttpSession session) {
		logger.debug("Reached AuthController.logout");
    	session.invalidate();
        return "redirect:/internal/home";
    }
 
}
