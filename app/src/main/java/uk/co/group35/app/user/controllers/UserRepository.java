package uk.co.group35.app.user.controllers;

import uk.co.group35.app.DBModels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * @return List of usernames of all Users in the DB 
     */
    @Query(value = "SELECT username FROM Users u",
        nativeQuery = true)
    List<String> findAllUsers();

    /**
     * @return User's username with username @username and password @password
     */
    @Query(value = "SELECT username FROM Users u WHERE u.username = :username and u.password = :password",
        nativeQuery = true)
    String checkLoginInformation(
        @Param("username") String username, 
        @Param("password") String password);
}