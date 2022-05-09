package com.carwash.postgresql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;


public class PostgreSQLisValidId {
    private Connection dbConnection = null;
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PostgreSQLisValidId.class);

    public static boolean isValid(Connection sqlConnect, String objectId) {
        boolean status = true;
        /*
        try {

            PreparedStatement request = dbConnection.prepareStatement("SELECT * FROM uptime ORDER BY time DESC LIMIT 10;");
            ResultSet dataFromDb = request.executeQuery();
            while (dataFromDb.next()) {
                System.out.print(dataFromDb.getString(1));
                System.out.print(": ");
                System.out.println(dataFromDb.getString(2));
            }*/
            /*
            PreparedStatement request = sqlConnect.prepareStatement("INSERT INTO uptime (time, message) VALUES (?, ?)");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            request.setTimestamp(1, timestamp);
            request.setString(2, data1 + " - " + data2);
            request.executeUpdate();
        } catch (SQLException ex) {
            logger.error("DB error: {}", ex.toString());
            status = false;
        }
*/
        return status;
    }
}
