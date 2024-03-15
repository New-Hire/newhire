package com.muyu.newhire.controller;

import com.muyu.newhire.dto.AuthTokenDto;
import com.muyu.newhire.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setup() {
        try (var mocks = MockitoAnnotations.openMocks(this)) {
            mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize mocks", e);
        }
    }

    @Test
    public void testGetToken() throws Exception {
//        when(authService.getToken(anyString(), anyString())).thenReturn("token");
//
//        mockMvc.perform(post("/auth/token")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"account\":\"username\",\"password\":\"password\"}"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("token"));
//
//        verify(authService, times(1)).getToken("username", "password");
    }

    @Test
    public void testGetUser() throws Exception {
//        when(authService.getUser()).thenReturn("user");
//
//        mockMvc.perform(get("/auth/user"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("user"));
//
//        verify(authService, times(1)).getUser();
    }
}