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
import xyz.hnnknk.deneb.dao.SystemUnit.MotherBoardDAOImpl;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.MotherBoard;
import xyz.hnnknk.deneb.service.SystemUnit.MotherBoardServiceImpl;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class MotherboardServiceTest {

    @InjectMocks
    private MotherBoardServiceImpl MotherBoardServiceImpl;

    @Mock
    private MotherBoardDAOImpl motherboardDAOImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MotherBoard m = new MotherBoard("Asus", "H81M-K", "1150");
        ArrayList<MotherBoard> motherboards = new ArrayList<>();
        motherboards.add(m);

        when(motherboardDAOImpl.listAll()).thenReturn(motherboards);
        when(motherboardDAOImpl.findById(1L)).thenReturn(m);
    }

    @Test
    public void findByIdReturnSuccess() throws EntityNotFoundException {
        MotherBoard m = MotherBoardServiceImpl.findById(1L);
        assertNotNull(m);
        assertEquals("H81M-K", m.getModel());
        assertEquals("Asus", m.getManufacturer());
        assertEquals("1150", m.getSocket());
    }

    @Test (expected = EntityNotFoundException.class)
    public void findByIdReturnNull() throws EntityNotFoundException {
        MotherBoardServiceImpl.findById(111L);
    }

    @Test
    public void saveReturnSuccess() throws EntityExistsException {
        MotherBoard m = new MotherBoard("Gigabyte", "G4720", "1151");
        MotherBoardServiceImpl.save(m);
    }

    @Test (expected = EntityExistsException.class)
    public void saveReturnEntityException() throws EntityExistsException {
        MotherBoard m = new MotherBoard("Asus", "H81M-K", "1150");
        MotherBoardServiceImpl.save(m);
    }

    @Test
    public void listAllReturnSuccess() {
        ArrayList<MotherBoard> motherboards = (ArrayList<MotherBoard>) MotherBoardServiceImpl.listAll();
        assertNotNull(motherboards);
        assertNotNull(motherboards.get(0));
        assertEquals("H81M-K", motherboards.get(0).getModel());
    }

    @Test
    public void listAllReturnEmpty() {
        reset(motherboardDAOImpl);
        ArrayList<MotherBoard> keys = new ArrayList<>();
        when(motherboardDAOImpl.listAll()).thenReturn(keys);

        ArrayList<MotherBoard> motherboards = (ArrayList<MotherBoard>) MotherBoardServiceImpl.listAll();
        assertNotNull(motherboards);
        assertTrue(motherboards.isEmpty());
    }

}
