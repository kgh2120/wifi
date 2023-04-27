package com.kk.wifi.service;

import com.kk.wifi.dao.HistoryDao;
import com.kk.wifi.dao.WifiDao;

public class NearWifiService {

    private static final NearWifiService instance = new NearWifiService();
    private final WifiDao wifiDao = WifiDao.getInstance();
    private final HistoryDao historyDao = HistoryDao.getInstance();

    public static NearWifiService getInstance(){
        return instance;
    }

    public void getNear20Wifis(double lat, double lng){

        historyDao.saveHistory(Double.toString(lat),Double.toString(lng));
    }



}
