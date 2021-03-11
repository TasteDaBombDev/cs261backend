package uk.co.group35.app.RESTcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.group35.app.DBModels.user.User;
import uk.co.group35.app.utils.PSQL.external.service.UserService;

import java.util.List;

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

	@PostMapping("/register") 
	public ResponseEntity<String> registerUser(@RequestBody User user) {
			Integer response = service.registerUser(user);
			// Required field(s) missing in POST request
			if (response == -1) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			} else if (response == 0) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("User with username '" + user.getUsername() + "' already exists...");
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(null);
			}

			
			
		}
    
}