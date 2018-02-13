package xyz.hnnknk.deneb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.hnnknk.deneb.model.HDD;
import xyz.hnnknk.deneb.service.SystemUnitService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HDDController {

    @Autowired
    SystemUnitService hddService;

    @RequestMapping(value = "/sysunit/hdd/", method = RequestMethod.GET)
    public ResponseEntity<List<HDD>> listAllHdds() {
        List<HDD> hdds = hddService.listAll();
        if(hdds.isEmpty()){
            return new ResponseEntity<List<HDD>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<HDD>>(hdds, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/ro/hdd/", method = RequestMethod.GET)
    public ResponseEntity<List<HDD>> listAllHddsRO() {
        List<HDD> hdds = hddService.listAll();
        if(hdds.isEmpty()){
            return new ResponseEntity<List<HDD>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<HDD>>(hdds, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/hdd/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HDD> getHDD(@PathVariable("id") long id) {
        System.out.println("Fetching HDD with id " + id);
        HDD hdd = (HDD) hddService.findById(id);
        if (hdd == null) {
            System.out.println("HDD with id " + id + " not found");
            return new ResponseEntity<HDD>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<HDD>(hdd, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/hdd/", method = RequestMethod.POST)
    public ResponseEntity<Void> createHDD(@Valid @RequestBody HDD hdd, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + hdd.toString());

        if (hddService.isExists(hdd)) {
            System.out.println("A " + hdd.toString() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        hddService.save(hdd);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/sysunit/hdd/{id}").buildAndExpand(hdd.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/sysunit/hdd/{id}", method = RequestMethod.PUT)
    public ResponseEntity<HDD> updateHDD(@PathVariable("id") long id,@Valid @RequestBody HDD hdd) {
        System.out.println("Updating " + hdd.toString());

        HDD currentHDD = (HDD) hddService.findById(id);

        if (currentHDD==null) {
            System.out.println("HDD with id " + id + " not found");
            return new ResponseEntity<HDD>(HttpStatus.NOT_FOUND);
        }

        currentHDD.setManufacter(hdd.getManufacter());
        currentHDD.setModel(hdd.getModel());
        currentHDD.setSerial(hdd.getSerial());
        currentHDD.setCapacity(hdd.getCapacity());
        currentHDD.setHddTypes(hdd.getHddTypes());

        hddService.update(currentHDD);
        return new ResponseEntity<HDD>(currentHDD, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/hdd/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HDD> deleteHDD(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting HDD with id " + id);

        HDD hdd = (HDD) hddService.findById(id);
        if (hdd == null) {
            System.out.println("Unable to delete. HDD with id " + id + " not found");
            return new ResponseEntity<HDD>(HttpStatus.NOT_FOUND);
        }

        hddService.delete(id);
        return new ResponseEntity<HDD>(HttpStatus.NO_CONTENT);
    }
}
