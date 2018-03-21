package xyz.hnnknk.deneb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.hnnknk.deneb.enums.NotificationTypes;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.Keyboard;
import xyz.hnnknk.deneb.service.NotificationService;
import xyz.hnnknk.deneb.service.Peripheral.PeripheralService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class KeyboardController {

    @Autowired
    PeripheralService<Keyboard> keyboardServiceImpl;

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/components/keyboard/", method = RequestMethod.GET)
    public ResponseEntity<List<Keyboard>> listAllKeyboards() {
        List<Keyboard> keyboards = keyboardServiceImpl.listAll();
        if(keyboards.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(keyboards, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/ro/keyboard/", method = RequestMethod.GET)
    public ResponseEntity<List<Keyboard>> listAllKeyboardsRO() {
        List<Keyboard> keyboards = keyboardServiceImpl.listAll();
        if(keyboards.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(keyboards, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/keyboard/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Keyboard> getKeyboard(@PathVariable("id") long id) {
        System.out.println("Fetching Keyboard with id " + id);

        try {
            Keyboard keyboard = keyboardServiceImpl.findById(id);

            return new ResponseEntity<>(keyboard, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/components/keyboard/", method = RequestMethod.POST)
    public ResponseEntity<Void> createKeyboard(@Valid @RequestBody Keyboard keyboard, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + keyboard.toString());

        try {
            keyboardServiceImpl.save(keyboard);
            notificationService.checkNotifications(NotificationTypes.KEYBOARD, keyboard.toString());

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/components/keyboard/{id}").buildAndExpand(keyboard.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/components/keyboard/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Keyboard> updateKeyboard(@PathVariable("id") long id,@Valid @RequestBody Keyboard keyboard) {
        System.out.println("Updating " + keyboard.toString());

        try {
            Keyboard currentKeyboard = keyboardServiceImpl.findById(id);
            currentKeyboard.setInvNumber(keyboard.getInvNumber());
            currentKeyboard.setManufacturer(keyboard.getManufacturer());
            currentKeyboard.setModel(keyboard.getModel());
            currentKeyboard.setSerial(keyboard.getSerial());

            keyboardServiceImpl.update(currentKeyboard);
            return new ResponseEntity<>(currentKeyboard, HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/components/keyboard/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Keyboard> deleteKeyboard(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Keyboard with id " + id);

        try {
            keyboardServiceImpl.findById(id);

            keyboardServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
