package com.kk.wifi;

import com.kk.wifi.controller.Controller;
import com.kk.wifi.controller.HelloController;
import com.kk.wifi.controller.SaveWifiController;
import com.kk.wifi.servlet.MyView;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
;


@WebServlet(name = "frontServlet", value = "/servlet/*")
public class FrontServlet extends HttpServlet {

    private Map<String, Controller> controllerMap = new HashMap<>();
    private static final String PREFIX = "/WEB-INF/views/";
    private static final String POSTFIX = ".jsp";

    public FrontServlet() {
        controllerMap.put("/servlet/hello",new HelloController());
        controllerMap.put("/servlet/wifi",new SaveWifiController());
    }




    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        String uri = req.getRequestURI();

        Controller controller = controllerMap.get(uri);
        if(controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, Object> model = new HashMap<>();
        String logicalPath = controller.process(createParams(req), model);

        MyView view = resolveView(logicalPath, model);
        view.render(req,resp);
    }

    private MyView resolveView(String logicalPath, Map<String, Object> model){
        return new MyView(PREFIX+logicalPath+ POSTFIX, model);
    }

    private Map<String,String> createParams(HttpServletRequest req) {
        Map<String,String> params = new HashMap<>();
        Enumeration<String> names = req.getParameterNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            params.put(name, req.getParameter(name));
        }
        return params;
    }


}
