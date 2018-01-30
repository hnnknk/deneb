package xyz.hnnknk.deneb;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import xyz.hnnknk.deneb.config.WebConfig;
import xyz.hnnknk.deneb.model.Monitor;
import xyz.hnnknk.deneb.service.MonitorService;

import javax.servlet.ServletContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class MonitorControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MonitorService monitorService;

    private MockMvc mockMvc;
    @Before
    public void setup()  {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        Monitor m1 = new Monitor();
        m1.setId(1L);
        m1.setInvNumber("144");
        m1.setManufacter("Philips");
        m1.setModel("170v");
        m1.setSerial("3Hg45ks86Gr");
        this.monitorService.save(m1);
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("monitorController"));
    }

    @Test
    public void testGetAllSuccess() throws Exception {

        mockMvc.perform(get("/components/monitor/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetSuccess() throws Exception {

        mockMvc.perform(get("/components/monitor/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.serial", is("3Hg45ks86Gr")))
                .andExpect(jsonPath("$.manufacter", is("Philips")))
                .andExpect(jsonPath("$.model", is("170v")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.invNumber", is("144")));
    }

}
