package com.hongyuji.imsystem.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToolController {

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String healthCheck(){
        return "--OK--";
    }
}
