package br.com.fiapmsclientess.application.config;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectBeans {

    @Bean
    public Logger logger() {
        return org.slf4j.LoggerFactory.getLogger("fiapmsclientess");
    }
}
