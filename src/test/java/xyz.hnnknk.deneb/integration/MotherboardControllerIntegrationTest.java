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
import xyz.hnnknk.deneb.model.MotherBoard;
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
public class MotherboardControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private SystemUnitService<MotherBoard> motherboardServiceImpl;

    private MockMvc mockMvc;

    private Long firstId;
    private Long secondId;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        try {
            this.motherboardServiceImpl.save(new MotherBoard("ASUS", "H81M-K", "1150"));
            this.motherboardServiceImpl.save(new MotherBoard("MSI", "MZ160", "1151"));

        } catch (EntityExistsException e) {}

        for (MotherBoard motherBoard : motherboardServiceImpl.listAll()) {
            if (motherBoard.getManufacturer().equals("ASUS")) {
                firstId = motherBoard.getId();
            } else if (motherBoard.getManufacturer().equals("MSI")) {
                secondId = motherBoard.getId();
            }
        }
    }

    @Test
    public void keyboardControllerExists() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("motherBoardController"));
    }

    @Test
    public void testGetAllSuccess() throws Exception {

        mockMvc.perform(get("/sysunit/motherboard/"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetSuccess() throws Exception {

        mockMvc.perform(get("/sysunit/motherboard/" + firstId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(4)))
                .andExpect(jsonPath("$.manufacturer", is("ASUS")))
                .andExpect(jsonPath("$.model", is("H81M-K")))
                .andExpect(jsonPath("$.id", is(firstId.intValue())))
                .andExpect(jsonPath("$.socket", is("1150")));
    }

    @Test
    public void testCreateSuccess() throws Exception {

        MotherBoard motherBoard = new MotherBoard("Gigabyte", "GA-78MLT", "1150");

        String d = new ObjectMapper().writeValueAsString(motherBoard);

        mockMvc.perform(post("/sysunit/motherboard/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

    }

    @Test
    public void testDeleteSuccess() throws Exception {

        mockMvc.perform(delete("/sysunit/motherboard/" + secondId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateSuccess() throws Exception {

        MotherBoard motherBoard = new MotherBoard("ASUSSS", "H81M-K", "1150");

        String d = new ObjectMapper().writeValueAsString(motherBoard);

        mockMvc.perform(put("/sysunit/motherboard/" + firstId).content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetFailed() throws Exception {

        mockMvc.perform(get("/sysunit/motherboard/144444"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testCreateFailed() throws Exception {

        MotherBoard motherBoard = new MotherBoard("ASUS", "H81M-K", "1150");

        String d = new ObjectMapper().writeValueAsString(motherBoard);

        mockMvc.perform(post("/sysunit/motherboard/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isConflict());


    }

    @Test
    public void testUpdateFailed() throws Exception {

        MotherBoard motherBoard = new MotherBoard("ASUSSS", "H81M-K", "1150");

        String d = new ObjectMapper().writeValueAsString(motherBoard);

        mockMvc.perform(put("/sysunit/motherboard/144444").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDeleteFailed() throws Exception {

        mockMvc.perform(delete("/sysunit/motherboard/144444"))
                .andExpect(status().isNotFound());
    }

}
