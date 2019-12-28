package com.jabirinc.jabirincpetclinic.controllers;

import com.jabirinc.jabirincpetclinic.model.Owner;
import com.jabirinc.jabirincpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

/**
 *      ==== How to write correct unit test for controllers ====
 * While writing junit test for a controller method, we shall keep in mind that:
 *
 *      - A unit test is supposed to test only a certain part of code (i.e. code written in controller class),
 *      so we shall mock all the dependencies injected and used in controller class.
 *      - If the test utilizes other dependencies (e.g. database/network) then it is integration testing and not
 *      unit testing.
 *      - We should not use any webserver otherwise it will make the unit testing slow.
 *      - Each unit test should be independent of other tests.
 *      - By definition, unit tests should be fast.
 *
 *
 * @ExtendWith(MockitoExtension.class) – [ Junit 5 ]
 * MockitoExtension initializes mocks and handles strict stubbings. It is equivalent of the MockitoJUnitRunner [Junit 4].
 *
 * @Mock is used for mock creation. It makes the test class more readable. In test class, to process mockito
 * annotations, MockitoAnnotations.initMocks(testClass) must be used at least once.
 * If you are using  ExtendWith(MockitoExtension.class)/RunWith(MockitoJUnitRunner.class) then explicit usage of
 * MockitoAnnotations.initMocks() is not necessary.
 * Mocks are initialized before each test method. Use @Mock in unit testing where spring test context is not needed.
 *
 * @InjectMocks also creates the mock implementation, additionally injects the dependent mocks that are marked with
 * the annotations @Mock into it.
 *
 * MockMVC
 * MockMVC class is part of Spring MVC test framework which helps in testing the controllers explicitly starting
 * a Servlet container.
 *
 * @WebMvcTest annotation is used for Spring MVC tests. It disables full auto-configuration and instead apply only
 * configuration relevant to MVC tests. The WebMvcTest annotation auto-configure MockMvc instance as well.
 * For example:
 *      @WebMvcTest(OwnerController.class)
 *      ---
 *      @Autowired
 *      MockMvc mockMvc;
 *      ---
 *
 * Using OwnerController.class as parameter, we are asking to initialize only one web controller and you need to
 * provide remaining dependencies required using Mock objects.
 *
 */
@ExtendWith(MockitoExtension.class)
//@WebMvcTest(OwnerController.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    MockMvc mockMvc;

    Set<Owner> owners;

    @BeforeEach
    void setUp() {

        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();

        //alternate for OwnerService Mock annotation
        //ownerService = mock(OwnerService.class);
    }

    /**
     * mockMvc.andDo(print()) will print the request and response. This is helpful to get detailed view in
     * case of error
     * @throws Exception
     */
    @Test
    void findOwners() throws Exception {

        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyNoMoreInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {

        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1L).build(), Owner.builder().id(2L).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attributeExists("selections"))
                .andExpect(model().attribute("selections", hasSize(2)));

        verifyNoMoreInteractions(ownerService);
    }

    @Test
    void processFindFormReturnOne() throws Exception {

        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1L).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verifyNoMoreInteractions(ownerService);
    }

    @Test
    void showOwner() throws Exception {

        Owner owner = Owner.builder().id(1L).build();
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owners/" + owner.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }
}