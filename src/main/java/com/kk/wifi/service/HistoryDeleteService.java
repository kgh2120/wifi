package com.kk.wifi.service;

import com.kk.wifi.dao.HistoryDao;

public class HistoryDeleteService {

    private final HistoryDao historyDao = HistoryDao.getInstance();
    private static final HistoryDeleteService instance = new HistoryDeleteService();
    public static HistoryDeleteService getInstance(){
        return instance;
    }

    public void deleteHistory(int id){
        historyDao.deleteHistory(id);
    }

}
