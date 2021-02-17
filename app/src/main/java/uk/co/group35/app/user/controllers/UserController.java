package uk.co.group35.app.user.controllers;

import java.util.List;
import uk.co.group35.app.DBModels.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

	@Autowired
	private UserRepository repo;

    @GetMapping("/all")
	public List<User> getUsers() {
		return this.repo.findAll();
	}  
	
	@PostMapping
	public void addNewUser(@RequestBody User user) {
		this.repo.save(user);
	}
    
}