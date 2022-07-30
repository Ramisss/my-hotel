package com.example.myhotel.controller;

import com.example.myhotel.service.UserService;
import com.example.myhotel.validation.Parametrs;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/signIn")
public class SignIn extends HttpServlet {

    public static final Logger logger = LogManager.getLogger();

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/signin.jsp").forward(req, resp);

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(Parametrs.EMAIL);
        String password = req.getParameter(Parametrs.PASSWORD);
        if (userService.authEmailAndPassword(email, password)) {
            resp.sendRedirect("/cabinet");
            logger.log(Level.INFO,"enter home page");
            return;
        }

        resp.sendRedirect("/signup");
        logger.log(Level.ERROR,"enter Registration page(email or password incorrect!)");

//        resp.setHeader(email,password);
    }
}
