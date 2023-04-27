package com.kk.wifi.controller;

import com.kk.wifi.service.HistoryRetrieveService;
import com.kk.wifi.utils.BodyUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "historyController", value = "/history")
public class HistoryController extends HttpServlet {

    private final HistoryRetrieveService service = HistoryRetrieveService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("hit!");
        BodyUtils.sendBody(resp,service.retrieveHistory());
    }
}
