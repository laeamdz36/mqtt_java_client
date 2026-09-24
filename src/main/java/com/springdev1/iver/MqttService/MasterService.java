/** MQTT Serivice
 * 
 */
package com.springdev1.iver.MqttService;

import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;

import jakarta.annotation.PreDestroy;

import com.hivemq.client.mqtt.MqttClient;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MasterService {

    private Mqtt5AsyncClient client;

    @Bean
    public Mqtt5AsyncClient mqttClient() {
        // Intance the client
        Mqtt5AsyncClient client = MqttClient.builder()
                .useMqttVersion5()
                .identifier("java-client-iver")
                .serverHost("192.168.10.105")
                .serverPort(1883)
                .simpleAuth()
                .username("user")
                .password("pass".getBytes(StandardCharsets.UTF_8))
                .applySimpleAuth()
                .buildAsync();
        client.connect();
        return client;
    }

    @PreDestroy
    public void cleanup() {
        if (client != null && client.getState().isConnected()) {
            client.disconnect();
        }
    }
}
