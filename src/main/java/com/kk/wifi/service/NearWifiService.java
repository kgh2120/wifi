package com.kk.wifi.service;

import com.kk.wifi.dao.DistanceDao;
import com.kk.wifi.dao.HistoryDao;
import com.kk.wifi.dao.WifiDao;
import com.kk.wifi.dto.NearWifiResponse;
import com.kk.wifi.dto.WifiLocationDto;
import com.kk.wifi.utils.LocationDistance;
import com.kk.wifi.vo.DistanceVO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class NearWifiService {

    private static final NearWifiService instance = new NearWifiService();
    private final WifiDao wifiDao = WifiDao.getInstance();
    private final HistoryDao historyDao = HistoryDao.getInstance();
    private final DistanceDao distanceDao = DistanceDao.getInstance();

    public static NearWifiService getInstance(){
        return instance;
    }

    public List<NearWifiResponse> getNear20Wifis(double lat, double lng){

        int id = historyDao.saveHistory(Double.toString(lat), Double.toString(lng));
        List<WifiLocationDto> location = wifiDao.getLocation();
        PriorityQueue<DistanceVO> pq = new PriorityQueue<>(
                Comparator.comparingDouble(DistanceVO::getDistance)
        );
        for (WifiLocationDto wifiLocationDto : location) {
            double distance = LocationDistance.distance(lat, lng, wifiLocationDto.getLat(),
                    wifiLocationDto.getLnt());
            pq.add(new DistanceVO(wifiLocationDto.getId(), distance,id));
        }

        List<DistanceVO> list = new ArrayList<>();

        for (int i = 0; i < 20; i++)
            list.add(pq.poll());

        distanceDao.save(list);

        return distanceDao.getNearWifi(id);
    }



}
