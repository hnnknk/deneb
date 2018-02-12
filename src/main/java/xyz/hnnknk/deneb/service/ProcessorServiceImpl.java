package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.ProcessorDAO;
import xyz.hnnknk.deneb.model.Processor;

import java.util.List;

public class ProcessorServiceImpl implements ProcessorService{

    @Autowired
    ProcessorDAO processorDAO;

    @Transactional
    @Override
    public void save(Processor processor) {
        processorDAO.save(processor);
    }

    @Transactional
    @Override
    public void update(Processor processor) {
        processorDAO.update(processor);
    }

    @Transactional
    @Override
    public void delete(long id) {
        processorDAO.delete(id);
    }

    @Transactional
    @Override
    public Processor findById(long id) {
        return processorDAO.findById(id);
    }

    @Transactional
    @Override
    public List<Processor> listAllProcessors() {
        return processorDAO.listAllProcessors();
    }

    @Transactional
    @Override
    public boolean isProcessorExists(Processor processor) {
        return processorDAO.isProcessorExists(processor);
    }
}
