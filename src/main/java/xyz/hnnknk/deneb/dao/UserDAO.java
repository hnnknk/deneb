package xyz.hnnknk.deneb.dao;

import xyz.hnnknk.deneb.model.User;

import java.util.List;

public interface UserDAO {

    void save(User user);
    void update(User user);
    void delete(long id);
    void deleteAllUsers();
    User findById(long id);
    User findByName(String name);
    List<User> listAllUsers();
    boolean isUserExist(User user);
}
