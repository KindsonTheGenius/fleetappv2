package com.kindsonthegenius.fleetappv2.security.controllers;


import com.kindsonthegenius.fleetappv2.exception.UserAlreadyExistException;
import com.kindsonthegenius.fleetappv2.security.models.User;
import com.kindsonthegenius.fleetappv2.security.services.RoleService;
import com.kindsonthegenius.fleetappv2.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

@Controller
public class UserController {

	@Autowired
    private UserService userService;

	@Autowired
	private RoleService roleService;

	@Resource
	private MessageSource messageSource;
	
	@GetMapping("/security/users")
	public String getUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "/security/users";
	}

	//The op parameter is either Edit or Details
	@GetMapping("/security/user/{op}/{id}")
	public String editEmployee(@PathVariable Integer id, @PathVariable String op, Model model){
		User user = userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("userRoles", roleService.getUserRoles(user));
		model.addAttribute("userNotRoles", roleService.getUserNotRoles(user));
		return "/security/user"+ op; //returns employeeEdit or employeeDetails
	}

	//Modified method to Add a new user User
	@PostMapping(value="usersAddNew")
	public String addNew(User user, RedirectAttributes redir, Model model) throws UserAlreadyExistException {
		userService.register(user);
		//RedirectView redirectView= new RedirectView("/login",true);
	    //redir.addFlashAttribute("registrationSuccess", "You successfully registered! You can now login");
	    model.addAttribute("registrationSuccess",
				messageSource.getMessage("user.registration.verification.email.msg",null, LocaleContextHolder.getLocale()));
	    return "security/registrationSucessful";
	}
	
}
