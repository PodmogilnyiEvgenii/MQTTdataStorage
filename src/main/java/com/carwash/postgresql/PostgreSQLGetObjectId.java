package com.carwash.postgresql;

import java.sql.*;
import java.util.LinkedHashMap;


public class PostgreSQLGetObjectId {
    private Connection dbConnection = null;
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PostgreSQLGetObjectId.class);

    public static LinkedHashMap<String, Integer> getCarwashIdMap(Connection sqlConnect) {
        LinkedHashMap<String, Integer> carwashMap = new LinkedHashMap<>();
        try {
            PreparedStatement request = sqlConnect.prepareStatement("SELECT * FROM carwash");
            ResultSet resultSet = request.executeQuery();
            while (resultSet.next()) {
                carwashMap.put(resultSet.getString("carwash_num"), resultSet.getInt("carwash_id"));
            }
        } catch (SQLException e) {
            logger.error("Get carwash id error: {}", e.toString());
        }
        return carwashMap;
    }

    public static LinkedHashMap<Integer, Integer> getOwnerIdMap(Connection sqlConnect) {
        LinkedHashMap<Integer, Integer> ownerMap = new LinkedHashMap<>();
        try {
            PreparedStatement request = sqlConnect.prepareStatement("SELECT * FROM carwash");
            ResultSet resultSet = request.executeQuery();
            while (resultSet.next()) {
                ownerMap.put(resultSet.getInt("carwash_id"), resultSet.getInt("owner_id"));
            }
        } catch (SQLException e) {
            logger.error("Get owner id error: {}", e.toString());
        }
        return ownerMap;
    }
}
