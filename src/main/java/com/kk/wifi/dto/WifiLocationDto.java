package com.kk.wifi.dto;

import lombok.Data;

@Data
public class WifiLocationDto {
    private String id;
    private double lat;
    private double lnt;

    public WifiLocationDto(String id, String lat, String lnt) {
        this.id = id;
        this.lat = Double.parseDouble(lat);
        this.lnt = Double.parseDouble(lnt);
    }
}
