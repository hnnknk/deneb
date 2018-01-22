package xyz.hnnknk.deneb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.hnnknk.deneb.model.Monitor;
import xyz.hnnknk.deneb.service.MonitorService;

import java.util.List;

@RestController
public class MonitorController {

    @Autowired
    MonitorService monitorService;

    @RequestMapping(value = "/components/monitor/", method = RequestMethod.GET)
    public ResponseEntity<List<Monitor>> listAllMonitors() {
        List<Monitor> monitors = monitorService.listAllMonitors();
        if(monitors.isEmpty()){
            return new ResponseEntity<List<Monitor>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Monitor>>(monitors, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/monitor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Monitor> getMonitor(@PathVariable("id") long id) {
        System.out.println("Fetching Monitor with id " + id);
        Monitor monitor = monitorService.findById(id);
        if (monitor == null) {
            System.out.println("Monitor with id " + id + " not found");
            return new ResponseEntity<Monitor>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Monitor>(monitor, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/monitor/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMonitor(@RequestBody Monitor monitor, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating " + monitor.toString());

        if (monitorService.isMonitorExists(monitor)) {
            System.out.println("A " + monitor.toString() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        monitorService.save(monitor);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/components/monitor/{id}").buildAndExpand(monitor.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/components/monitor/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Monitor> updateMonitor(@PathVariable("id") long id, @RequestBody Monitor monitor) {
        System.out.println("Updating " + monitor.toString());

        Monitor currentMonitor = monitorService.findById(id);

        if (currentMonitor==null) {
            System.out.println("Monitor with id " + id + " not found");
            return new ResponseEntity<Monitor>(HttpStatus.NOT_FOUND);
        }

        currentMonitor.setInvNumber(monitor.getInvNumber());
        currentMonitor.setManufacter(monitor.getManufacter());
        currentMonitor.setModel(monitor.getModel());
        currentMonitor.setSerial(monitor.getSerial());

        monitorService.update(currentMonitor);
        return new ResponseEntity<Monitor>(currentMonitor, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/monitor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Monitor> deleteMonitor(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Monitor with id " + id);

        Monitor monitor = monitorService.findById(id);
        if (monitor == null) {
            System.out.println("Unable to delete. Monitor with id " + id + " not found");
            return new ResponseEntity<Monitor>(HttpStatus.NOT_FOUND);
        }

        monitorService.delete(id);
        return new ResponseEntity<Monitor>(HttpStatus.NO_CONTENT);
    }
}
