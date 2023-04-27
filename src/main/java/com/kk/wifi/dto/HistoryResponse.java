package com.kk.wifi.dto;

import lombok.Data;

@Data
public class HistoryResponse {

    private Integer id;
    private double lat;
    private double lnt;
    private String createdAt;

    public HistoryResponse(Integer id, String lat, String lnt, String createdAt) {
        this.id = id;
        this.lat = Double.parseDouble(lat);
        this.lnt = Double.parseDouble(lnt);
        this.createdAt = createdAt;
    }
}
