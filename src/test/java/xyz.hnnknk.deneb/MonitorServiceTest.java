package xyz.hnnknk.deneb;

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
import xyz.hnnknk.deneb.model.Monitor;
import xyz.hnnknk.deneb.service.Peripheral.MonitorServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class MonitorServiceTest {

    @InjectMocks
    private MonitorServiceImpl monitorService;

    @Mock
    private PeripheralDAO monitorDAOImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Monitor mon = new Monitor();
        mon.setSerial("1n5dHrR85Ye");
        mon.setModel("170v");
        mon.setManufacter("Philips");
        mon.setInvNumber("144");
        Mockito.when(monitorDAOImpl.findById(1L)).thenReturn(mon);
    }

    @Test
    public void testMonitorFindById() {
        Monitor m = monitorService.findById(1L);
        Assert.assertNotNull(m);
        Assert.assertEquals("1n5dHrR85Ye", m.getSerial());
        Assert.assertEquals("170v", m.getModel());
        Assert.assertEquals("Philips", m.getManufacter());
        Assert.assertEquals("144", m.getInvNumber());
    }




}
