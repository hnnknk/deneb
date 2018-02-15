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
import xyz.hnnknk.deneb.model.Mouse;
import xyz.hnnknk.deneb.service.Peripheral.MouseServiceImpl;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class MouseServiceTest {

    @InjectMocks
    private MouseServiceImpl mouseService;

    @Mock
    private PeripheralDAO mouseDAOImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Mouse mou = new Mouse("144","A4tech","x7","1n5dHrR85Ye");
        ArrayList<Mouse> mouses = new ArrayList<Mouse>();
        mouses.add(mou);

        Mockito.when(mouseDAOImpl.listAll()).thenReturn(mouses);
        Mockito.when(mouseDAOImpl.findById(1L)).thenReturn(mou);
    }

    @Test
    public void findByIdReturnSuccess() throws EntityNotFoundException {
        Mouse m = mouseService.findById(1L);
        Assert.assertNotNull(m);
        Assert.assertEquals("1n5dHrR85Ye", m.getSerial());
        Assert.assertEquals("x7", m.getModel());
        Assert.assertEquals("A4tech", m.getManufacter());
        Assert.assertEquals("144", m.getInvNumber());
    }

    @Test (expected = EntityNotFoundException.class)
    public void findByIdReturnNull() throws EntityNotFoundException {
        mouseService.findById(111L);
    }

    @Test
    public void saveReturnSuccess() throws EntityExistsException {
        Mouse m = new Mouse("123","Logitech","B-100","1n5dHrR85Ye");
        mouseService.save(m);
    }

    @Test (expected = EntityExistsException.class)
    public void saveReturnEntityException() throws EntityExistsException {
        Mouse m = new Mouse("144","A4tech","x7","1n5dHrR85Ye");
        mouseService.save(m);
    }

    @Test
    public void listAllReturnSuccess() {
        ArrayList<Mouse> mouses = (ArrayList<Mouse>) mouseService.listAll();
        Assert.assertNotNull(mouses);
        Assert.assertNotNull(mouses.get(0));
        Assert.assertEquals("144", mouses.get(0).getInvNumber());
    }

    @Test
    public void listAllReturnEmpty() {
        Mockito.reset(mouseDAOImpl);
        ArrayList<Mouse> mous = new ArrayList<Mouse>();
        Mockito.when(mouseDAOImpl.listAll()).thenReturn(mous);

        ArrayList<Mouse> mouses = (ArrayList<Mouse>) mouseService.listAll();
        Assert.assertNotNull(mouses);
        Assert.assertTrue(mouses.isEmpty());
    }

}
