package com.webhook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication // Starts my application
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
        // Spring Boot starts an embedded web server, normally on port 8080.
        
    }
}
