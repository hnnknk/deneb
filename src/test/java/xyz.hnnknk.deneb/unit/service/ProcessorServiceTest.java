package xyz.hnnknk.deneb.unit.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xyz.hnnknk.deneb.config.WebConfig;
import xyz.hnnknk.deneb.dao.SystemUnit.ProcessorDAOImpl;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.Processor;
import xyz.hnnknk.deneb.service.SystemUnit.ProcessorServiceImpl;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class ProcessorServiceTest {

    @InjectMocks
    private ProcessorServiceImpl ProcessorServiceImpl;

    @Mock
    private ProcessorDAOImpl processorDAOImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Processor p = new Processor("Intel", "i5-3550k", "3400", 4);
        ArrayList<Processor> processors = new ArrayList<>();
        processors.add(p);

        when(processorDAOImpl.listAll()).thenReturn(processors);
        when(processorDAOImpl.findById(1L)).thenReturn(p);
    }

    @Test
    public void findByIdReturnSuccess() throws EntityNotFoundException {
        Processor p = ProcessorServiceImpl.findById(1L);
        assertNotNull(p);
        assertEquals("i5-3550k", p.getModel());
        assertEquals("Intel", p.getManufacturer());
        assertEquals("3400", p.getSpeed());
        assertEquals(Integer.valueOf(4), p.getNumberOfCores());
    }

    @Test (expected = EntityNotFoundException.class)
    public void findByIdReturnNull() throws EntityNotFoundException {
        ProcessorServiceImpl.findById(111L);
    }

    @Test
    public void saveReturnSuccess() throws EntityExistsException {
        Processor p = new Processor("Intel", "i3-6100", "3300", 2);
        ProcessorServiceImpl.save(p);
    }

    @Test (expected = EntityExistsException.class)
    public void saveReturnEntityException() throws EntityExistsException {
        Processor p = new Processor("Intel", "i5-3550k", "3400", 4);
        ProcessorServiceImpl.save(p);
    }

    @Test
    public void listAllReturnSuccess() {
        ArrayList<Processor> processors = (ArrayList<Processor>) ProcessorServiceImpl.listAll();
        assertNotNull(processors);
        assertNotNull(processors.get(0));
        assertEquals("i5-3550k", processors.get(0).getModel());
    }

    @Test
    public void listAllReturnEmpty() {
        reset(processorDAOImpl);
        ArrayList<Processor> keys = new ArrayList<>();
        when(processorDAOImpl.listAll()).thenReturn(keys);

        ArrayList<Processor> processors = (ArrayList<Processor>) ProcessorServiceImpl.listAll();
        assertNotNull(processors);
        assertTrue(processors.isEmpty());
    }

}

