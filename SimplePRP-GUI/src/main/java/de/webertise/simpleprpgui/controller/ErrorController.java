package de.webertise.simpleprpgui.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/error/**")
public class ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping(value = "access-denied", method = RequestMethod.GET)
    public String accessDenied(Locale locale, Model model, HttpSession session) {
        return "redirect:/internal/home?accessDenied=true";
    }

    @RequestMapping(value = "page-not-found", method = RequestMethod.GET)
    public String pageNotFound(Locale locale, Model model, HttpSession session) {
        return "redirect:/internal/home?pageNotFound=true";
    }

}
