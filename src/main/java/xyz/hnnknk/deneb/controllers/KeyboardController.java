package xyz.hnnknk.deneb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.hnnknk.deneb.enums.NotificationTypes;
import xyz.hnnknk.deneb.model.Keyboard;
import xyz.hnnknk.deneb.service.NotificationService;
import xyz.hnnknk.deneb.service.Peripheral.PeripheralService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class KeyboardController {

    @Autowired
    PeripheralService keyboardServiceImpl;

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/components/keyboard/", method = RequestMethod.GET)
    public ResponseEntity<List<Keyboard>> listAllKeyboards() {
        List<Keyboard> keyboards = keyboardServiceImpl.listAll();
        if(keyboards.isEmpty()){
            return new ResponseEntity<List<Keyboard>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Keyboard>>(keyboards, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/ro/keyboard/", method = RequestMethod.GET)
    public ResponseEntity<List<Keyboard>> listAllKeyboardsRO() {
        List<Keyboard> keyboards = keyboardServiceImpl.listAll();
        if(keyboards.isEmpty()){
            return new ResponseEntity<List<Keyboard>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Keyboard>>(keyboards, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/keyboard/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Keyboard> getKeyboard(@PathVariable("id") long id) {
        System.out.println("Fetching Keyboard with id " + id);
        Keyboard keyboard = (Keyboard) keyboardServiceImpl.findById(id);
        if (keyboard == null) {
            System.out.println("Keyboard with id " + id + " not found");
            return new ResponseEntity<Keyboard>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Keyboard>(keyboard, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/keyboard/", method = RequestMethod.POST)
    public ResponseEntity<Void> createKeyboard(@Valid @RequestBody Keyboard keyboard, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + keyboard.toString());

        if (keyboardServiceImpl.isExists(keyboard)) {
            System.out.println("A " + keyboard.toString() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        notificationService.checkNotifications(NotificationTypes.KEYBOARD, keyboard.toString());

        keyboardServiceImpl.save(keyboard);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/components/keyboard/{id}").buildAndExpand(keyboard.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/components/keyboard/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Keyboard> updateKeyboard(@PathVariable("id") long id,@Valid @RequestBody Keyboard keyboard) {
        System.out.println("Updating " + keyboard.toString());

        Keyboard currentKeyboard = (Keyboard) keyboardServiceImpl.findById(id);

        if (currentKeyboard==null) {
            System.out.println("Keyboard with id " + id + " not found");
            return new ResponseEntity<Keyboard>(HttpStatus.NOT_FOUND);
        }

        currentKeyboard.setInvNumber(keyboard.getInvNumber());
        currentKeyboard.setManufacter(keyboard.getManufacter());
        currentKeyboard.setModel(keyboard.getModel());
        currentKeyboard.setSerial(keyboard.getSerial());

        keyboardServiceImpl.update(currentKeyboard);
        return new ResponseEntity<Keyboard>(currentKeyboard, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/keyboard/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Keyboard> deleteKeyboard(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Keyboard with id " + id);

        Keyboard keyboard = (Keyboard) keyboardServiceImpl.findById(id);
        if (keyboard == null) {
            System.out.println("Unable to delete. Keyboard with id " + id + " not found");
            return new ResponseEntity<Keyboard>(HttpStatus.NOT_FOUND);
        }

        keyboardServiceImpl.delete(id);
        return new ResponseEntity<Keyboard>(HttpStatus.NO_CONTENT);
    }
}
