import org.eclipse.paho.client.mqttv3.MqttException;

public class App {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Get MQTT data and storage to PostgreSQL");
        MQTTapp mqttApp = new MQTTapp();
        mqttApp.connect();
    }
}
