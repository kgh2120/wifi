package com.kk.wifi.controller;

import com.kk.wifi.service.SaveWifiService;
import java.util.Map;

public class SaveWifiController implements Controller{

    private final SaveWifiService service = SaveWifiService.getInstance();
    @Override
    public String process(Map<String, String> param, Map<String, Object> model) {
        int count = service.saveWifiData();
        model.put("count",count);
        return "wifi";
    }
}
