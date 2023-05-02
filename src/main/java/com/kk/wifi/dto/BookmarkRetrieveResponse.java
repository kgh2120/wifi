package com.kk.wifi.dto;

import lombok.Data;

@Data
public class BookmarkRetrieveResponse {

    private int id;
    private int history_id;
    private String wifi_id;
    private String group_name;
    private String wifi_name;
    private String created_at;


}
