package jack.org.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;
import java.util.List;

import jack.org.project.model.Book;
import jack.org.project.model.Comment;
import jack.org.project.model.User;
import jack.org.project.service.SecurityService;
import jack.org.project.service.UserService;
import jack.org.project.validator.UserValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;


	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, Principal p) {

		model.addAttribute("currentUser", userService.findByUsername(p.getName()));

		return "home";
	}

}