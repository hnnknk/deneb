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
import xyz.hnnknk.deneb.model.Mouse;
import xyz.hnnknk.deneb.service.Peripheral.PeripheralService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MouseController {

    @Autowired
    PeripheralService mouseServiceImpl;

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/components/mouse/", method = RequestMethod.GET)
    public ResponseEntity<List<Mouse>> listAllmouses() {
        List<Mouse> mouses = mouseServiceImpl.listAll();
        if(mouses.isEmpty()){
            return new ResponseEntity<List<Mouse>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Mouse>>(mouses, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/ro/mouse/", method = RequestMethod.GET)
    public ResponseEntity<List<Mouse>> listAllmousesRO() {
        List<Mouse> mouses = mouseServiceImpl.listAll();
        if(mouses.isEmpty()){
            return new ResponseEntity<List<Mouse>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Mouse>>(mouses, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/mouse/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mouse> getmouse(@PathVariable("id") long id) {
        System.out.println("Fetching mouse with id " + id);
        Mouse mouse = (Mouse) mouseServiceImpl.findById(id);
        if (mouse == null) {
            System.out.println("mouse with id " + id + " not found");
            return new ResponseEntity<Mouse>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Mouse>(mouse, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/mouse/", method = RequestMethod.POST)
    public ResponseEntity<Void> createmouse(@Valid @RequestBody Mouse mouse, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + mouse.toString());

        if (mouseServiceImpl.isExists(mouse)) {
            System.out.println("A " + mouse.toString() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        notificationService.checkNotifications(NotificationTypes.MOUSE, mouse.toString());

        mouseServiceImpl.save(mouse);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/components/mouse/{id}").buildAndExpand(mouse.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/components/mouse/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Mouse> updatemouse(@PathVariable("id") long id,@Valid @RequestBody Mouse mouse) {
        System.out.println("Updating " + mouse.toString());

        Mouse currentmouse = (Mouse) mouseServiceImpl.findById(id);

        if (currentmouse==null) {
            System.out.println("mouse with id " + id + " not found");
            return new ResponseEntity<Mouse>(HttpStatus.NOT_FOUND);
        }

        currentmouse.setInvNumber(mouse.getInvNumber());
        currentmouse.setManufacter(mouse.getManufacter());
        currentmouse.setModel(mouse.getModel());
        currentmouse.setSerial(mouse.getSerial());

        mouseServiceImpl.update(currentmouse);
        return new ResponseEntity<Mouse>(currentmouse, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/mouse/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Mouse> deletemouse(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting mouse with id " + id);

        Mouse mouse = (Mouse) mouseServiceImpl.findById(id);
        if (mouse == null) {
            System.out.println("Unable to delete. mouse with id " + id + " not found");
            return new ResponseEntity<Mouse>(HttpStatus.NOT_FOUND);
        }

        mouseServiceImpl.delete(id);
        return new ResponseEntity<Mouse>(HttpStatus.NO_CONTENT);
    }
}
