package xyz.hnnknk.deneb.unit.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xyz.hnnknk.deneb.config.WebConfig;
import xyz.hnnknk.deneb.dao.SystemUnit.PowerSupplyDAOImpl;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.PowerSupply;
import xyz.hnnknk.deneb.service.SystemUnit.PowerSupplyServiceImpl;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class PowerSupplyServiceTest {

    @InjectMocks
    private PowerSupplyServiceImpl PowerSupplyServiceImpl;

    @Mock
    private PowerSupplyDAOImpl powerSupplyDAOImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PowerSupply p = new PowerSupply("Corsair", "450Bronze", 450);
        ArrayList<PowerSupply> powerSupplys = new ArrayList<>();
        powerSupplys.add(p);

        when(powerSupplyDAOImpl.listAll()).thenReturn(powerSupplys);
        when(powerSupplyDAOImpl.findById(1L)).thenReturn(p);
    }

    @Test
    public void findByIdReturnSuccess() throws EntityNotFoundException {
        PowerSupply p = PowerSupplyServiceImpl.findById(1L);
        assertNotNull(p);
        assertEquals("450Bronze", p.getModel());
        assertEquals("Corsair", p.getManufacturer());
        assertEquals(Integer.valueOf(450), p.getPower());
    }

    @Test (expected = EntityNotFoundException.class)
    public void findByIdReturnNull() throws EntityNotFoundException {
        PowerSupplyServiceImpl.findById(111L);
    }

    @Test
    public void saveReturnSuccess() throws EntityExistsException {
        PowerSupply p = new PowerSupply("FSP", "HGN400YRE", 400);
        PowerSupplyServiceImpl.save(p);
    }

    @Test (expected = EntityExistsException.class)
    public void saveReturnEntityException() throws EntityExistsException {
        PowerSupply p = new PowerSupply("Corsair", "450Bronze", 450);
        PowerSupplyServiceImpl.save(p);
    }

    @Test
    public void listAllReturnSuccess() {
        ArrayList<PowerSupply> powerSupplys = (ArrayList<PowerSupply>) PowerSupplyServiceImpl.listAll();
        assertNotNull(powerSupplys);
        assertNotNull(powerSupplys.get(0));
        assertEquals("450Bronze", powerSupplys.get(0).getModel());
    }

    @Test
    public void listAllReturnEmpty() {
        reset(powerSupplyDAOImpl);
        ArrayList<PowerSupply> keys = new ArrayList<>();
        when(powerSupplyDAOImpl.listAll()).thenReturn(keys);

        ArrayList<PowerSupply> powerSupplys = (ArrayList<PowerSupply>) PowerSupplyServiceImpl.listAll();
        assertNotNull(powerSupplys);
        assertTrue(powerSupplys.isEmpty());
    }

}

