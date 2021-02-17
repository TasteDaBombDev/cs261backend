package uk.co.group35.app.user.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

	@Autowired
	private UserRepository repo;

    @GetMapping("/all")
	public ArrayList<User> getUsers() {
		return repo.findall();
	}  
	
	@PostMapping
	public void addNewUser(@RequestBody User user) {
		repo.save(user);
	}
    
}