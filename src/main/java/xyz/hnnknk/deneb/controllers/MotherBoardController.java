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
import xyz.hnnknk.deneb.model.MotherBoard;
import xyz.hnnknk.deneb.service.SystemUnit.SystemUnitService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MotherBoardController {

    @Autowired
    SystemUnitService motherBoardServiceImpl;

    @RequestMapping(value = "/sysunit/motherBoard/", method = RequestMethod.GET)
    public ResponseEntity<List<MotherBoard>> listAllMotherBoardes() {
        List<MotherBoard> motherBoardes = motherBoardServiceImpl.listAll();
        if(motherBoardes.isEmpty()){
            return new ResponseEntity<List<MotherBoard>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<MotherBoard>>(motherBoardes, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunits/ro/motherBoard/", method = RequestMethod.GET)
    public ResponseEntity<List<MotherBoard>> listAllMotherBoardesRO() {
        List<MotherBoard> motherBoardes = motherBoardServiceImpl.listAll();
        if(motherBoardes.isEmpty()){
            return new ResponseEntity<List<MotherBoard>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<MotherBoard>>(motherBoardes, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/motherBoard/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MotherBoard> getMotherBoard(@PathVariable("id") long id) {
        System.out.println("Fetching motherBoard with id " + id);

        try {
            MotherBoard motherBoard = (MotherBoard) motherBoardServiceImpl.findById(id);
            return new ResponseEntity<MotherBoard>(motherBoard, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<MotherBoard>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sysunit/motherBoard/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMotherBoard(@Valid @RequestBody MotherBoard motherBoard, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + motherBoard.toString());

        try {
            motherBoardServiceImpl.save(motherBoard);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/sysunit/motherBoard/{id}").buildAndExpand(motherBoard.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/sysunit/motherBoard/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MotherBoard> updateMotherBoard(@PathVariable("id") long id,@Valid @RequestBody MotherBoard motherBoard) {
        System.out.println("Updating " + motherBoard.toString());

        try {
            MotherBoard currentMotherBoard = (MotherBoard) motherBoardServiceImpl.findById(id);
            currentMotherBoard.setManufacter(motherBoard.getManufacter());
            currentMotherBoard.setModel(motherBoard.getModel());
            currentMotherBoard.setSocket(motherBoard.getSocket());

            motherBoardServiceImpl.update(currentMotherBoard);
            return new ResponseEntity<MotherBoard>(currentMotherBoard, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<MotherBoard>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sysunit/motherBoard/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<MotherBoard> deleteMotherBoard(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting MotherBoard with id " + id);

        try {
            motherBoardServiceImpl.findById(id);

            motherBoardServiceImpl.delete(id);
            return new ResponseEntity<MotherBoard>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<MotherBoard>(HttpStatus.NOT_FOUND);
        }
    }
}
