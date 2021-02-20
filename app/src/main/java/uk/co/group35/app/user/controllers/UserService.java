package uk.co.group35.app.user.controllers;

import java.util.List;
import uk.co.group35.app.DBModels.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository Users_t;

    /**
     * Returns list of usernames of all users in the DB
     */
    public List<String> getAllUsers() {
        return Users_t.findAll().stream().map(User::getUsername).collect(Collectors.toList());
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
    

    
}