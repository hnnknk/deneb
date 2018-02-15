package xyz.hnnknk.deneb.unit.dao;

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
import xyz.hnnknk.deneb.model.Ups;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
@Transactional
public class UpsDAOTest {

    @Autowired
    PeripheralDAO upsDAOImpl;

    private Ups ups;
    private Ups ups2;

    @Before
    public void setup() {
        clear();
        this.ups = new Ups("144","APC","BC500","1n5dHrR85Ye");
        upsDAOImpl.save(ups);
        this.ups2 = new Ups("123","Ippon","i-650","1n5dHrR85Ye");
        upsDAOImpl.save(ups2);
    }

    public void clear() {
        List<Ups> l = upsDAOImpl.listAll();
        for(Ups u : l) {
            upsDAOImpl.delete(u.getId());
        }
    }

    @Test
    public void listAllSuccess() {

        List<Ups> list = upsDAOImpl.listAll();
        assertThat(list, not(empty()));
        assertThat(list, hasSize(2));
        assertThat(list, hasItems(ups, ups2));
    }

    @Test
    public void listAllIsEmpty() {
        clear();
        List<Ups> list = upsDAOImpl.listAll();
        assertThat(list, empty());
    }
}
