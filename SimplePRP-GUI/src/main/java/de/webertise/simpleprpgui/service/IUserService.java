package de.webertise.simpleprpgui.service;

import java.util.List;

import de.webertise.simpleprpgui.exception.UserNotFound;
import de.webertise.simpleprpgui.model.User;

public interface IUserService {

    public User create(User user);

    public User delete(int id) throws UserNotFound;

    public List<User> findAll();

    public User update(User user) throws UserNotFound;

    public User findById(int id);

    public User findByUsername(String userName);

}
