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
import xyz.hnnknk.deneb.model.PowerSupply;
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
public class PowerSupplyControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private SystemUnitService<PowerSupply> powerSupplyServiceImpl;

    private MockMvc mockMvc;

    private Long firstId;
    private Long secondId;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        try {
            this.powerSupplyServiceImpl.save(new PowerSupply("Corsair", "450-GOLD", 450));
            this.powerSupplyServiceImpl.save(new PowerSupply("FSP", "F400DND", 400));

        } catch (EntityExistsException e) {}

        for (PowerSupply powerSupply : powerSupplyServiceImpl.listAll()) {
            if (powerSupply.getManufacturer().equals("Corsair")) {
                firstId = powerSupply.getId();
            } else if (powerSupply.getManufacturer().equals("FSP")) {
                secondId = powerSupply.getId();
            }
        }
    }

    @Test
    public void keyboardControllerExists() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("powerSupplyController"));
    }

    @Test
    public void testGetAllSuccess() throws Exception {

        mockMvc.perform(get("/sysunit/powersupply/"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetSuccess() throws Exception {

        mockMvc.perform(get("/sysunit/powersupply/" + firstId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(4)))
                .andExpect(jsonPath("$.manufacturer", is("Corsair")))
                .andExpect(jsonPath("$.model", is("450-GOLD")))
                .andExpect(jsonPath("$.id", is(firstId.intValue())))
                .andExpect(jsonPath("$.power", is(450)));
    }

    @Test
    public void testCreateSuccess() throws Exception {

        PowerSupply powerSupply = new PowerSupply("Thermalake", "600Th", 600);

        String d = new ObjectMapper().writeValueAsString(powerSupply);

        mockMvc.perform(post("/sysunit/powersupply/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

    }

    @Test
    public void testDeleteSuccess() throws Exception {

        mockMvc.perform(delete("/sysunit/powersupply/" + secondId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateSuccess() throws Exception {

        PowerSupply powerSupply = new PowerSupply("Corsairrr", "450-GOLD", 450);

        String d = new ObjectMapper().writeValueAsString(powerSupply);

        mockMvc.perform(put("/sysunit/powersupply/" + firstId).content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetFailed() throws Exception {

        mockMvc.perform(get("/sysunit/powersupply/144444"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testCreateFailed() throws Exception {

        PowerSupply powerSupply = new PowerSupply("Corsair", "450-GOLD", 450);

        String d = new ObjectMapper().writeValueAsString(powerSupply);

        mockMvc.perform(post("/sysunit/powersupply/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isConflict());


    }

    @Test
    public void testUpdateFailed() throws Exception {

        PowerSupply powerSupply = new PowerSupply("Corsair", "450-GOLD", 450);

        String d = new ObjectMapper().writeValueAsString(powerSupply);

        mockMvc.perform(put("/sysunit/powersupply/144444").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDeleteFailed() throws Exception {

        mockMvc.perform(delete("/sysunit/powersupply/144444"))
                .andExpect(status().isNotFound());
    }

}
