package xyz.hnnknk.deneb.unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.config.WebConfig;
import xyz.hnnknk.deneb.dao.Peripheral.PeripheralDAO;
import xyz.hnnknk.deneb.model.Monitor;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
@Transactional
public class MonitorDAOTest {

    @Autowired
    PeripheralDAO monitorDAOImpl;

    private Monitor mon;
    private Monitor mon2;

    @Before
    public void setup() {
        clear();
        this.mon = new Monitor("144","Philips","170v","1n5dHrR85Ye");
        monitorDAOImpl.save(mon);
        this.mon2 = new Monitor("123","Acer","m-125","1n5dHrR85Ye");
        monitorDAOImpl.save(mon2);
    }

    public void clear() {
        List<Monitor> l = monitorDAOImpl.listAll();
        for(Monitor m : l) {
            monitorDAOImpl.delete(m.getId());
        }
    }

    @Test
    public void listAllSuccess() {

        List<Monitor> list = monitorDAOImpl.listAll();
        assertThat(list, not(empty()));
        assertThat(list, hasSize(2));
        assertThat(list, hasItems(mon, mon2));
    }

    @Test
    public void listAllIsEmpty() {
        clear();
        List<Monitor> list = monitorDAOImpl.listAll();
        assertThat(list, empty());
    }
}
