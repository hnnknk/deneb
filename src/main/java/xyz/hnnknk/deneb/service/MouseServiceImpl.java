package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.MouseDAO;
import xyz.hnnknk.deneb.model.Mouse;

import java.util.List;

@Service
public class MouseServiceImpl implements MouseService {

    @Autowired
    MouseDAO mouseDAO;

    @Transactional
    @Override
    public void save(Mouse mouse) {
        mouseDAO.save(mouse);
    }

    @Transactional
    @Override
    public void update(Mouse mouse) {
        mouseDAO.update(mouse);
    }

    @Transactional
    @Override
    public void delete(long id) {
        mouseDAO.delete(id);
    }

    @Transactional
    @Override
    public Mouse findById(long id) {
        return mouseDAO.findById(id);
    }

    @Transactional
    @Override
    public List<Mouse> listAllMouses() {
        return mouseDAO.listAllMouses();
    }

    @Transactional
    @Override
    public boolean isMouseExists(Mouse mouse) {
        return mouseDAO.isMouseExists(mouse);
    }
}
