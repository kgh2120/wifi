package com.kk.wifi.controller;

import java.util.Map;

public class HelloController implements Controller{

    @Override
    public String process(Map<String, String> param, Map<String, Object> model) {

        return "Hello";
    }
}
