package de.webertise.simpleprpgui.security.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import de.webertise.simpleprpgui.model.User;

@Repository("userDao")
public class UserDao implements IUserDao {

    @PersistenceContext(unitName = "SimplePRPguiPU")
    private EntityManager em;

    public User loadUserByUsername(final String login) {
        Query query = this.em.createQuery("SELECT u FROM User u WHERE u.username = :username");
        query.setParameter("username", login);
        @SuppressWarnings("unchecked")
        List<User> users = query.getResultList();
        if (users != null && users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

}