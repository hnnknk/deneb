package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.BackupsDAO;
import xyz.hnnknk.deneb.model.Backups;

import java.util.List;

public class BackupsServiceImpl implements BackupsService {

    @Autowired
    BackupsDAO backupsDAO;

    @Transactional
    @Override
    public void save(Backups backups) {
        backupsDAO.save(backups);
    }

    @Transactional
    @Override
    public void update(Backups backups) {
        backupsDAO.update(backups);
    }

    @Transactional
    @Override
    public void delete(long id) {
        backupsDAO.delete(id);
    }

    @Transactional
    @Override
    public Backups findById(long id) {
        return backupsDAO.findById(id);
    }

    @Transactional
    @Override
    public List<Backups> listAllBackupses() {
        return backupsDAO.listAllBackupses();
    }

}

