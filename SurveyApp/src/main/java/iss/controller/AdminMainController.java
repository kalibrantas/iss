package iss.controller;

import iss.dao.postgre.PgQuestionnaireDao;
import iss.dao.postgre.PgRespondentDao;
import iss.dao.postgre.PgUserDao;
import iss.message.ResponseMessage;
import iss.message.User;

import java.security.Principal;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class AdminMainController {
	private String TEMPLATE_PATH = "admin";
	PgUserDao userDao;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		userDao = new PgUserDao(dataSource);

	}

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
	
	
	
	
	@RequestMapping("/templates/{template1}/{template2}")
	public String getTemplates(@PathVariable String template1, @PathVariable String template2) {
		return TEMPLATE_PATH + "/templates/" + template1+"/"+template2;
	}
	
	@RequestMapping("/admin/**")
	public String home() {		
		return "admin/index";
	}
	
	@RequestMapping(value="/user/getall")
	public @ResponseBody ArrayList<User> getAllUser(){
		return userDao.getAllUser();
	}
	
	@RequestMapping(value="/user/save", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage saveUser(@RequestBody User user){
		System.out.println(user.getEmail());
		System.out.println(user.getFirst_name());
		System.out.println(user.getLast_name());
		System.out.println(user.getPassword());
		System.out.println(user.getRole());
		userDao.saveUser(user);
		return new ResponseMessage("success", "berhasil");
	}
	
}
