package com.tasosmartidis.rest_api_tutorial.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.tasosmartidis.rest_api_tutorial")
public class RootConfig {

}
