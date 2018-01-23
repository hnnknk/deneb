package xyz.hnnknk.deneb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.hnnknk.deneb.model.Mouse;
import xyz.hnnknk.deneb.service.MouseService;

import java.util.List;

@RestController
public class MouseController {
    @Autowired
    MouseService mouseService;

    @RequestMapping(value = "/components/mouse/", method = RequestMethod.GET)
    public ResponseEntity<List<Mouse>> listAllmouses() {
        List<Mouse> mouses = mouseService.listAllMouses();
        if(mouses.isEmpty()){
            return new ResponseEntity<List<Mouse>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Mouse>>(mouses, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/mouse/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mouse> getmouse(@PathVariable("id") long id) {
        System.out.println("Fetching mouse with id " + id);
        Mouse mouse = mouseService.findById(id);
        if (mouse == null) {
            System.out.println("mouse with id " + id + " not found");
            return new ResponseEntity<Mouse>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Mouse>(mouse, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/mouse/", method = RequestMethod.POST)
    public ResponseEntity<Void> createmouse(@RequestBody Mouse mouse, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + mouse.toString());

        if (mouseService.isMouseExists(mouse)) {
            System.out.println("A " + mouse.toString() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        mouseService.save(mouse);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/components/mouse/{id}").buildAndExpand(mouse.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/components/mouse/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Mouse> updatemouse(@PathVariable("id") long id, @RequestBody Mouse mouse) {
        System.out.println("Updating " + mouse.toString());

        Mouse currentmouse = mouseService.findById(id);

        if (currentmouse==null) {
            System.out.println("mouse with id " + id + " not found");
            return new ResponseEntity<Mouse>(HttpStatus.NOT_FOUND);
        }

        currentmouse.setInvNumber(mouse.getInvNumber());
        currentmouse.setManufacter(mouse.getManufacter());
        currentmouse.setModel(mouse.getModel());
        currentmouse.setSerial(mouse.getSerial());

        mouseService.update(currentmouse);
        return new ResponseEntity<Mouse>(currentmouse, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/mouse/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Mouse> deletemouse(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting mouse with id " + id);

        Mouse mouse = mouseService.findById(id);
        if (mouse == null) {
            System.out.println("Unable to delete. mouse with id " + id + " not found");
            return new ResponseEntity<Mouse>(HttpStatus.NOT_FOUND);
        }

        mouseService.delete(id);
        return new ResponseEntity<Mouse>(HttpStatus.NO_CONTENT);
    }
}
