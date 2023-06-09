package com.kk.wifi.utils;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BodyUtils {

    public static void sendBody(HttpServletResponse resp, Object body){
        Gson gson = new Gson();
        String json = gson.toJson(body);
        resp.setContentType("application/json;charset=UTF-8");
        try {
            resp.getWriter().println(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T  readBody(HttpServletRequest request, Class<T> body) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
        StringBuilder builder = new StringBuilder();
        String buffer;
        while ((buffer = input.readLine()) != null) {
            if (builder.length() > 0) {
                builder.append("\n");
            }
            builder.append(buffer);
        }
        Gson gson = new Gson();
        return gson.fromJson(builder.toString(), body);
    }

}
