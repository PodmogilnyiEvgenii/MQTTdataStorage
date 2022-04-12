import java.sql.*;
import java.sql.Timestamp;


public class PostgreSQLapp {
    private final String DB_URL = "jdbc:postgresql://localhost:5432/mqtt_db";
    private final String DB_USER = "mqttuser";
    private final String DB_PASS = "mqttuser";
    private Connection dbConnection = null;
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PostgreSQLapp.class);

    public PostgreSQLapp() {
    }

    public boolean sendData(String data1, String data2) {
        boolean status = true;
        try {
            /*
            PreparedStatement request = dbConnection.prepareStatement("SELECT * FROM uptime ORDER BY time DESC LIMIT 10;");
            ResultSet dataFromDb = request.executeQuery();
            while (dataFromDb.next()) {
                System.out.print(dataFromDb.getString(1));
                System.out.print(": ");
                System.out.println(dataFromDb.getString(2));
            }*/
            PreparedStatement request = dbConnection.prepareStatement("INSERT INTO uptime (time, message) VALUES (?, ?)");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            request.setTimestamp(1, timestamp);
            request.setString(2, data1 +" - " +data2);
            request.executeUpdate();
        } catch (SQLException ex) {
            logger.error("DB error: {}",ex.toString());
            status = false;
        }

        return status;
    }

    public void connect() {
        try {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            logger.info("Connected to the PostgreSQL server successfully.");
        } catch (SQLException ex) {
            logger.error("Connection PostgreSQL error:  {}",ex.toString());
        }
    }
}
