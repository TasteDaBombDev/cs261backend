package uk.co.group35.app.user.controllers;

import uk.co.group35.app.DBModels.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}