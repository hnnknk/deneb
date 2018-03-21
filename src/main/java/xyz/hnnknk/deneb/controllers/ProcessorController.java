package xyz.hnnknk.deneb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.Processor;
import xyz.hnnknk.deneb.service.SystemUnit.SystemUnitService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProcessorController {

    @Autowired
    SystemUnitService processorServiceImpl;

    @RequestMapping(value = "/sysunit/processor/", method = RequestMethod.GET)
    public ResponseEntity<List<Processor>> listAllProcessors() {
        List<Processor> processors = processorServiceImpl.listAll();
        if(processors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(processors, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/ro/processor/", method = RequestMethod.GET)
    public ResponseEntity<List<Processor>> listAllProcessorsRO() {
        List<Processor> processors = processorServiceImpl.listAll();
        if(processors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(processors, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/processor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Processor> getProcessor(@PathVariable("id") long id) {
        System.out.println("Fetching processor with id " + id);

        try {
            Processor processor = (Processor) processorServiceImpl.findById(id);
            return new ResponseEntity<>(processor, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sysunit/processor/", method = RequestMethod.POST)
    public ResponseEntity<Void> createProcessor(@Valid @RequestBody Processor processor, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + processor.toString());

        try {
            processorServiceImpl.save(processor);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/sysunit/processor/{id}").buildAndExpand(processor.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/sysunit/processor/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Processor> updateProcessor(@PathVariable("id") long id,@Valid @RequestBody Processor processor) {
        System.out.println("Updating " + processor.toString());

        try {
            Processor currentProcessor = (Processor) processorServiceImpl.findById(id);
            currentProcessor.setManufacturer(processor.getManufacturer());
            currentProcessor.setModel(processor.getModel());
            currentProcessor.setNumberOfCores(processor.getNumberOfCores());
            currentProcessor.setSpeed(processor.getSpeed());

            processorServiceImpl.update(currentProcessor);
            return new ResponseEntity<>(currentProcessor, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sysunit/processor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Processor> deleteProcessor(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Processor with id " + id);

        try {
            processorServiceImpl.findById(id);

            processorServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
