package com.springdev1.iver.MqttController;

// import org.springframework.stereotype.Controller;
import com.springdev1.iver.MqttService.MqttServicePublisher;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttMessageController {

    private final MqttServicePublisher mqttServicePublisher;

    public MqttMessageController(MqttServicePublisher mqttServicePublisher) {
        this.mqttServicePublisher = mqttServicePublisher;
    }

    @GetMapping("/send_mqtt_msg")
    // enpoint to send mqtt message, the enpoint
    // contain in the request param the topic and the message to send
    public String sendMessage(
            @RequestParam("message") String msg,
            @RequestParam("topic") String topic) {
        mqttServicePublisher.publishMessage(topic, msg);
        return "Message sent successfully";
    }
}
