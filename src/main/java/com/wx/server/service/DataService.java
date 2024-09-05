package com.wx.server.service;

import java.util.ArrayList;
import java.util.List;

public class DataService {

    private List<String> data = new ArrayList<>();
    private int temperature = 0;
    private boolean isFired = false;
    private String imagePath;

    private static final DataService instance = new DataService();

    // 初始化数据
    private DataService() {}

    public static DataService getInstance() {
        return instance;
    }

    public List<String> getData() {
        return new ArrayList<>(data); // 返回副本以防止并发修改
    }
    public int getTemperature() {
        return temperature;
    }
    public String getImagePath() {
        return imagePath;
    }

    public String getFireInfo() {
        return isFired ? "Warning: Fire Detected!!!" : "";
    }

    public void updateData(String input) {
        updateTemperature(input);
        data.clear();
        System.out.println("Data updated: " + input);
        String[] parts = input.split(",");
        data.add("当前温度 " + ": " + parts[0] + " ℃");
        data.add("当前坐标 " + ": (" + parts[1] + ", " + parts[2] + ")");
        if (isFired) {
            data.add("Warning: Fire Detected!!!");
        }
        System.out.println("Data updated: " + data);
    }

    public void updateTemperature(String input) {
        System.out.println("Data updated: " + input);
        String[] parts = input.split(",");

        // 将字符串数组转换为整数数组
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            try {
                numbers[i] = Integer.parseInt(parts[i].trim());
            } catch (NumberFormatException e) {
                System.out.println("无效的数字格式: " + parts[i]);
            }
        }
        temperature = numbers[0];
    }

    public void updateMsg(String msg) {
        data.add("Msg: " + msg);
    }

    public void updateImageData(String s) {
        imagePath = s;
    }

    public void updateDetectedData(String s) {
        System.out.println("Data updated: " + s);
        isFired = s.equals("fire");
    }
}