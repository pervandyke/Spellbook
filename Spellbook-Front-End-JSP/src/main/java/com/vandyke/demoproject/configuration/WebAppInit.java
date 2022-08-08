package com.vandyke.demoproject.configuration;

import javax.servlet.ServletContext;

import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;

public class WebAppInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        servletContext
            .addServlet("H2Console", WebServlet.class)
            .addMapping("/console/*");
    }
}
