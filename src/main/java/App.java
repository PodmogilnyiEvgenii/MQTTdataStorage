import org.eclipse.paho.client.mqttv3.MqttException;

public class App {
    public static void main(String[] args) {
        System.out.println("Get MQTT data and storage to PostgreSQL");

        MQTTapp mqttApp = new MQTTapp();
        mqttApp.connect();
    }
}
