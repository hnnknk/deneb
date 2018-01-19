package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void delete(long id) {
        User y = findById(id);
        if(y != null) {
            sessionFactory.getCurrentSession().delete(y);
        }
    }

    @Override
    public void deleteAllUsers() {
        for(User u : listAllUsers() ) {
            sessionFactory.getCurrentSession().delete(u);
        }
    }

    @Override
    public User findById(long id) {
        for(User us : listAllUsers()) {
            if (id == us.getId()) {
                return us;
            }
        }

        return null;
    }

    @Override
    public User findByName(String name) {
        for(User use : listAllUsers()) {
            if (name.equals(use.getName())) {
                return use;
            }
        }

        return null;
    }

    @Override
    public List<User> listAllUsers() {
        @SuppressWarnings("unchecked")
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public boolean isUserExist(User user) {
        for(User a : listAllUsers()) {
            if (a.equals(user)) {
                return true;
            }
        }
        return false;
    }
}
