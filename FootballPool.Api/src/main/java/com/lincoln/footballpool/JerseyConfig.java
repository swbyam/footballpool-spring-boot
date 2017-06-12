package com.lincoln.footballpool;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import com.lincoln.footballpool.api.controllers.GamesController;

import javax.ws.rs.ApplicationPath;

@Configuration
//Controls the default mapping of the Jersey servlet!
@ApplicationPath("/footballpool")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(GamesController.class);
    }
}
