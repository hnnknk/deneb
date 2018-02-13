package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.model.Processor;
import xyz.hnnknk.deneb.model.SystemUnit;

import java.util.List;

@Service
public class ProcessorServiceImpl implements SystemUnitService {

    @Autowired
    SystemUnitDAO processorDAOImpl;

    @Transactional
    @Override
    public void save(SystemUnit systemUnit) {
        processorDAOImpl.save(systemUnit);
    }

    @Transactional
    @Override
    public void update(SystemUnit systemUnit) {
        processorDAOImpl.update(systemUnit);
    }

    @Transactional
    @Override
    public void delete(long id) {
        processorDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public Processor findById(long id) {
        return (Processor) processorDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<Processor> listAll() {
        return processorDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isExists(SystemUnit systemUnit) {

        boolean result = false;

        for(Processor proc : listAll()) {
            if (proc.getId().equals(systemUnit.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
