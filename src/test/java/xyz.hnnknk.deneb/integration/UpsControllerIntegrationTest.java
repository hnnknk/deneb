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
import xyz.hnnknk.deneb.model.Ups;
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
public class UpsControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PeripheralService upsServiceImpl;

    private MockMvc mockMvc;

    private Long firstId;
    private Long secondId;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        try {
            this.upsServiceImpl.save(new Ups("133", "APC", "CS500", "3Hg45ks86Gr"));
            this.upsServiceImpl.save(new Ups("118", "APC", "CS500", "8Hg4DF54s4r"));
        } catch (EntityExistsException e) {}

        List<Ups> list = this.upsServiceImpl.listAll();

        for(Ups u : list) {
            if(u.getInvNumber().equals("133")) {
                firstId = u.getId();
            } else if (u.getInvNumber().equals("118")) {
                secondId = u.getId();
            }
        }
    }

    @Test
    public void upsControllerExists() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("upsController"));
    }

    @Test
    public void testGetAllSuccess() throws Exception {

        mockMvc.perform(get("/components/ups/"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetSuccess() throws Exception {

        mockMvc.perform(get("/components/ups/" + firstId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.serial", is("3Hg45ks86Gr")))
                .andExpect(jsonPath("$.manufacturer", is("APC")))
                .andExpect(jsonPath("$.model", is("CS500")))
                .andExpect(jsonPath("$.id", is(firstId.intValue())))
                .andExpect(jsonPath("$.invNumber", is("133")));
    }

    @Test
    public void testCreateSuccess() throws Exception {

        Ups m = new Ups("171", "Ippon", "I4000", "75HdnG45K23ls");

        String d = new ObjectMapper().writeValueAsString(m);

        mockMvc.perform(post("/components/ups/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void testDeleteSuccess() throws Exception {

        mockMvc.perform(delete("/components/ups/" + secondId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateSuccess() throws Exception {

        Ups m = new Ups("114", "Ippon", "I3500", "u4Rgd620Nc3b");

        String d = new ObjectMapper().writeValueAsString(m);


        mockMvc.perform(put("/components/ups/" + firstId).content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }


    @Test
    public void testGetFailed() throws Exception {

        mockMvc.perform(get("/components/ups/144444"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testCreateFailed() throws Exception {

        Ups m = new Ups("133", "Ippon", "I3500", "u4Rgd620Nc3b");

        String d = new ObjectMapper().writeValueAsString(m);

        mockMvc.perform(post("/components/ups/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isConflict());


    }

    @Test
    public void testUpdateFailed() throws Exception {

        Ups m = new Ups("171", "Ippon", "I4000", "75HdnG45K23ls");

        String d = new ObjectMapper().writeValueAsString(m);

        mockMvc.perform(put("/components/ups/144444").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDeleteFailed() throws Exception {

        mockMvc.perform(delete("/components/ups/144444"))
                .andExpect(status().isNotFound());
    }

}

