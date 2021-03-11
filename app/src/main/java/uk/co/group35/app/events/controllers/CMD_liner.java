package uk.co.group35.app.events.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Class that adds to the database
 */
@Component
public class CMD_liner implements CommandLineRunner {

    private DbDriver DBdriver;

    public CMD_liner(DbDriver DBDriver) {
        this.DBdriver = DBDriver;
    }

    @Override
    public void run(String... args) throws Exception {



    }

}
