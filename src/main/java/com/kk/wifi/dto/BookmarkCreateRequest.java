package com.kk.wifi.dto;

import lombok.Data;

@Data
public class BookmarkCreateRequest {
    private int distanceId;
    private String wifiId;
    private int bookmarkGroupId;
}
