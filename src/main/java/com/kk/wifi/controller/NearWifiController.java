package com.kk.wifi.controller;

import com.kk.wifi.dto.NearWifiRequest;
import com.kk.wifi.dto.NearWifiResponse;
import com.kk.wifi.service.NearWifiService;
import com.kk.wifi.utils.BodyUtils;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "nearWifiController", value = "/near")
public class NearWifiController extends HttpServlet {

    private final NearWifiService service = NearWifiService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        NearWifiRequest dto = (NearWifiRequest) BodyUtils.readBody(req,
                NearWifiRequest.class);
        List<NearWifiResponse> response = service.getNear20Wifis(dto.getLat(), dto.getLnt());

        BodyUtils.sendBody(resp,response);
    }
}
