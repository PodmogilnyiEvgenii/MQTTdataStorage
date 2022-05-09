package com.carwash.postgresql;

import java.sql.*;
import java.sql.Timestamp;
import java.util.LinkedHashMap;


public class PostgreSQLWriteCashData {
    private Connection dbConnection = null;
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PostgreSQLWriteCashData.class);

    public static boolean write(Connection sqlConnect, LinkedHashMap<String, String> mqttData) {
        boolean status = true;
        if (PostgreSQLObjectId.carwashMap.get(mqttData.get("CarWashId"))==null)
        {
            logger.error("Carwash id not found");
            status = false;
        } else{
            try {
                PreparedStatement request = sqlConnect.prepareStatement("INSERT INTO status_cash (data_time, owner_id, carwash_id, work_status, post_status, cash_income, bank_income) VALUES (?, ?, ?, ?, ?, ?, ?)");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                request.setTimestamp(1, timestamp);
                request.setInt(2, PostgreSQLObjectId.ownerMap.get(PostgreSQLObjectId.carwashMap.get(mqttData.get("CarWashId"))));
                request.setInt(3, PostgreSQLObjectId.carwashMap.get(mqttData.get("CarWashId")));
                request.setString(4, mqttData.get("WorkStatus"));
                request.setString(5, mqttData.get("PostStatus"));
                request.setInt(6, Integer.parseInt(mqttData.get("CashIncome")));
                request.setInt(7, Integer.parseInt(mqttData.get("BankIncome")));
                request.executeUpdate();
            } catch (SQLException e) {
                logger.error("DB error: {}", e.toString());
                status = false;
            }
        }
        return status;
    }
}
