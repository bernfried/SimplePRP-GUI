package de.webertise.simpleprpgui.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.webertise.simpleprpgui.exception.UserNotFound;
import de.webertise.simpleprpgui.model.User;
import de.webertise.simpleprpgui.service.IUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/internal/user/**")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String profile(Locale locale, Model model, HttpSession session) {

        // get user based on session principal name
        String username = (String) session.getAttribute("principal");
        logger.debug("UserController.profile - Username: {}", username);
        User user = userService.findByUsername(username);

        // add to model
        model.addAttribute("user", user);

        return "profile";
    }

    @RequestMapping(value = "profile/{id}", method = RequestMethod.POST)
    public String update(@PathVariable long id, @Validated User user, BindingResult result) {
        logger.debug("Reached UserController.update");

        if (result.hasErrors()) {
            return "profile?validated=false";
        } else {
            try {
                userService.update(user);
            } catch (UserNotFound e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return "profile";
    }
}
