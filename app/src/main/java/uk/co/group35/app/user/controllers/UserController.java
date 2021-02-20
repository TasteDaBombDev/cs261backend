package uk.co.group35.app.user.controllers;

import java.util.List;
import uk.co.group35.app.DBModels.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

	@Autowired
	private UserService service;

	//private UserService service = new UserService(User_t);

    @GetMapping("/list")
	public List<String> getUsers() {
		return service.getAllUsers();
	}  
    
}