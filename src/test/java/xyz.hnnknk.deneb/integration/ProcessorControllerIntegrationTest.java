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
import xyz.hnnknk.deneb.model.Processor;
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
public class ProcessorControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private SystemUnitService<Processor> processorServiceImpl;

    private MockMvc mockMvc;

    private Long firstId;
    private Long secondId;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        try {
            this.processorServiceImpl.save(new Processor("Intel", "i5-3500k", "3500", 4));
            this.processorServiceImpl.save(new Processor("AMD", "Ryzen 5", "3300", 6));

        } catch (EntityExistsException e) {}

        for (Processor processor : processorServiceImpl.listAll()) {
            if (processor.getManufacturer().equals("Intel")) {
                firstId = processor.getId();
            } else if (processor.getManufacturer().equals("AMD")) {
                secondId = processor.getId();
            }
        }
    }

    @Test
    public void keyboardControllerExists() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("processorController"));
    }

    @Test
    public void testGetAllSuccess() throws Exception {

        mockMvc.perform(get("/sysunit/processor/"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetSuccess() throws Exception {

        mockMvc.perform(get("/sysunit/processor/" + firstId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.speed", is("3500")))
                .andExpect(jsonPath("$.manufacturer", is("Intel")))
                .andExpect(jsonPath("$.model", is("i5-3500k")))
                .andExpect(jsonPath("$.id", is(firstId.intValue())))
                .andExpect(jsonPath("$.numberOfCores", is(4)));
    }

    @Test
    public void testCreateSuccess() throws Exception {

        Processor processor = new Processor("Intell", "i7-6100", "3500", 8);

        String d = new ObjectMapper().writeValueAsString(processor);

        mockMvc.perform(post("/sysunit/processor/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

    }

    @Test
    public void testDeleteSuccess() throws Exception {

        mockMvc.perform(delete("/sysunit/processor/" + secondId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateSuccess() throws Exception {

        Processor processor = new Processor("Intelll", "i5-3500k", "3500", 4);

        String d = new ObjectMapper().writeValueAsString(processor);

        mockMvc.perform(put("/sysunit/processor/" + firstId).content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetFailed() throws Exception {

        mockMvc.perform(get("/sysunit/processor/144444"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testCreateFailed() throws Exception {

        Processor processor = new Processor("Intel", "i5-3500k", "3500", 4);

        String d = new ObjectMapper().writeValueAsString(processor);

        mockMvc.perform(post("/sysunit/processor/").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isConflict());


    }

    @Test
    public void testUpdateFailed() throws Exception {

        Processor processor = new Processor("Intel", "i5-3500k", "3500", 4);

        String d = new ObjectMapper().writeValueAsString(processor);

        mockMvc.perform(put("/sysunit/processor/144444").content(d).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDeleteFailed() throws Exception {

        mockMvc.perform(delete("/sysunit/processor/144444"))
                .andExpect(status().isNotFound());
    }

}
