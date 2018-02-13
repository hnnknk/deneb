package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.PeripheralDAO;
import xyz.hnnknk.deneb.model.Mouse;

import java.util.List;

@Service
public class MouseServiceImpl implements MouseService {

    @Autowired
    PeripheralDAO mouseDAOImpl;

    @Transactional
    @Override
    public void save(Mouse mouse) {
        mouseDAOImpl.save(mouse);
    }

    @Transactional
    @Override
    public void update(Mouse mouse) {
        mouseDAOImpl.update(mouse);
    }

    @Transactional
    @Override
    public void delete(long id) {
        mouseDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public Mouse findById(long id) {
        return (Mouse) mouseDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<Mouse> listAllMouses() {
        return mouseDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isMouseExists(Mouse mouse) {
        return mouseDAOImpl.isExists(mouse);
    }
}
