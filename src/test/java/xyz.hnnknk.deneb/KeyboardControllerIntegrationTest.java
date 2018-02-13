package xyz.hnnknk.deneb;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import xyz.hnnknk.deneb.config.WebConfig;
import xyz.hnnknk.deneb.model.Keyboard;
import xyz.hnnknk.deneb.service.Peripheral.PeripheralService;

import javax.servlet.ServletContext;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class KeyboardControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PeripheralService keyboardServiceImpl;

    private MockMvc mockMvc;

    private Long firstId;
    private Long secondId;

    @Before
    public void setup()  {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.keyboardServiceImpl.save(new Keyboard("144", "BTC", "6301c", "3Hg45ks86Gr"));
        this.keyboardServiceImpl.save(new Keyboard("155", "Logitech", "K120", "783G6r45TNe"));

        List<Keyboard> list = this.keyboardServiceImpl.listAll();
        for(Keyboard k : list) {
            if(k.getInvNumber().equals("144")) {
                firstId = k.getId();
            } else if (k.getInvNumber().equals("155")) {
                secondId = k.getId();
            }
        }
    }

    @Test
    public void keyboardControllerExists() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("keyboardController"));
    }

    @Test
    public void testGetAllSuccess() throws Exception {

        mockMvc.perform(get("/components/keyboard/"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetSuccess() throws Exception {

        mockMvc.perform(get("/components/keyboard/" + firstId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.serial", is("3Hg45ks86Gr")))
                .andExpect(jsonPath("$.manufacter", is("BTC")))
                .andExpect(jsonPath("$.model", is("6301c")))
                .andExpect(jsonPath("$.id", is(firstId.intValue())))
                .andExpect(jsonPath("$.invNumber", is("144")));
    }

    @Test
    public void testCreateSuccess() throws Exception {

        Keyboard m = new Keyboard("131", "Defender", "df-563cr", "75HdnG45K23ls");

        String d = new ObjectMapper().writeValueAsString(m);

        mockMvc.perform(post("/components/keyboard/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void testDeleteSuccess() throws Exception {

        mockMvc.perform(delete("/components/keyboard/" + secondId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateSuccess() throws Exception {

        Keyboard m = new Keyboard("144", "Defender", "df-566v", "u4Rgd620Nc3b");

        String d = new ObjectMapper().writeValueAsString(m);

        mockMvc.perform(put("/components/keyboard/" + firstId).content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetFailed() throws Exception {

        mockMvc.perform(get("/components/keyboard/144444"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testCreateFailed() throws Exception {

        Keyboard m = new Keyboard("144", "Defender", "df-566v", "u4Rgd620Nc3b");

        String d = new ObjectMapper().writeValueAsString(m);

        mockMvc.perform(post("/components/keyboard/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isConflict());


    }

    @Test
    public void testUpdateFailed() throws Exception {

        Keyboard m = new Keyboard("144", "Defender", "df-566v", "u4Rgd620Nc3b");

        String d = new ObjectMapper().writeValueAsString(m);

        mockMvc.perform(put("/components/keyboard/144444").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDeleteFailed() throws Exception {

        mockMvc.perform(delete("/components/keyboard/144444"))
                .andExpect(status().isNotFound());
    }

}
