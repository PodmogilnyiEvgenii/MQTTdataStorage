import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.core.JsonProcessingException;


import org.eclipse.paho.client.mqttv3.*;

public class MQTTapp {
    private String mqttServer = "tcp://srv2.clusterfly.ru";
    private int mqttPort = 9991;
    private String mqttClientId = "ClientDB";
    private String mqttLogin = "user_e26b81e5";
    private String mqttPass = "pass_3a57aa79";
    private String mqttTopic1 = "user_e26b81e5/uptime";
    private String mqttTopic2 = "user_e26b81e5/test";
    private PostgreSQLapp sqlApp = new PostgreSQLapp();;

    public MQTTapp() {
        sqlApp.connect();
    }


    public void connect() {
        try {
            IMqttClient mqttClient = new MqttClient(mqttServer + ":" + String.valueOf(mqttPort), mqttClientId);
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setAutomaticReconnect(true);
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setConnectionTimeout(5);
            mqttConnectOptions.setUserName(mqttLogin);
            mqttConnectOptions.setPassword(mqttPass.toCharArray());

            mqttClient.setCallback(new MQTTCallback());
            mqttClient.connect(mqttConnectOptions);
            mqttClient.subscribe(mqttTopic1);
            mqttClient.subscribe(mqttTopic2);

        } catch (MqttException ex) {
            //System.out.println(ex.toString());
            System.out.println("Connection MQTT error: " + ex.toString());
        }
    }

    public class MQTTCallback implements MqttCallback {
        @Override
        public void connectionLost(Throwable throwable) {
            System.out.println("Connection lost...");
        }

        @Override
        public void messageArrived(String s, MqttMessage mqttMessage)  {
            System.out.print("Get message: " + mqttMessage.toString());
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode mqttData = objectMapper.readTree(mqttMessage.toString());

                if (mqttData.get("uptime").asText() != null || mqttData.get("random").asText() != null) {
                    if (sqlApp.sendData(mqttData.get("uptime").asText(), mqttData.get("random").asText())) {
                        System.out.println(" data send ok");
                    } else {
                        System.out.println(" data send error");
                    }
                }
            } catch(Exception ex){
                System.out.println(" json error");
            }
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        }
    }
}
