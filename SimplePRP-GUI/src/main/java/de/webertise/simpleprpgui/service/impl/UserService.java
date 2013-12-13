package de.webertise.simpleprpgui.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.webertise.simpleprpgui.exception.UserNotFound;
import de.webertise.simpleprpgui.model.User;
import de.webertise.simpleprpgui.repository.UserRepository;
import de.webertise.simpleprpgui.service.IUserService;

@Service("userService")
public class UserService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user) {
        User createdUser = user;
        return userRepository.save(createdUser);
    }

    @Override
    @Transactional
    public User findById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = UserNotFound.class)
    public User delete(int id) throws UserNotFound {
        User deletedShop = userRepository.findOne(id);

        if (deletedShop == null)
            throw new UserNotFound();

        userRepository.delete(deletedShop);
        return deletedShop;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User update(User user) throws UserNotFound {
        // TODO Auto-generated method stub
        return null;
    }

}
