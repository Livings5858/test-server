package com.wx.server.controller;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class MqttPublishController {
    String topic        = "control";
    //    String content      = "Message from MqttPublishSample";
    int qos             = 2;
    String broker       = "tcp://localhost:1884";
    String clientId     = "JavaSample";
    MemoryPersistence persistence = new MemoryPersistence();
    org.eclipse.paho.client.mqttv3.MqttClient sampleClient;
    int isConnected = 0;

    public String Connect() {
        try {
            sampleClient = new org.eclipse.paho.client.mqttv3.MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            isConnected = 1;
            System.out.println("Connected");
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
        return ("Connected to broker: " + broker);
    }

    @GetMapping("/control")
    public String Publish(@RequestParam(value = "content", defaultValue = "Message from MqttPublishSample") String content) {
        System.out.println("Publishing message: "+content);
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        String status = " OK";
        if (isConnected == 0) {
            Connect();
        }
        try {
            sampleClient.publish(topic, message);
        } catch (MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            status = " error, " + "reason "+me.getReasonCode();
            if (isConnected == 1) {
                Disconnect();
            }
            me.printStackTrace();
        }
        System.out.println("Message published");
        return "Publishing message: " + content + status;
    }

    public String Disconnect() {
        try {
            sampleClient.disconnect();
            isConnected = 0;
            System.out.println("Disconnected");
        } catch (MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
        return "Disconnected: " + broker;
    }
}
