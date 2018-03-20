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
import xyz.hnnknk.deneb.model.Keyboard;
import xyz.hnnknk.deneb.service.Peripheral.KeyboardServiceImpl;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class KeyboardServiceTest {

    @InjectMocks
    private KeyboardServiceImpl keyboardService;

    @Mock
    private PeripheralDAO<Keyboard> keyboardDAOImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Keyboard key = new Keyboard("144","BTC","6301C","1n5dHrR85Ye");
        ArrayList<Keyboard> keyboards = new ArrayList<>();
        keyboards.add(key);

        when(keyboardDAOImpl.listAll()).thenReturn(keyboards);
        when(keyboardDAOImpl.findById(1L)).thenReturn(key);
    }

    @Test
    public void findByIdReturnSuccess() throws EntityNotFoundException {
        Keyboard k = keyboardService.findById(1L);
        assertNotNull(k);
        assertEquals("1n5dHrR85Ye", k.getSerial());
        assertEquals("6301C", k.getModel());
        assertEquals("BTC", k.getManufacter());
        assertEquals("144", k.getInvNumber());
    }

    @Test (expected = EntityNotFoundException.class)
    public void findByIdReturnNull() throws EntityNotFoundException {
        keyboardService.findById(111L);
    }

    @Test
    public void saveReturnSuccess() throws EntityExistsException {
        Keyboard k = new Keyboard("123","Logitech","B-100","1n5dHrR85Ye");
        keyboardService.save(k);
    }

    @Test (expected = EntityExistsException.class)
    public void saveReturnEntityException() throws EntityExistsException {
        Keyboard k = new Keyboard("144","BTC","6301C","1n5dHrR85Ye");
        keyboardService.save(k);
    }

    @Test
    public void listAllReturnSuccess() {
        ArrayList<Keyboard> keyboards = (ArrayList<Keyboard>) keyboardService.listAll();
        assertNotNull(keyboards);
        assertNotNull(keyboards.get(0));
        assertEquals("144", keyboards.get(0).getInvNumber());
    }

    @Test
    public void listAllReturnEmpty() {
        reset(keyboardDAOImpl);
        ArrayList<Keyboard> keys = new ArrayList<>();
        when(keyboardDAOImpl.listAll()).thenReturn(keys);

        ArrayList<Keyboard> keyboards = (ArrayList<Keyboard>) keyboardService.listAll();
        assertNotNull(keyboards);
        assertTrue(keyboards.isEmpty());
    }

}
