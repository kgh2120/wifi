package com.kk.wifi.controller;

import com.kk.wifi.service.SaveWifiService;
import com.kk.wifi.utils.BodyUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "saveWifiController", value = "/wifi")
public class SaveWifiController extends HttpServlet {

    private final SaveWifiService service = SaveWifiService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BodyUtils.sendBody(resp,service.saveWifiData());
    }

}
