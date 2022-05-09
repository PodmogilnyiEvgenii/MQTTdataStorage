package com.carwash.postgresql;
import java.sql.*;
import java.util.LinkedHashMap;


public class PostgreSQLConnect {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PostgreSQLConnect.class);

    public Connection connect(LinkedHashMap<String, String> optionsMap) {
        Connection dbConnection = null;
        try {
            dbConnection = DriverManager.getConnection(optionsMap.get("DB_URL"), optionsMap.get("DB_USER"), optionsMap.get("DB_PASS"));
            logger.info("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            logger.error("Connection PostgreSQL error:  {}", e.toString());
            return null;
        }
        return dbConnection;
    }
}
