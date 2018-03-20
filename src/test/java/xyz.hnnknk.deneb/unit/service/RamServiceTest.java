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
import xyz.hnnknk.deneb.dao.SystemUnit.RAMDAOImpl;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.RAM;
import xyz.hnnknk.deneb.service.SystemUnit.RAMServiceImpl;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class RamServiceTest {

    @InjectMocks
    private RAMServiceImpl RAMServiceImpl;

    @Mock
    private RAMDAOImpl ramDAOImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RAM r = new RAM("Kingston", "4PNGS4753D/2", 4096);
        ArrayList<RAM> rams = new ArrayList<>();
        rams.add(r);

        when(ramDAOImpl.listAll()).thenReturn(rams);
        when(ramDAOImpl.findById(1L)).thenReturn(r);
    }

    @Test
    public void findByIdReturnSuccess() throws EntityNotFoundException {
        RAM r = RAMServiceImpl.findById(1L);
        assertNotNull(r);
        assertEquals("4PNGS4753D/2", r.getModel());
        assertEquals("Kingston", r.getManufacturer());
        assertEquals(Integer.valueOf(4096), r.getCapacity());
    }

    @Test (expected = EntityNotFoundException.class)
    public void findByIdReturnNull() throws EntityNotFoundException {
        RAMServiceImpl.findById(111L);
    }

    @Test
    public void saveReturnSuccess() throws EntityExistsException {
        RAM r = new RAM("Samsung", "4SMG3852/2", 2056);
        RAMServiceImpl.save(r);
    }

    @Test (expected = EntityExistsException.class)
    public void saveReturnEntityException() throws EntityExistsException {
        RAM r = new RAM("Kingston", "4PNGS4753D/2", 4096);
        RAMServiceImpl.save(r);
    }

    @Test
    public void listAllReturnSuccess() {
        ArrayList<RAM> rams = (ArrayList<RAM>) RAMServiceImpl.listAll();
        assertNotNull(rams);
        assertNotNull(rams.get(0));
        assertEquals("4PNGS4753D/2", rams.get(0).getModel());
    }

    @Test
    public void listAllReturnEmpty() {
        reset(ramDAOImpl);
        ArrayList<RAM> keys = new ArrayList<>();
        when(ramDAOImpl.listAll()).thenReturn(keys);

        ArrayList<RAM> rams = (ArrayList<RAM>) RAMServiceImpl.listAll();
        assertNotNull(rams);
        assertTrue(rams.isEmpty());
    }

}

