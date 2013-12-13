package de.webertise.simpleprpgui.security.dao;

import de.webertise.simpleprpgui.model.User;

public interface IUserDao {

    public User loadUserByUsername(final String username);

}
