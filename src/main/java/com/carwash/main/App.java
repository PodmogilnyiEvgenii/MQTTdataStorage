package com.carwash.main;

import com.carwash.mqtt.MQTTConnect;
import com.carwash.postgresql.FlywayMigrations;
import com.carwash.options.OptionsGet;
import com.carwash.postgresql.PostgreSQLStart;


import java.sql.Connection;

public class App {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(App.class);
    public static Connection postgreSQLConnection=null;

    public static void main(String[] args) {
        logger.info("Get MQTT data and storage to PostgreSQL");

        OptionsGet.getOptions();
        FlywayMigrations.makeStructure(OptionsGet.optionsMap);

        PostgreSQLStart.start();



        MQTTConnect mqttConnect = new MQTTConnect();
        mqttConnect.connect(OptionsGet.optionsMap, postgreSQLConnection);
        logger.info("Run complete");
    }
}
