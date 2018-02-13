package xyz.hnnknk.deneb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.hnnknk.deneb.service.NotificationService;
import xyz.hnnknk.deneb.enums.NotificationTypes;
import xyz.hnnknk.deneb.model.Ups;
import xyz.hnnknk.deneb.service.Peripheral.PeripheralService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UpsController {

    @Autowired
    PeripheralService upsServiceImpl;

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/components/ups/", method = RequestMethod.GET)
    public ResponseEntity<List<Ups>> listAllUpses() {
        List<Ups> upses = upsServiceImpl.listAll();
        if(upses.isEmpty()){
            return new ResponseEntity<List<Ups>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Ups>>(upses, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/ro/ups/", method = RequestMethod.GET)
    public ResponseEntity<List<Ups>> listAllUpsesRO() {
        List<Ups> upses = upsServiceImpl.listAll();
        if(upses.isEmpty()){
            return new ResponseEntity<List<Ups>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Ups>>(upses, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/ups/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ups> getUps(@PathVariable("id") long id) {
        System.out.println("Fetching ups with id " + id);
        Ups ups = (Ups) upsServiceImpl.findById(id);
        if (ups == null) {
            System.out.println("Ups with id " + id + " not found");
            return new ResponseEntity<Ups>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Ups>(ups, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/ups/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUps(@Valid @RequestBody Ups ups, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + ups.toString());

        if (upsServiceImpl.isExists(ups)) {
            System.out.println("A " + ups.toString() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        notificationService.checkNotifications(NotificationTypes.UPS, ups.toString());

        upsServiceImpl.save(ups);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/components/ups/{id}").buildAndExpand(ups.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/components/ups/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ups> updateUps(@PathVariable("id") long id,@Valid @RequestBody Ups ups) {
        System.out.println("Updating " + ups.toString());

        Ups currentUps = (Ups) upsServiceImpl.findById(id);

        if (currentUps==null) {
            System.out.println("Ups with id " + id + " not found");
            return new ResponseEntity<Ups>(HttpStatus.NOT_FOUND);
        }

        currentUps.setInvNumber(ups.getInvNumber());
        currentUps.setManufacter(ups.getManufacter());
        currentUps.setModel(ups.getModel());
        currentUps.setSerial(ups.getSerial());

        upsServiceImpl.update(currentUps);
        return new ResponseEntity<Ups>(currentUps, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/ups/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Ups> deleteUps(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Ups with id " + id);

        Ups ups = (Ups) upsServiceImpl.findById(id);
        if (ups == null) {
            System.out.println("Unable to delete. Ups with id " + id + " not found");
            return new ResponseEntity<Ups>(HttpStatus.NOT_FOUND);
        }

        upsServiceImpl.delete(id);
        return new ResponseEntity<Ups>(HttpStatus.NO_CONTENT);
    }
}
