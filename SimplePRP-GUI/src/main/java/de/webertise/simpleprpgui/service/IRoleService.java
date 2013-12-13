package de.webertise.simpleprpgui.service;

import java.util.List;

import de.webertise.simpleprpgui.exception.RoleNotFound;
import de.webertise.simpleprpgui.model.Role;

public interface IRoleService {

    public Role create(Role role);

    public Role delete(int id) throws RoleNotFound;

    public List<Role> findAll();

    public Role update(Role role) throws RoleNotFound;

    public Role findById(int id);

}
