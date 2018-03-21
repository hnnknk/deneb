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
import xyz.hnnknk.deneb.model.Monitor;
import xyz.hnnknk.deneb.service.Peripheral.PeripheralService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MonitorController {

    @Autowired
    PeripheralService<Monitor> monitorServiceImpl;

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/components/monitor/", method = RequestMethod.GET)
    public ResponseEntity<List<Monitor>> listAllMonitors() {
        List<Monitor> monitors = monitorServiceImpl.listAll();
        if(monitors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(monitors, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/ro/monitor/", method = RequestMethod.GET)
    public ResponseEntity<List<Monitor>> listAllMonitorsRO() {
        List<Monitor> monitors = monitorServiceImpl.listAll();
        if(monitors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(monitors, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/monitor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Monitor> getMonitor(@PathVariable("id") long id) {
        System.out.println("Fetching Monitor with id " + id);

        try {
            Monitor monitor = monitorServiceImpl.findById(id);

            return new ResponseEntity<>(monitor, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/components/monitor/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMonitor(@Valid @RequestBody Monitor monitor, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + monitor.toString());

        try {
            monitorServiceImpl.save(monitor);
            notificationService.checkNotifications(NotificationTypes.MONITOR, monitor.toString());

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/components/monitor/{id}").buildAndExpand(monitor.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (EntityExistsException e ) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/components/monitor/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Monitor> updateMonitor(@PathVariable("id") long id,@Valid @RequestBody Monitor monitor) {
        System.out.println("Updating " + monitor.toString());

        try {
            Monitor currentMonitor = monitorServiceImpl.findById(id);
            currentMonitor.setInvNumber(monitor.getInvNumber());
            currentMonitor.setManufacturer(monitor.getManufacturer());
            currentMonitor.setModel(monitor.getModel());
            currentMonitor.setSerial(monitor.getSerial());

            monitorServiceImpl.update(currentMonitor);
            return new ResponseEntity<>(currentMonitor, HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/components/monitor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Monitor> deleteMonitor(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Monitor with id " + id);

        try {
            monitorServiceImpl.findById(id);

            monitorServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
