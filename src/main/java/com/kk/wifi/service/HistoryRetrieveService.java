package com.kk.wifi.service;

import com.kk.wifi.dao.HistoryDao;
import com.kk.wifi.dto.HistoryResponse;
import java.util.List;

public class HistoryRetrieveService {

    private final HistoryDao historyDao = HistoryDao.getInstance();
    private static final HistoryRetrieveService instance = new HistoryRetrieveService();

    public static HistoryRetrieveService getInstance(){
        return instance;
    }

    public List<HistoryResponse> retrieveHistory(){
        historyDao.init();
        System.out.println("service-hit!");
        List<HistoryResponse> responses = historyDao.retrieveHistory();
        historyDao.end();
        return responses;

    }

}
