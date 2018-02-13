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
import xyz.hnnknk.deneb.model.Monitor;
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
public class MonitorControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PeripheralService monitorServiceImpl;

    private MockMvc mockMvc;

    private Long firstId;
    private Long secondId;

    @Before
    public void setup()  {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.monitorServiceImpl.save(new Monitor("144", "Philips", "170v", "3Hg45ks86Gr"));
        this.monitorServiceImpl.save(new Monitor("155", "Philips", "170v", "783G6r45TNe"));

        List<Monitor> list = this.monitorServiceImpl.listAll();

        for(Monitor m : list) {
            if(m.getInvNumber().equals("144")) {
                firstId = m.getId();
            } else if (m.getInvNumber().equals("155")) {
                secondId = m.getId();
            }
        }

    }

    @Test
    public void monitorControllerExists() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("monitorController"));
    }

    @Test
    public void testGetAllSuccess() throws Exception {

        mockMvc.perform(get("/components/monitor/"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetSuccess() throws Exception {

        mockMvc.perform(get("/components/monitor/" + firstId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.serial", is("3Hg45ks86Gr")))
                .andExpect(jsonPath("$.manufacter", is("Philips")))
                .andExpect(jsonPath("$.model", is("170v")))
                .andExpect(jsonPath("$.id", is(firstId.intValue())))
                .andExpect(jsonPath("$.invNumber", is("144")));
    }

    @Test
    public void testCreateSuccess() throws Exception {

        Monitor m = new Monitor("131", "Samsung", "7200p", "75HdnG45K23ls");

        String d = new ObjectMapper().writeValueAsString(m);

        mockMvc.perform(post("/components/monitor/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void testDeleteSuccess() throws Exception {

        mockMvc.perform(delete("/components/monitor/" + secondId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateSuccess() throws Exception {

        Monitor m = new Monitor("144", "Acer", "v2543", "u4Rgd620Nc3b");

        String d = new ObjectMapper().writeValueAsString(m);

        mockMvc.perform(put("/components/monitor/" + firstId).content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetFailed() throws Exception {

        mockMvc.perform(get("/components/monitor/144444"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testCreateFailed() throws Exception {

        Monitor m = new Monitor("144", "Samsung", "7200p", "75HdnG45K23ls");

        String d = new ObjectMapper().writeValueAsString(m);

        mockMvc.perform(post("/components/monitor/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isConflict());


    }

    @Test
    public void testUpdateFailed() throws Exception {

        Monitor m = new Monitor("144", "Acer", "v2543", "u4Rgd620Nc3b");

        String d = new ObjectMapper().writeValueAsString(m);

        mockMvc.perform(put("/components/monitor/144444").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDeleteFailed() throws Exception {

        mockMvc.perform(delete("/components/monitor/144444"))
                .andExpect(status().isNotFound());
    }

}
