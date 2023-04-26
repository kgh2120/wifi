package com.kk.wifi.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyView {

    private String viewPath;
    private Map<String,Object> model;

    public MyView(String viewPath, Map<String, Object> model) {
        this.viewPath = viewPath;
        this.model = model;
    }

    public void render(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);

        for (Entry<String, Object> entry : model.entrySet())
            req.setAttribute(entry.getKey(), entry.getValue());

        dispatcher.forward(req,res);
    }
}
