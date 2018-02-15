package xyz.hnnknk.deneb.integration;

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
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.model.Mouse;
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
public class MouseControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PeripheralService mouseServiceImpl;

    private MockMvc mockMvc;

    private Long firstId;
    private Long secondId;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        try {
            this.mouseServiceImpl.save(new Mouse("133", "A4Tech", "X-718F", "3Hg45ks86Gr"));
            this.mouseServiceImpl.save(new Mouse("118", "Logitech", "B100", "8Hg4DF54s4r"));
        } catch (EntityExistsException e) {}

        List<Mouse> list = this.mouseServiceImpl.listAll();
        for(Mouse m : list) {
            if(m.getInvNumber().equals("133")) {
                firstId = m.getId();
            } else if (m.getInvNumber().equals("118")) {
                secondId = m.getId();
            }
        }

    }

    @Test
    public void mouseControllerExists() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("mouseController"));
    }

    @Test
    public void testGetAllSuccess() throws Exception {

        mockMvc.perform(get("/components/mouse/"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetSuccess() throws Exception {

        mockMvc.perform(get("/components/mouse/" + firstId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.serial", is("3Hg45ks86Gr")))
                .andExpect(jsonPath("$.manufacter", is("A4Tech")))
                .andExpect(jsonPath("$.model", is("X-718F")))
                .andExpect(jsonPath("$.id", is(firstId.intValue())))
                .andExpect(jsonPath("$.invNumber", is("133")));
    }

    @Test
    public void testCreateSuccess() throws Exception {

        Mouse mous = new Mouse("171", "Razer", "deathadder", "75HdnG45K23ls");

        String d = new ObjectMapper().writeValueAsString(mous);

        mockMvc.perform(post("/components/mouse/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void testDeleteSuccess() throws Exception {

        mockMvc.perform(delete("/components/mouse/" + secondId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateSuccess() throws Exception {

        Mouse m = new Mouse("114", "Defender", "df-54012", "u4Rgd620Nc3b");

        String d = new ObjectMapper().writeValueAsString(m);


        mockMvc.perform(put("/components/mouse/" + firstId).content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }


    @Test
    public void testGetFailed() throws Exception {

        mockMvc.perform(get("/components/mouse/144444"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testCreateFailed() throws Exception {

        Mouse m = new Mouse("133", "Defender", "df-54012", "u4Rgd620Nc3b");

        String d = new ObjectMapper().writeValueAsString(m);

        mockMvc.perform(post("/components/mouse/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isConflict());


    }

    @Test
    public void testUpdateFailed() throws Exception {

        Mouse m = new Mouse("114", "Defender", "df-54012", "u4Rgd620Nc3b");

        String d = new ObjectMapper().writeValueAsString(m);

        mockMvc.perform(put("/components/mouse/144444").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDeleteFailed() throws Exception {

        mockMvc.perform(delete("/components/mouse/144444"))
                .andExpect(status().isNotFound());
    }

}

