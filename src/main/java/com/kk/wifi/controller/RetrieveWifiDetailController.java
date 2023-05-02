package com.kk.wifi.controller;

import com.kk.wifi.dto.WIfiDetailResponse;
import com.kk.wifi.service.RetrieveWifiDetailService;
import com.kk.wifi.utils.BodyUtils;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "retrieveWifiDetailController", value = "/wifi/detail")
public class RetrieveWifiDetailController extends HttpServlet {


    private final RetrieveWifiDetailService retrieveWifiDetailService = RetrieveWifiDetailService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try{
            int historyId = Integer.parseInt(Objects.requireNonNull(req.getParameter("historyId")));
            String wifiId = Objects.requireNonNull(req.getParameter("wifiId"));
            WIfiDetailResponse response = retrieveWifiDetailService.retrieveWifiDetail(historyId,wifiId);
            BodyUtils.sendBody(resp, response);
        }catch (NullPointerException | NumberFormatException e){
            resp.setStatus(404);
            // TODO Exception Handling 추가
        }


    }
}
