package com.example.myhotel.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/signup")
public class SignUp extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello! Sammiya!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("message", message);
        request.getRequestDispatcher("pages/signup.jsp").forward(request, response);

    }

    public void destroy() {

    }
}