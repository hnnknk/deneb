package xyz.hnnknk.deneb.dao;

import xyz.hnnknk.deneb.model.Backups;

import java.util.List;

public interface BackupsDAO {

    void save(Backups backups);
    void update(Backups backups);
    void delete(long id);

    Backups findById(long id);

    List<Backups> listAllBackupses();
}
