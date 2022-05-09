package com.carwash.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import java.sql.Connection;
import java.util.HashMap;

public class MQTTConnect {
    public MqttClient mqttClient = null;
    public static Connection sqlConnect = null;

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MQTTConnect.class);

    public void connect(HashMap<String, String> optionsMap, Connection sqlConnect) /*throws MqttException*/ {
        this.sqlConnect = sqlConnect;
        try {
            mqttClient = new MqttClient(optionsMap.get("MQTT_SERVER") + ":" + optionsMap.get("MQTT_PORT"), optionsMap.get("MQTT_CLIENT_ID"));
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setAutomaticReconnect(true);
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setConnectionTimeout(5);
            mqttConnectOptions.setUserName(optionsMap.get("MQTT_LOGIN"));
            mqttConnectOptions.setPassword(optionsMap.get("MQTT_PASS").toCharArray());

            mqttClient.setCallback(new MQTTCallbacks());
            mqttClient.connect(mqttConnectOptions);

            do {  } while (!mqttClient.isConnected());

            mqttClient.subscribe(optionsMap.get("MQTT_TOPIC1"));
            mqttClient.subscribe(optionsMap.get("MQTT_TOPIC2"));
        } catch (MqttException e) {
            logger.error("MQTT connecting error: {}", e.toString());
        }
    }

}
