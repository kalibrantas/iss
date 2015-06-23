package iss.controller;

import java.security.Principal;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("/user")
	public @ResponseBody Principal user(Principal user) {
		return user;
	}

	@RequestMapping("/login")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/home";
		} else {
			return "login";
		}
	}
	
	@RequestMapping("/home")
	public String home() {		
		return "home/index";
	}
	
}
