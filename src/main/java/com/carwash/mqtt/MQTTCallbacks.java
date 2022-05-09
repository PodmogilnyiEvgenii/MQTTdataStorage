package com.carwash.mqtt;
import java.util.LinkedHashMap;
import org.eclipse.paho.client.mqttv3.*;
import com.carwash.postgresql.*;

public class MQTTCallbacks implements MqttCallback {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MQTTCallbacks.class);

    @Override
    public void connectionLost(Throwable throwable) {
        logger.error("Connection lost");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        logger.debug("Get message: {}", mqttMessage.toString());

        LinkedHashMap<String, String> mqttData = MQTTDataParser.parser(mqttMessage);
        if (mqttData != null) {
            if (PostgreSQLWriteCashData.write(MQTTConnect.sqlConnect, mqttData)) {
                logger.debug("Data sending to db ok");
            } else {
                logger.error("Data sending to db error");
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}
