package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnitDAO;
import xyz.hnnknk.deneb.model.Processor;

import java.util.List;

@Service
public class ProcessorServiceImpl implements ProcessorService{

    @Autowired
    SystemUnitDAO processorDAOImpl;

    @Transactional
    @Override
    public void save(Processor processor) {
        processorDAOImpl.save(processor);
    }

    @Transactional
    @Override
    public void update(Processor processor) {
        processorDAOImpl.update(processor);
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
    public List<Processor> listAllProcessors() {
        return processorDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isProcessorExists(Processor processor) {
        return processorDAOImpl.isExists(processor);
    }
}
