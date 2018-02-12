package xyz.hnnknk.deneb.dao;

import xyz.hnnknk.deneb.model.Processor;

import java.util.List;

public interface ProcessorDAO {

    void save(Processor processor);
    void update(Processor processor);
    void delete(long id);

    Processor findById(long id);

    List<Processor> listAllProcessors();

    boolean isProcessorExists(Processor processor);
}
