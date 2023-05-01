package com.kk.wifi.dto;

import lombok.Data;

@Data
public class BookmarkGroupListResponse {

    private int id;
    private String name;
    private int orders;
    private String created_at;
    private String modified_at;

}
