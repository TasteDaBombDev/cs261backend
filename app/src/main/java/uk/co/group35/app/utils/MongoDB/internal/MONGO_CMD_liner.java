package uk.co.group35.app.utils.MongoDB.internal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Class that adds to the database
 */
@Component
public class MONGO_CMD_liner implements CommandLineRunner {

    private MongoDB_Driver DBdriver;

    public MONGO_CMD_liner(MongoDB_Driver MongoDBDriver) {
        this.DBdriver = MongoDBDriver;
    }

    @Override
    public void run(String... args) throws Exception {



    }

}
