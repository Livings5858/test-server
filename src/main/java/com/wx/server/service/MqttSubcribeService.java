package com.wx.server.service;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSubcribeService {
    public void Subscribe() {
        String broker = "tcp://localhost:1884";
        String sensorTopic = "sensor";
        String imageTopic = "image_path";
        String detectTopic = "image_dectected";
        String username = "emqx";
        String password = "public";
        String clientid = "subscribe_client";
        int qos = 0;

        try {
            MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
            // 连接参数
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            // 设置回调
            client.setCallback(new MqttCallback() {

                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost: " + cause.getMessage());
                }

                public void messageArrived(String topic, MqttMessage message) {
                    System.out.println("topic: " + topic);
                    System.out.println("Qos: " + message.getQos());
                    System.out.println("message content: " + new String(message.getPayload()));
                    DataService dataService = DataService.getInstance();
                    if (sensorTopic.equals(topic)) {
                        dataService.updateData(new String(message.getPayload()));
                    } else if (imageTopic.equals(topic)) {
                        dataService.updateImageData(new String(message.getPayload()));
                    } else if (detectTopic.equals(topic)) {
                        dataService.updateDetectedData(new String(message.getPayload()));
                    } else {
                        System.out.println("topic: " + topic + "is unknown");
                    }
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------" + token.isComplete());
                }

            });
            client.connect(options);
            client.subscribe(sensorTopic, qos);
            client.subscribe(imageTopic, qos);
            client.subscribe(detectTopic, qos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
