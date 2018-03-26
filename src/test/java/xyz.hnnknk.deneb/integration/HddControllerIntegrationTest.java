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
import xyz.hnnknk.deneb.model.HDD;
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
public class HddControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private SystemUnitService<HDD> hddServiceImpl;

    private MockMvc mockMvc;

    private Long firstId;
    private Long secondId;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        try {
            this.hddServiceImpl.save(new HDD("Toshiba", "DA150ATA", "sn33422fht", 500, "HDD"));
            this.hddServiceImpl.save(new HDD("Samsung", "850EVO", "sn483hnf45hf", 256, "SSD"));

        } catch (EntityExistsException e) {}

        for (HDD hdd : hddServiceImpl.listAll()) {
            if (hdd.getSerial().equals("sn33422fht")) {
                firstId = hdd.getId();
            } else if (hdd.getSerial().equals("sn483hnf45hf")) {
                secondId = hdd.getId();
            }
        }
    }

    @Test
    public void keyboardControllerExists() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("HDDController"));
    }

    @Test
    public void testGetAllSuccess() throws Exception {

        mockMvc.perform(get("/sysunit/hdd/"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetSuccess() throws Exception {

        mockMvc.perform(get("/sysunit/hdd/" + firstId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(6)))
                .andExpect(jsonPath("$.serial", is("sn33422fht")))
                .andExpect(jsonPath("$.manufacturer", is("Toshiba")))
                .andExpect(jsonPath("$.model", is("DA150ATA")))
                .andExpect(jsonPath("$.id", is(firstId.intValue())))
                .andExpect(jsonPath("$.hddType", is("HDD")))
                .andExpect(jsonPath("$.capacity", is(500)));
    }

    @Test
    public void testCreateSuccess() throws Exception {

        HDD hdd = new HDD("Plextor", "M6PRO", "sn43fmd4587f", 128,"SSD");

        String d = new ObjectMapper().writeValueAsString(hdd);

        mockMvc.perform(post("/sysunit/hdd/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

    }

    @Test
    public void testDeleteSuccess() throws Exception {

        mockMvc.perform(delete("/sysunit/hdd/" + secondId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateSuccess() throws Exception {

        HDD hdd = new HDD("Toshiba", "DA150ATA", "sn33422fht", 1000, "HDD");

        String d = new ObjectMapper().writeValueAsString(hdd);

        mockMvc.perform(put("/sysunit/hdd/" + firstId).content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetFailed() throws Exception {

        mockMvc.perform(get("/sysunit/hdd/144444"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testCreateFailed() throws Exception {

        HDD hdd = new HDD("Toshiba", "DA150ATA", "sn33422fht", 500, "HDD");

        String d = new ObjectMapper().writeValueAsString(hdd);

        mockMvc.perform(post("/sysunit/hdd/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isConflict());


    }

    @Test
    public void testUpdateFailed() throws Exception {

        HDD hdd = new HDD("Toshiba", "DA150ATA", "sn33422fht", 500, "HDD");

        String d = new ObjectMapper().writeValueAsString(hdd);

        mockMvc.perform(put("/sysunit/hdd/144444").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDeleteFailed() throws Exception {

        mockMvc.perform(delete("/sysunit/hdd/144444"))
                .andExpect(status().isNotFound());
    }

}
