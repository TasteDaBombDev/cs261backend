package uk.co.group35.app.utils.PSQL.external.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.group35.app.DBModels.user.User;
import uk.co.group35.app.utils.PSQL.external.UserRepository;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository Users_t;

    /**
     * Returns list of usernames of all users in the DB
     */
    public List<String> getAllUsers() {
        return Users_t.findAllUsers();
        
        //return Users_t.findAll().stream().map(User::getUsername).collect(Collectors.toList());
    }

    /**
     * Processes a login attempt
     * @param user Login attempt
     * @return -1 for failed request, 0 for successful login, 1 for unsuccessful login
     */
    public Integer checkLoginInformation(User user) {
        String username = user.getUsername(), password = user.getPassword();
        if (username == null || password == null) {
            return -1;
        } else if (Users_t.checkLoginInformation(username, password) != null) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Register a new user
     * @param username username of new user
     * @param password password of new user
     * @return -1 for failed request, 0 if username already exists, 1 for successful registration
     */
    public Integer registerUser(User user) {
        String username = user.getUsername(), password = user.getPassword();
        if (username == null || password == null) {
            return -1;
        }
        User newUser = new User(username, password);
        try {
            Users_t.saveAndFlush(newUser);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        return 1;

    }
    

    
}