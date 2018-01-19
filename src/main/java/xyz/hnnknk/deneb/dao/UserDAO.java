package xyz.hnnknk.deneb.dao;

import xyz.hnnknk.deneb.model.User;

import java.util.List;

public interface UserDAO {
    void save(User user);
    List<User> list();
}
