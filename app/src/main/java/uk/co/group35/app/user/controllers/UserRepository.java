package uk.co.group35.app.user.controllers;

import uk.co.group35.app.DBModels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Returns User with username @username and password @password
     */
    @Query(value = "SELECT * FROM Users u WHERE u.username = :username and u.password = :password",
        nativeQuery = true)
    User checkLoginInformation(
        @Param("username") String username, 
        @Param("password") String password); 
}