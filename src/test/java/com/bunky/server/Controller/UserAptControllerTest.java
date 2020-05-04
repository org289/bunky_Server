package com.bunky.server.Controller;

import com.bunky.server.Dao.UserAptDao;
import com.bunky.server.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

//@ExtendWith((SpringExtension.class))
@WebMvcTest(UserAptController.class)
class UserAptControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    UserRepo userRepo;

    @MockBean
    UserAptDao userAptDao;


//    @Test
//    void loginUser() throws Exception {
//        // isn't complete yet
//
//        RequestBuilder request = MockMvcRequestBuilders.get("/loginUser/amypariz@gmail.com");
//        mvc.perform(request).andExpect(content().string("2"));
//
////        MvcResult result = mvc.perform(request).andReturn();
////        assertEquals("2", result.getResponse().getContentAsString());
//
//    }

    @Test
    void getAllUsers() throws Exception {
        // UNIT-TEST
        Mockito.when(userAptDao.getAllUsers()).thenReturn(Collections.emptyList());
        RequestBuilder request = MockMvcRequestBuilders.get("/users");
        MvcResult result = mvc.perform(request).andReturn();

        System.out.println("The result is: " + result);

        Mockito.verify(userAptDao).getAllUsers();


    }
}
