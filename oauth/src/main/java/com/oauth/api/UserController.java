package com.oauth.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.models.UserTO;
import com.oauth.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	 @RequestMapping("/")
	    public Principal resource(Principal principal) {
	        return principal;
	    }

	
	@RequestMapping(path="/user/create")
	public UserTO createUser(@RequestBody UserTO userTo) {
		
		return userService.saveUser(userTo);
	}
	
	@RequestMapping(path="/user/get",method=RequestMethod.GET)
	public UserTO getUser(@RequestParam long id) {
		
		return userService.getUserById(id);
	}
}
