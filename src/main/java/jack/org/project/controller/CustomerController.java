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

import jack.org.project.model.User;
import jack.org.project.service.SecurityService;
import jack.org.project.service.UserService;
import jack.org.project.validator.UserValidator;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;


	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String home(Model model, Principal p) {
		User currentUser = userService.findByUsername(p.getName());

		model.addAttribute("currentUser", currentUser);

		return "homepage";
	}


	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String login(Model model, String error, Principal principal) {
		String name = principal.getName();
		User currentUser = userService.findByUsername(principal.getName());


		model.addAttribute("userForm", userService.findByUsername(name));
		model.addAttribute("currentUser", userService.findByUsername(name));

		return "account";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,
			Principal principal) {
		String name = principal.getName();
		userService.update(userForm, userService.findByUsername(name));

		return "redirect:/customer/account";
	}

}
