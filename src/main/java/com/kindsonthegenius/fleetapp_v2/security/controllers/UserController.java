package com.kindsonthegenius.fleetapp_v2.security.controllers;


import com.kindsonthegenius.fleetapp_v2.security.models.User;
import com.kindsonthegenius.fleetapp_v2.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

	@Autowired
    private UserService userService;
	
	@GetMapping("/security/users")
	public String getUser() {
		return "/security/users";
	}
	
	
	//Modified method to Add a new user User
	@PostMapping(value="users/addNew")
	public RedirectView addNew(User user, RedirectAttributes redir) {
		
		userService.save(user);	
		
		RedirectView redirectView= new RedirectView("/login",true);
		
	    redir.addFlashAttribute("message",	"You successfully registered! You can now login");
	        
	    return redirectView;				
	}
	
}
