package com.yan.performanceAmountQuery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/packageQuery")
public class PackageQuery {


    @RequestMapping("/amountQuery")
    public String amountQuery(){
        return "/package/amountQuery";
    }

    @RequestMapping("/queryByCondition")
    public void amountQueryByCondition(HttpServletRequest request){

    }
}
