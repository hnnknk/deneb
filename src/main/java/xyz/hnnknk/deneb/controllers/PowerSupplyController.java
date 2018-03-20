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
import xyz.hnnknk.deneb.model.PowerSupply;
import xyz.hnnknk.deneb.service.SystemUnit.SystemUnitService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PowerSupplyController {

    @Autowired
    SystemUnitService<PowerSupply> powerSupplyServiceImpl;


    @RequestMapping(value = "/sysunit/powersupply/", method = RequestMethod.GET)
    public ResponseEntity<List<PowerSupply>> listAllPowerSupplyes() {
        List<PowerSupply> powerSupplyes = powerSupplyServiceImpl.listAll();
        if(powerSupplyes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(powerSupplyes, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/ro/powersupply/", method = RequestMethod.GET)
    public ResponseEntity<List<PowerSupply>> listAllPowerSupplyesRO() {
        List<PowerSupply> powerSupplyes = powerSupplyServiceImpl.listAll();
        if(powerSupplyes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(powerSupplyes, HttpStatus.OK);
    }

    @RequestMapping(value = "/sysunit/powersupply/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PowerSupply> getPowerSupply(@PathVariable("id") long id) {
        System.out.println("Fetching powerSupply with id " + id);

        try {
            PowerSupply powerSupply = powerSupplyServiceImpl.findById(id);
            return new ResponseEntity<>(powerSupply, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sysunit/powersupply/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPowerSupply(@Valid @RequestBody PowerSupply powerSupply, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + powerSupply.toString());

        try {
            powerSupplyServiceImpl.save(powerSupply);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/sysunit/powersupply/{id}").buildAndExpand(powerSupply.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/sysunit/powersupply/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PowerSupply> updatePowerSupply(@PathVariable("id") long id,@Valid @RequestBody PowerSupply powerSupply) {
        System.out.println("Updating " + powerSupply.toString());

        try {
            PowerSupply currentPowerSupply = powerSupplyServiceImpl.findById(id);
            currentPowerSupply.setManufacturer(powerSupply.getManufacturer());
            currentPowerSupply.setModel(powerSupply.getModel());
            currentPowerSupply.setPower(powerSupply.getPower());

            powerSupplyServiceImpl.update(currentPowerSupply);
            return new ResponseEntity<>(currentPowerSupply, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sysunit/powersupply/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PowerSupply> deletePowerSupply(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting PowerSupply with id " + id);

        try {
            powerSupplyServiceImpl.findById(id);

            powerSupplyServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
