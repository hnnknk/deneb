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
import xyz.hnnknk.deneb.service.NotificationService;
import xyz.hnnknk.deneb.enums.NotificationTypes;
import xyz.hnnknk.deneb.model.Ups;
import xyz.hnnknk.deneb.service.Peripheral.PeripheralService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UpsController {

    @Autowired
    PeripheralService<Ups> upsServiceImpl;

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/components/ups/", method = RequestMethod.GET)
    public ResponseEntity<List<Ups>> listAllUpses() {
        List<Ups> upses = upsServiceImpl.listAll();
        if(upses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(upses, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/ro/ups/", method = RequestMethod.GET)
    public ResponseEntity<List<Ups>> listAllUpsesRO() {
        List<Ups> upses = upsServiceImpl.listAll();
        if(upses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(upses, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/ups/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ups> getUps(@PathVariable("id") long id) {
        System.out.println("Fetching ups with id " + id);

        try {
            Ups ups = upsServiceImpl.findById(id);
            return new ResponseEntity<>(ups, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/components/ups/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUps(@Valid @RequestBody Ups ups, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + ups.toString());

        try {
            upsServiceImpl.save(ups);
            notificationService.checkNotifications(NotificationTypes.UPS, ups.toString());

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/components/ups/{id}").buildAndExpand(ups.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/components/ups/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ups> updateUps(@PathVariable("id") long id,@Valid @RequestBody Ups ups) {
        System.out.println("Updating " + ups.toString());

        try {
            Ups currentUps = upsServiceImpl.findById(id);
            currentUps.setInvNumber(ups.getInvNumber());
            currentUps.setManufacturer(ups.getManufacturer());
            currentUps.setModel(ups.getModel());
            currentUps.setSerial(ups.getSerial());

            upsServiceImpl.update(currentUps);
            return new ResponseEntity<>(currentUps, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/components/ups/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Ups> deleteUps(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Ups with id " + id);

        try {
            upsServiceImpl.findById(id);

            upsServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
