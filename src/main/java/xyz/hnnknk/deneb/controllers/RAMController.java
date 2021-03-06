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
import xyz.hnnknk.deneb.model.RAM;
import xyz.hnnknk.deneb.service.SystemUnit.SystemUnitService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RAMController {

    @Autowired
    SystemUnitService<RAM> RAMServiceImpl;

    @RequestMapping(value = "/sysunit/ram/", method = RequestMethod.GET)
    public ResponseEntity<List<RAM>> listAllRAMes() {
        List<RAM> rams = RAMServiceImpl.listAll();
        if(rams.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rams, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/ro/ram/", method = RequestMethod.GET)
    public ResponseEntity<List<RAM>> listAllRAMesRO() {
        List<RAM> rams = RAMServiceImpl.listAll();
        if(rams.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rams, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/ram/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RAM> getRAM(@PathVariable("id") long id) {
        System.out.println("Fetching ram with id " + id);

        try {
            RAM ram = RAMServiceImpl.findById(id);
            return new ResponseEntity<>(ram, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sysunit/ram/", method = RequestMethod.POST)
    public ResponseEntity<Void> createRAM(@Valid @RequestBody RAM ram, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + ram.toString());

        try {
            RAMServiceImpl.save(ram);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/sysunit/ram/{id}").buildAndExpand(ram.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/sysunit/ram/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RAM> updateRAM(@PathVariable("id") long id,@Valid @RequestBody RAM ram) {
        System.out.println("Updating " + ram.toString());

        try {
            RAM currentRAM = RAMServiceImpl.findById(id);
            currentRAM.setCapacity(ram.getCapacity());
            currentRAM.setManufacturer(ram.getManufacturer());
            currentRAM.setModel(ram.getModel());

            RAMServiceImpl.update(currentRAM);
            return new ResponseEntity<>(currentRAM, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sysunit/ram/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RAM> deleteRAM(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting RAM with id " + id);

        try {
            RAMServiceImpl.findById(id);

            RAMServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
