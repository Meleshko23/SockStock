package com.example.sockstock.service;

import com.example.sockstock.repository.SockStockRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SockStockServiceTest {

    @Mock
    private SockStockRepository sockStockRepository;

    @InjectMocks
    private SockStockService sockStockService;

}