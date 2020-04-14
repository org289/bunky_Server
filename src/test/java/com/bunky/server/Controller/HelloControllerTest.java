package com.bunky.server.Controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(HelloController.class)
class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void helloTest() {
        HelloController controller = new HelloController(); // Arrange
        String response = controller.helloTest(); // Act
        assertEquals("My Test is working!", response); // Assert
    }

    @Test
    void hello() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/hello/Amy");
            mvc.perform(request).andExpect(content().string ("Hello Amy!"));

        }
}