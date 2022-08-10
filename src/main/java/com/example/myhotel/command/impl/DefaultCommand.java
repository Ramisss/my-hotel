package com.example.myhotel.command.impl;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.PagePath;
import com.example.myhotel.controller.Router;
import com.example.myhotel.entity.type.Role;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultCommand implements Command {
    public static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException, ServiceException {
        Router router = new Router(PagePath.HOME_PAGE, Router.Type.FORWARD);
        logger.log(Level.INFO,"DefaultCommand");
        return router;
    }
}
