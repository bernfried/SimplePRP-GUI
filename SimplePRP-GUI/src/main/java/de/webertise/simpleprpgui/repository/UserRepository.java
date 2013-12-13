package de.webertise.simpleprpgui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.webertise.simpleprpgui.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
