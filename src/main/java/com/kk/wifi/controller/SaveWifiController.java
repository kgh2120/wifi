package com.kk.wifi.controller;

import com.google.gson.Gson;
import com.kk.wifi.service.SaveWifiService;
import com.kk.wifi.utils.BodySender;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "saveWifiController", value = "/wifi")
public class SaveWifiController extends HttpServlet {

    private final SaveWifiService service = SaveWifiService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BodySender.sendBody(resp,service.saveWifiData());
    }

}
