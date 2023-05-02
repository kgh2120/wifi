package com.kk.wifi.service;

import com.kk.wifi.dao.WifiDao;
import com.kk.wifi.dto.WIfiDetailResponse;

public class RetrieveWifiDetailService {

    private final WifiDao wifiDao = WifiDao.getInstance();
    private static final RetrieveWifiDetailService instance = new RetrieveWifiDetailService();

    public static RetrieveWifiDetailService getInstance(){
        return instance;
    }

    public WIfiDetailResponse retrieveWifiDetail(int historyId, String wifiId) {
        return wifiDao.getWifiDetailInfo(historyId,wifiId);
    }
}
