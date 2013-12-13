package de.webertise.simpleprpgui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.webertise.simpleprpgui.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
