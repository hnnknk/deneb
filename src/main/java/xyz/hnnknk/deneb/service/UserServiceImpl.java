package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.UserDAO;
import xyz.hnnknk.deneb.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public List<User> findAllUsers() {
        return userDAO.listAllUsers();
    }

    @Transactional
    public User findById(long id) {
        return userDAO.findById(id);
    }

    @Transactional
    public User findByName(String name) {
        return userDAO.findByName(name);
    }

    @Transactional
    public void saveUser(User user) {
        userDAO.save(user);
    }

    @Transactional
    public void updateUser(User user) {
        userDAO.update(user);
    }

    @Transactional
    public void deleteUserById(long id) {
        userDAO.delete(id);
    }

    @Transactional
    public boolean isUserExist(User user) {
        return userDAO.isUserExist(user);
    }

    @Transactional
    public void deleteAllUsers(){
        userDAO.deleteAllUsers();
    }

}
