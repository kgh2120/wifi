package com.kk.wifi.vo;

import lombok.Data;

@Data
public class DistanceVO {

    private long id;
    private String wifiId;
    private int historyId;
    private double distance;

    public DistanceVO(String wifiId, double distance, int historyId) {
        this.wifiId = wifiId;
        this.distance = distance;
        this.historyId = historyId;
    }
}
