package com.teisseire_cyril.chat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, Model model) {
        String username = (String) request.getSession().getAttribute("username");

        // If your are not logged, redirect to login page
        if (username == null || username.isEmpty()) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);

        // If you are log you will be redirect on chat page
        return "chat";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        // Login page
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(HttpServletRequest request, @RequestParam(defaultValue = "") String username) {
        username = username.trim();

        // If you have submit empty name you will be redirect on login page
        if (username.isEmpty()) {
            return "login";
        }
        request.getSession().setAttribute("username", username);

        // If your name is not empty you will be redirect on chat page
        return "redirect:/";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).invalidate();

        // You will be redirect on login page
        return "redirect:/";
    }

}