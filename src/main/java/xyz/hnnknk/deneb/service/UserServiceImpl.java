package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.UserDAO;
import xyz.hnnknk.deneb.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    @Transactional
    public void saveUser(User user) {
        userDAO.save(user);
    }


}
