package xyz.hnnknk.deneb.service;

import xyz.hnnknk.deneb.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> list();
}
