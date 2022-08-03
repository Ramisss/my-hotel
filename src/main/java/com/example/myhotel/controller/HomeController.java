package com.example.myhotel.controller;


import com.example.myhotel.command.Command;
import com.example.myhotel.command.CommandType;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/controller")
public class HomeController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeRequest(req, resp);
    }

    private void executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strCommand = request.getParameter(RequestParameter.COMMAND);
        logger.log(Level.INFO, "command is: " + strCommand);
        Command command = CommandType.define(strCommand);
        logger.log(Level.INFO, "CommandType.define---->>>>>" + command);
        Router router;
        try {
            router = command.execute(request);

            logger.log(Level.INFO, "moving to page: " + router.getPage());

            if (router.getActionType() == Router.Type.FORWARD) {
                request.getRequestDispatcher(router.getPage()).forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + router.getPage());
            }
        } catch (CommandException | ServiceException e) {
            logger.log(Level.ERROR, "error in executing command" + strCommand, e);
            throw new ServletException(e);
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        req.getRequestDispatcher("index.jsp").forward(req, resp);
//
//    }
}
