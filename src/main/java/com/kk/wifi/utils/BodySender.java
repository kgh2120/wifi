package com.kk.wifi.utils;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class BodySender {

    public static void sendBody(HttpServletResponse resp, Object body){
        Gson gson = new Gson();
        String json = gson.toJson(body);
        resp.setContentType("application/json");
        try {
            resp.getWriter().println(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
