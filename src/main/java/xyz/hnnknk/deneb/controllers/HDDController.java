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
import xyz.hnnknk.deneb.model.HDD;
import xyz.hnnknk.deneb.service.SystemUnit.SystemUnitService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HDDController {

    @Autowired
    SystemUnitService<HDD> HDDServiceImpl;

    @RequestMapping(value = "/sysunit/hdd/", method = RequestMethod.GET)
    public ResponseEntity<List<HDD>> listAllHDDes() {
        List<HDD> hdds = HDDServiceImpl.listAll();
        if(hdds.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hdds, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/ro/hdd/", method = RequestMethod.GET)
    public ResponseEntity<List<HDD>> listAllHDDesRO() {
        List<HDD> hdds = HDDServiceImpl.listAll();
        if(hdds.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hdds, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/hdd/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HDD> getHDD(@PathVariable("id") long id) {
        System.out.println("Fetching hdd with id " + id);

        try {
            HDD hdd = HDDServiceImpl.findById(id);
            return new ResponseEntity<>(hdd, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sysunit/hdd/", method = RequestMethod.POST)
    public ResponseEntity<Void> createHDD(@Valid @RequestBody HDD hdd, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + hdd.toString());

        try {
            HDDServiceImpl.save(hdd);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/sysunit/hdd/{id}").buildAndExpand(hdd.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/sysunit/hdd/{id}", method = RequestMethod.PUT)
    public ResponseEntity<HDD> updateHDD(@PathVariable("id") long id,@Valid @RequestBody HDD hdd) {
        System.out.println("Updating " + hdd.toString());

        try {
            HDD currentHDD = HDDServiceImpl.findById(id);
            currentHDD.setCapacity(hdd.getCapacity());
            currentHDD.setHddType(hdd.getHddType());
            currentHDD.setManufacturer(hdd.getManufacturer());
            currentHDD.setModel(hdd.getModel());
            currentHDD.setSerial(hdd.getSerial());

            HDDServiceImpl.update(currentHDD);
            return new ResponseEntity<>(currentHDD, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sysunit/hdd/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HDD> deleteHDD(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting HDD with id " + id);

        try {
            HDDServiceImpl.findById(id);

            HDDServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
