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
import xyz.hnnknk.deneb.dao.SystemUnit.HDDDAOImpl;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.HDD;
import xyz.hnnknk.deneb.service.SystemUnit.HDDServiceImpl;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class HddServiceTest {

    @InjectMocks
    private HDDServiceImpl HDDServiceImpl;

    @Mock
    private HDDDAOImpl hddDAOImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        HDD h = new HDD("Toshiba", "DA150ATA", "SN3432fydb", 500, "HDD");
        ArrayList<HDD> hdds = new ArrayList<>();
        hdds.add(h);

        when(hddDAOImpl.listAll()).thenReturn(hdds);
        when(hddDAOImpl.findById(1L)).thenReturn(h);
    }

    @Test
    public void findByIdReturnSuccess() throws EntityNotFoundException {
        HDD h = HDDServiceImpl.findById(1L);
        assertNotNull(h);
        assertEquals("SN3432fydb", h.getSerial());
        assertEquals("DA150ATA", h.getModel());
        assertEquals("Toshiba", h.getManufacturer());
        assertEquals(Integer.valueOf(500), h.getCapacity());
        assertEquals("HDD", h.getHddType());
    }

    @Test (expected = EntityNotFoundException.class)
    public void findByIdReturnNull() throws EntityNotFoundException {
        HDDServiceImpl.findById(111L);
    }

    @Test
    public void saveReturnSuccess() throws EntityExistsException {
        HDD h = new HDD("Samsung", "EVO850", "SN37f6dj5", 256, "SSD");
        HDDServiceImpl.save(h);
    }

    @Test (expected = EntityExistsException.class)
    public void saveReturnEntityException() throws EntityExistsException {
        HDD h = new HDD("Toshiba", "DA150ATA", "SN3432fydb", 500, "HDD");
        HDDServiceImpl.save(h);
    }

    @Test
    public void listAllReturnSuccess() {
        ArrayList<HDD> hdds = (ArrayList<HDD>) HDDServiceImpl.listAll();
        assertNotNull(hdds);
        assertNotNull(hdds.get(0));
        assertEquals("SN3432fydb", hdds.get(0).getSerial());
    }

    @Test
    public void listAllReturnEmpty() {
        reset(hddDAOImpl);
        ArrayList<HDD> keys = new ArrayList<>();
        when(hddDAOImpl.listAll()).thenReturn(keys);

        ArrayList<HDD> hdds = (ArrayList<HDD>) HDDServiceImpl.listAll();
        assertNotNull(hdds);
        assertTrue(hdds.isEmpty());
    }

}
