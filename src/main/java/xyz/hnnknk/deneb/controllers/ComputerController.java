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
import xyz.hnnknk.deneb.model.Computer;
import xyz.hnnknk.deneb.model.HDD;
import xyz.hnnknk.deneb.service.ComputerService;
import xyz.hnnknk.deneb.service.SystemUnit.SystemUnitService;

import javax.validation.Valid;

@RestController
public class ComputerController {

    @Autowired
    ComputerService computerService;

    @Autowired
    SystemUnitService<HDD> HDDServiceImpl;

    @RequestMapping(value = "/computers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Computer> getComputer(@PathVariable("id") long id) {
        System.out.println("Fetching computer with id " + id);

        try {
            Computer computer = computerService.findById(id);
            return new ResponseEntity<>(computer, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/computers/", method = RequestMethod.POST)
    public ResponseEntity<Void> createComputer(@Valid @RequestBody Computer computer, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + computer.toString());

        try {
            computerService.save(computer);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/computers{id}").buildAndExpand(computer.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }
}
