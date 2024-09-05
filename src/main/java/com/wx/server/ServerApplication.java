package com.wx.server;

import com.wx.server.service.MqttSubcribeService;
import com.wx.server.service.MqttSubcribeService.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		MqttSubcribeService mqttSubcribeService = new MqttSubcribeService();
		mqttSubcribeService.Subscribe();
		SpringApplication.run(ServerApplication.class, args);
	}
}
