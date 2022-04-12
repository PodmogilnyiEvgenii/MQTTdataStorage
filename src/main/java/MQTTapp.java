import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;

public class MQTTapp {
    private final String MQTT_SERVER = "tcp://srv2.clusterfly.ru";
    private final int MQTT_PORT = 9991;
    private final String MQTT_CLIENT_ID = "ClientDB";
    private final String MQTT_LOGIN = "user_e26b81e5";
    private final String MQTT_PASS = "pass_3a57aa79";
    private final String MQTT_TOPIC1 = "user_e26b81e5/uptime";
    private final String MQTT_TOPIC2 = "user_e26b81e5/test";
    private final PostgreSQLapp sqlApp = new PostgreSQLapp();
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MQTTapp.class);

    public MQTTapp() {
        sqlApp.connect();
    }


    public void connect() {
        try {
            IMqttClient mqttClient = new MqttClient(MQTT_SERVER + ":" + MQTT_PORT, MQTT_CLIENT_ID);
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setAutomaticReconnect(true);
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setConnectionTimeout(5);
            mqttConnectOptions.setUserName(MQTT_LOGIN);
            mqttConnectOptions.setPassword(MQTT_PASS.toCharArray());

            mqttClient.setCallback(new MQTTCallback());
            mqttClient.connect(mqttConnectOptions);
            mqttClient.subscribe(MQTT_TOPIC1);
            mqttClient.subscribe(MQTT_TOPIC2);

        } catch (MqttException ex) {
            logger.error("Connection MQTT error: {}",ex.toString());
        }
    }

    private class MQTTCallback implements MqttCallback {
        @Override
        public void connectionLost(Throwable throwable) {
            logger.error("Connection lost");
        }

        @Override
        public void messageArrived(String s, MqttMessage mqttMessage)  {
            logger.debug("Get message: {}",mqttMessage.toString());
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode mqttData = objectMapper.readTree(mqttMessage.toString());

                if (mqttData.get("uptime").asText() != null || mqttData.get("random").asText() != null) {
                    if (sqlApp.sendData(mqttData.get("uptime").asText(), mqttData.get("random").asText())) {
                    } else {
                        logger.error("Data sending to db error");
                    }
                }
            } catch(Exception ex){
                logger.error("Json error");
            }
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        }
    }
}
