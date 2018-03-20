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
import xyz.hnnknk.deneb.model.MotherBoard;
import xyz.hnnknk.deneb.service.SystemUnit.SystemUnitService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MotherBoardController {

    @Autowired
    SystemUnitService<MotherBoard> motherBoardServiceImpl;

    @RequestMapping(value = "/sysunit/motherboard/", method = RequestMethod.GET)
    public ResponseEntity<List<MotherBoard>> listAllMotherBoardes() {
        List<MotherBoard> motherBoardes = motherBoardServiceImpl.listAll();
        if(motherBoardes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(motherBoardes, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/ro/motherboard/", method = RequestMethod.GET)
    public ResponseEntity<List<MotherBoard>> listAllMotherBoardesRO() {
        List<MotherBoard> motherBoardes = motherBoardServiceImpl.listAll();
        if(motherBoardes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(motherBoardes, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/motherboard/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MotherBoard> getMotherBoard(@PathVariable("id") long id) {
        System.out.println("Fetching motherBoard with id " + id);
        
        try {
            MotherBoard motherBoard = motherBoardServiceImpl.findById(id);
            return new ResponseEntity<>(motherBoard, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @RequestMapping(value = "/sysunit/motherboard/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMotherBoard(@Valid @RequestBody MotherBoard motherBoard, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + motherBoard.toString());

        try {
            motherBoardServiceImpl.save(motherBoard);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/sysunit/motherboard/{id}").buildAndExpand(motherBoard.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/sysunit/motherboard/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MotherBoard> updateMotherBoard(@PathVariable("id") long id,@Valid @RequestBody MotherBoard motherBoard) {
        System.out.println("Updating " + motherBoard.toString());

        try {
            MotherBoard currentMotherBoard = motherBoardServiceImpl.findById(id);
            currentMotherBoard.setManufacturer(motherBoard.getManufacturer());
            currentMotherBoard.setModel(motherBoard.getModel());
            currentMotherBoard.setSocket(motherBoard.getSocket());

            motherBoardServiceImpl.update(currentMotherBoard);
            return new ResponseEntity<>(currentMotherBoard, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sysunit/motherboard/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<MotherBoard> deleteMotherBoard(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting MotherBoard with id " + id);

        try {
            motherBoardServiceImpl.findById(id);

            motherBoardServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
