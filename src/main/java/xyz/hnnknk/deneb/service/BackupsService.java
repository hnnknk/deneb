package xyz.hnnknk.deneb.service;

import xyz.hnnknk.deneb.model.Backups;

import java.util.List;

public interface BackupsService {

    void save(Backups backups);
    void update(Backups backups);
    void delete(long id);

    Backups findById(long id);

    List<Backups> listAllBackupses();
}
