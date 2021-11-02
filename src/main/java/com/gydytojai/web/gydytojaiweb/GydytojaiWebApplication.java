package com.gydytojai.web.gydytojaiweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // scan for components in current package and below
public class GydytojaiWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GydytojaiWebApplication.class, args);
    }
}
