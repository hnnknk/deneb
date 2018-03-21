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
import xyz.hnnknk.deneb.model.Mouse;
import xyz.hnnknk.deneb.service.Peripheral.PeripheralService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MouseController {

    @Autowired
    PeripheralService<Mouse> mouseServiceImpl;

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/components/mouse/", method = RequestMethod.GET)
    public ResponseEntity<List<Mouse>> listAllMouses() {
        List<Mouse> mouses = mouseServiceImpl.listAll();
        if(mouses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mouses, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/ro/mouse/", method = RequestMethod.GET)
    public ResponseEntity<List<Mouse>> listAllMousesRO() {
        List<Mouse> mouses = mouseServiceImpl.listAll();
        if(mouses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mouses, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/mouse/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mouse> getMouse(@PathVariable("id") long id) {
        System.out.println("Fetching mouse with id " + id);

        try {
            Mouse mouse = mouseServiceImpl.findById(id);

            return new ResponseEntity<>(mouse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/components/mouse/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMouse(@Valid @RequestBody Mouse mouse, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + mouse.toString());

        try {
            mouseServiceImpl.save(mouse);
            notificationService.checkNotifications(NotificationTypes.MOUSE, mouse.toString());

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/components/mouse/{id}").buildAndExpand(mouse.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);

        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/components/mouse/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Mouse> updateMouse(@PathVariable("id") long id,@Valid @RequestBody Mouse mouse) {
        System.out.println("Updating " + mouse.toString());

        try {
            Mouse currentMouse = mouseServiceImpl.findById(id);
            currentMouse.setInvNumber(mouse.getInvNumber());
            currentMouse.setManufacturer(mouse.getManufacturer());
            currentMouse.setModel(mouse.getModel());
            currentMouse.setSerial(mouse.getSerial());

            mouseServiceImpl.update(currentMouse);
            return new ResponseEntity<>(currentMouse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/components/mouse/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Mouse> deleteMouse(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting mouse with id " + id);

        try {
            mouseServiceImpl.findById(id);

            mouseServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
