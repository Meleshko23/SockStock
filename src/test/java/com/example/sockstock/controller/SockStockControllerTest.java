package com.example.sockstock.controller;

import com.example.sockstock.repository.SockStockRepository;
import com.example.sockstock.service.SockStockService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SockStockController.class)
public class SockStockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SockStockService sockStockService;

    @MockBean
    private SockStockRepository sockStockRepository;

    @InjectMocks
    private SockStockController sockStockController;

    @Test
    public void testIncomeSocks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"color\":\"red\",\"cottonPart\":80,\"quantity\":10}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testOutcomeSocks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"color\":\"red\",\"cottonPart\":80,\"quantity\":10}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAllSocks() throws Exception {
        mockMvc.perform(get("/api/socks?color=red&operation=MORE&cottonPart=80"))
                .andExpect(status().isOk());
    }
}

