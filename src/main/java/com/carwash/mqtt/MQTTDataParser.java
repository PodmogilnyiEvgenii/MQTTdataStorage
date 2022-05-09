package com.carwash.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.LinkedHashMap;
import java.util.Map;

public class MQTTDataParser {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MQTTDataParser.class);

    public static LinkedHashMap<String, String> parser(MqttMessage mqttMessage) {
        LinkedHashMap<String, String> mqttData = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            mqttData = objectMapper.readValue(mqttMessage.toString(), LinkedHashMap.class);
            /*
            logger.debug("Parsed data ========================");
            for (Map.Entry<String, String> entry : mqttData.entrySet()) {
                logger.debug("{} = {}",entry.getKey(),entry.getValue());
            }
            logger.debug("Parsed data ========================");
            */
        } catch (Exception e) {
            logger.error("Json error");
            return null;
        }
        return mqttData;

    }
}
