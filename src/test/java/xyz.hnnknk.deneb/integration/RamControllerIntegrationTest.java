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
import xyz.hnnknk.deneb.model.RAM;
import xyz.hnnknk.deneb.model.RAM;
import xyz.hnnknk.deneb.service.SystemUnit.SystemUnitService;

import javax.servlet.ServletContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class RamControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private SystemUnitService<RAM> ramServiceImpl;

    private MockMvc mockMvc;

    private Long firstId;
    private Long secondId;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        try {
            this.ramServiceImpl.save(new RAM("Corsair", "c473hdb3/2",4096));
            this.ramServiceImpl.save(new RAM("Kingston", "kn39hdn/2",2048));

        } catch (EntityExistsException e) {}

        for (RAM ram : ramServiceImpl.listAll()) {
            if (ram.getManufacturer().equals("Corsair")) {
                firstId = ram.getId();
            } else if (ram.getManufacturer().equals("Kingston")) {
                secondId = ram.getId();
            }
        }
    }

    @Test
    public void keyboardControllerExists() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("RAMController"));
    }

    @Test
    public void testGetAllSuccess() throws Exception {

        mockMvc.perform(get("/sysunit/ram/"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetSuccess() throws Exception {

        mockMvc.perform(get("/sysunit/ram/" + firstId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(4)))
                .andExpect(jsonPath("$.manufacturer", is("Corsair")))
                .andExpect(jsonPath("$.model", is("c473hdb3/2")))
                .andExpect(jsonPath("$.id", is(firstId.intValue())))
                .andExpect(jsonPath("$.capacity", is(4096)));
    }

    @Test
    public void testCreateSuccess() throws Exception {

        RAM ram = new RAM("Hynix", "hy38dns/2", 2048);

        String d = new ObjectMapper().writeValueAsString(ram);

        mockMvc.perform(post("/sysunit/ram/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

    }

    @Test
    public void testDeleteSuccess() throws Exception {

        mockMvc.perform(delete("/sysunit/ram/" + secondId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateSuccess() throws Exception {

        RAM ram = new RAM("Corsairrr", "c473hdb3/2",4096);

        String d = new ObjectMapper().writeValueAsString(ram);

        mockMvc.perform(put("/sysunit/ram/" + firstId).content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetFailed() throws Exception {

        mockMvc.perform(get("/sysunit/ram/144444"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testCreateFailed() throws Exception {

        RAM ram = new RAM("Corsair", "c473hdb3/2",4096);

        String d = new ObjectMapper().writeValueAsString(ram);

        mockMvc.perform(post("/sysunit/ram/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isConflict());


    }

    @Test
    public void testUpdateFailed() throws Exception {

        RAM ram = new RAM("Corsairrr", "c473hdb3/2",4096);

        String d = new ObjectMapper().writeValueAsString(ram);

        mockMvc.perform(put("/sysunit/ram/144444").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDeleteFailed() throws Exception {

        mockMvc.perform(delete("/sysunit/ram/144444"))
                .andExpect(status().isNotFound());
    }

}
