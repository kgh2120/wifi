package com.kk.wifi.controller;

import java.util.Map;

public interface Controller {

    String process(Map<String,String> param, Map<String,Object> model);

}
