package com.kk.wifi.controller;

import com.kk.wifi.dto.NearWifiRequest;
import com.kk.wifi.utils.BodyUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "nearWifiController", value = "/near")
public class NearWifiController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        NearWifiRequest reqeust = (NearWifiRequest) BodyUtils.readBody(req,
                NearWifiRequest.class);


        BodyUtils.sendBody(resp,reqeust);
    }
}
