package com.carwash.postgresql;

import org.flywaydb.core.Flyway;

import java.util.LinkedHashMap;

public class FlywayMigrations {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(FlywayMigrations.class);

    public static void makeStructure(LinkedHashMap<String, String> optionsMap) {
        var flyway = Flyway.configure()
                .dataSource(optionsMap.get("DB_URL"), optionsMap.get("DB_USER"), optionsMap.get("DB_PASS"))
                .locations("filesystem:./src/main/resources")
                .baselineOnMigrate(true)
                .load();
        flyway.migrate();
        logger.info("Flyway migration finished");
    }
}
