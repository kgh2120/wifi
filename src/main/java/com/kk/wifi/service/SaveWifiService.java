package com.kk.wifi.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.kk.wifi.dao.WifiDao;
import com.kk.wifi.dto.WifiCountResponse;
import com.kk.wifi.vo.WifiVO;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SaveWifiService {

    private final WifiDao wifiDao = WifiDao.getInstance();
    private static final SaveWifiService instance = new SaveWifiService();

    public static SaveWifiService getInstance(){
        return instance;
    }

    public WifiCountResponse saveWifiData(){



        int offset = 1;
        int limit = 1000;
        wifiDao.init();

        int count = wifiDao.countWifis();
        if(count != 0)
            return new WifiCountResponse(count);

        for (int i = 0; i < 24; i++) {
            List<WifiVO> userInfo = getUserInfo(offset,limit);
            System.out.println("Offset = "+offset + " limit = " + limit + " size = " + userInfo.size());
            wifiDao.insertWifis(userInfo);
            offset += 1000;
            limit += 1000;
        }

        count = wifiDao.countWifis();
        wifiDao.end();

        return new WifiCountResponse(count);


    }

    private List<WifiVO> getUserInfo(int offSet, int limit) {
        // 24번 반복 how?
        try {
            String url = "http://openapi.seoul.go.kr:8088/6e495875506b676839384d6b79554b/json/TbPublicWifiInfo/"+offSet+"/"+limit+"/";

            // OkHttp 클라이언트 객체 생성
            OkHttpClient client = new OkHttpClient();

            // GET 요청 객체 생성
            Request.Builder builder = new Request.Builder().url(url).get();
            Request request = builder.build();

            // OkHttp 클라이언트로 GET 요청 객체 전송
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // 응답 받아서 처리
                ResponseBody body = response.body();

                if (body != null) {

                    JsonElement jsonElement = JsonParser.parseString(body.string());
                    JsonObject root = jsonElement.getAsJsonObject()
                            .get("TbPublicWifiInfo").getAsJsonObject();
                    JsonArray row = root.get("row").getAsJsonArray();
                    Gson gson = new Gson();
                    return gson.fromJson(row.toString(), new TypeToken<List<WifiVO>>(){}.getType());
                }
            }
            else
                System.err.println("Error Occurred");
            return null;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
