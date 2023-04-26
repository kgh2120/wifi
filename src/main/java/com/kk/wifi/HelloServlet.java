package com.kk.wifi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.kk.wifi.vo.WifiVO;
import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        response.setContentType("text/html;charset=UTF-8");
//        WifiDao wifiDao = new WifiDao();
//        List<WifiVO> userInfo = getUserInfo();
//        wifiDao.insertWifis(userInfo);
////        for (WifiVO wifiVO : userInfo) {
////            wifiDao.insertWifi(wifiVO);
////        }
//        // Hello

        RequestDispatcher dispatcher = request.getRequestDispatcher("Hello.jsp");
        dispatcher.forward(request,response);
    }

    public void destroy() {
    }

    public List<WifiVO> getUserInfo() {

        try {
            String url = "http://openapi.seoul.go.kr:8088/6e495875506b676839384d6b79554b/json/TbPublicWifiInfo/1/1000/";

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

//                    System.out.println("Response:" + body.string());
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