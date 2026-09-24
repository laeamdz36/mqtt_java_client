package com.springdev1.iver.MqttService;

import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

@Service
public class MqttServicePublisher {

    private final Mqtt5AsyncClient mqttClient;

    public MqttServicePublisher(Mqtt5AsyncClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    public void publishMessage(String topic, String message) {

        this.mqttClient.publishWith()
                .topic(topic)
                .payload(message.getBytes(StandardCharsets.UTF_8))
                .qos(MqttQos.AT_LEAST_ONCE)
                .send()
                .whenComplete((publishResult, throwable) -> {
                    if (throwable != null) {
                        // Manejar error de envío
                        System.err.println("Error publicando mensaje MQTT: " + throwable.getMessage());
                    } else {
                        System.out.println("Mensaje publicado exitosamente en el topico: " + topic);
                    }
                });
    }

}
