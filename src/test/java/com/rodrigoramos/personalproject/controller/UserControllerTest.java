package com.rodrigoramos.personalproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.service.UserServiceImpl;
import com.rodrigoramos.personalproject.service.interfaces.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    UserServiceImpl userService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void teasdas() throws Exception {

        


    }
}
