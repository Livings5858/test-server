# MQTT 数据展示后端实现

## 介绍

基于SpringBoot框架，集成了MQTT Java SDK，实现通过HTTP请求获取或发送消息。

* SpringBoot stater： https://start.spring.io/
* MQTT Eclipse Paho Java Client：https://eclipse.dev/paho/index.php?page=clients/java/index.php

## 目录结构
```
├── ServerApplication.java            // 主程序
├── controller
│   ├── DataController.java           // 处理数据请求
│   └── MqttPublishController.java    // 处理发布MQTT消息的请求
└── service
    ├── DataService.java              // 单例，临时存储MQTT接收到的消息中的数据
    └── MqttSubcribeService.java      // MQTT订阅主题，接收消息
```

## 关联仓库

* MQTT 数据展示前端实现：https://github.com/Livings5858/test-ui


