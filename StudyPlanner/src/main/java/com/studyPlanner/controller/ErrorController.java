package com.studyPlanner.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController{
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        return "customErrorPage"; // return a view name or string response
    }
}
