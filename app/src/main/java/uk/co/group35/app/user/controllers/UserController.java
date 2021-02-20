package uk.co.group35.app.user.controllers;

import java.util.List;
import uk.co.group35.app.DBModels.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

	@Autowired
	private UserService service;

    @GetMapping("/list")
	public List<String> getUsers() {
		return service.getAllUsers();
	}  

	@GetMapping("/login")
	public ResponseEntity<Integer> checkLoginInformation(@RequestBody User user) {
		Integer response = service.checkLoginInformation(user);
		// Required field(s) missing in GET request
		if (response == -1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			return ResponseEntity.ok(response);
		}

	}
    
}