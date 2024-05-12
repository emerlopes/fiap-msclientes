package br.com.fiapmsclientess.application.config;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InjectBeansTest {

    @InjectMocks
    InjectBeans injectBeans;

    @Test
    void logger() {
        assertNotNull(injectBeans.logger());
    }

}