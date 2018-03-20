package xyz.hnnknk.deneb.unit.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xyz.hnnknk.deneb.config.WebConfig;
import xyz.hnnknk.deneb.dao.Peripheral.PeripheralDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.Monitor;
import xyz.hnnknk.deneb.service.Peripheral.MonitorServiceImpl;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class MonitorServiceTest {

    @InjectMocks
    private MonitorServiceImpl monitorService;

    @Mock
    private PeripheralDAO<Monitor> monitorDAOImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Monitor mon = new Monitor("144","Philips","170v","1n5dHrR85Ye");
        ArrayList<Monitor> monitors = new ArrayList<>();
        monitors.add(mon);

        when(monitorDAOImpl.listAll()).thenReturn(monitors);
        when(monitorDAOImpl.findById(1L)).thenReturn(mon);
    }

    @Test
    public void findByIdReturnSuccess() throws EntityNotFoundException {
        Monitor m = monitorService.findById(1L);
        assertNotNull(m);
        assertEquals("1n5dHrR85Ye", m.getSerial());
        assertEquals("170v", m.getModel());
        assertEquals("Philips", m.getManufacter());
        assertEquals("144", m.getInvNumber());
    }

    @Test (expected = EntityNotFoundException.class)
    public void findByIdReturnNull() throws EntityNotFoundException {
        monitorService.findById(111L);
    }

    @Test
    public void saveReturnSuccess() throws EntityExistsException {
        Monitor m = new Monitor("123","Acer","m-125","1n5dHrR85Ye");
        monitorService.save(m);
    }

    @Test (expected = EntityExistsException.class)
    public void saveReturnEntityException() throws EntityExistsException {
        Monitor m = new Monitor("144","Acer","m-125","1n5dHrR85Ye");
        monitorService.save(m);
    }

    @Test
    public void listAllReturnSuccess() {
        ArrayList<Monitor> monitors = (ArrayList<Monitor>) monitorService.listAll();
        assertNotNull(monitors);
        assertNotNull(monitors.get(0));
        assertEquals("144", monitors.get(0).getInvNumber());
    }

    @Test
    public void listAllReturnEmpty() {
        reset(monitorDAOImpl);
        ArrayList<Monitor> mons = new ArrayList<Monitor>();
        when(monitorDAOImpl.listAll()).thenReturn(mons);

        ArrayList<Monitor> monitors = (ArrayList<Monitor>) monitorService.listAll();
        assertNotNull(monitors);
        assertTrue(monitors.isEmpty());
    }

}
