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
import xyz.hnnknk.deneb.model.Ups;
import xyz.hnnknk.deneb.service.Peripheral.UpsServiceImpl;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class UpsServiceTest {

    @InjectMocks
    private UpsServiceImpl upsService;

    @Mock
    private PeripheralDAO upsDAOImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Ups ups = new Ups("144","APC","BC500","1n5dHrR85Ye");
        ArrayList<Ups> upses = new ArrayList<Ups>();
        upses.add(ups);

        Mockito.when(upsDAOImpl.listAll()).thenReturn(upses);
        Mockito.when(upsDAOImpl.findById(1L)).thenReturn(ups);
    }

    @Test
    public void findByIdReturnSuccess() throws EntityNotFoundException {
        Ups ups = upsService.findById(1L);
        Assert.assertNotNull(ups);
        Assert.assertEquals("1n5dHrR85Ye", ups.getSerial());
        Assert.assertEquals("BC500", ups.getModel());
        Assert.assertEquals("APC", ups.getManufacter());
        Assert.assertEquals("144", ups.getInvNumber());
    }

    @Test (expected = EntityNotFoundException.class)
    public void findByIdReturnNull() throws EntityNotFoundException {
        upsService.findById(111L);
    }

    @Test
    public void saveReturnSuccess() throws EntityExistsException {
        Ups ups = new Ups("123","Ippon","i-650","1n5dHrR85Ye");
        upsService.save(ups);
    }

    @Test (expected = EntityExistsException.class)
    public void saveReturnEntityException() throws EntityExistsException {
        Ups k = new Ups("144","APC","BC500","1n5dHrR85Ye");
        upsService.save(k);
    }

    @Test
    public void listAllReturnSuccess() {
        ArrayList<Ups> upses = (ArrayList<Ups>) upsService.listAll();
        Assert.assertNotNull(upses);
        Assert.assertNotNull(upses.get(0));
        Assert.assertEquals("144", upses.get(0).getInvNumber());
    }

    @Test
    public void listAllReturnEmpty() {
        Mockito.reset(upsDAOImpl);
        ArrayList<Ups> us = new ArrayList<Ups>();
        Mockito.when(upsDAOImpl.listAll()).thenReturn(us);

        ArrayList<Ups> upses = (ArrayList<Ups>) upsService.listAll();
        Assert.assertNotNull(upses);
        Assert.assertTrue(upses.isEmpty());
    }

}
