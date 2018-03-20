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
import xyz.hnnknk.deneb.model.Mouse;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
@Transactional
public class MouseDAOTest {

    @Autowired
    PeripheralDAO<Mouse> mouseDAOImpl;

    private Mouse mouse;
    private Mouse mouse2;

    @Before
    public void setup() {
        clear();
        this.mouse = new Mouse("144","A4tech","x7","1n5dHrR85Ye");
        mouseDAOImpl.save(mouse);
        this.mouse2 = new Mouse("123","Logitech","B-100","1n5dHrR85Ye");
        mouseDAOImpl.save(mouse2);
    }

    public void clear() {
        List<Mouse> l = mouseDAOImpl.listAll();
        for(Mouse m : l) {
            mouseDAOImpl.delete(m.getId());
        }
    }

    @Test
    public void listAllSuccess() {

        List<Mouse> list = mouseDAOImpl.listAll();
        assertThat(list, not(empty()));
        assertThat(list, hasSize(2));
        assertThat(list, hasItems(mouse, mouse2));
    }

    @Test
    public void listAllIsEmpty() {
        clear();
        List<Mouse> list = mouseDAOImpl.listAll();
        assertThat(list, empty());
    }
}
