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
import xyz.hnnknk.deneb.model.Keyboard;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
@Transactional
public class KeyboardDAOTest {

    @Autowired
    PeripheralDAO keyboardDAOImpl;

    private Keyboard key;
    private Keyboard key2;

    @Before
    public void setup() {
        this.key = new Keyboard("144","BTC","6301C","1n5dHrR85Ye");
        keyboardDAOImpl.save(key);
        this.key2 = new Keyboard("123","Logitech","B-100","1n5dHrR85Ye");
        keyboardDAOImpl.save(key2);
    }

    public void clear() {
        List<Keyboard> l = keyboardDAOImpl.listAll();
        for(Keyboard m : l) {
            keyboardDAOImpl.delete(m.getId());
        }
    }

    @Test
    public void listAllSuccess() {
        List<Keyboard> list = keyboardDAOImpl.listAll();
        assertThat(list, not(empty()));
        assertThat(list, hasSize(2));
        assertThat(list, hasItems(key, key2));
    }

    @Test
    public void listAllIsEmpty() {
        clear();
        List<Keyboard> list = keyboardDAOImpl.listAll();
        assertThat(list, empty());
    }
}
