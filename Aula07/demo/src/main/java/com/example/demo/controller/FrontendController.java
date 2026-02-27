package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller para servir as páginas do frontend
 */
@Controller
public class FrontendController {

    @GetMapping({"/", "/index.html"})
    public String index() {
        return "forward:/index.html";
    }

    @GetMapping("/pages/**")
    public String pages() {
        return "forward:/pages/";
    }
}
