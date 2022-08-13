package com.example.myhotel.command.impl.admin;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.PagePath;
import com.example.myhotel.controller.RequestParameter;
import com.example.myhotel.controller.Router;
import com.example.myhotel.entity.User;
import com.example.myhotel.entity.type.Role;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import com.example.myhotel.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllUsersCommand implements Command {
    public static final Logger logger = LogManager.getLogger();

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, ServiceException {
        HttpSession httpSession = request.getSession();
        Role userRole = (Role) httpSession.getAttribute(RequestParameter.USER_ROLE);


        Router router;

        if (!userRole.equals(Role.ROLE_ADMIN)) {
            router = new Router(PagePath.HOME_PAGE, Router.Type.REDIRECT);
            return router;
        }
        List<User> userList = userService.findAll();
        if (userList != null)
            userList = userService.findAll();
        logger.log(Level.INFO, userList);
        httpSession.setAttribute(RequestParameter.USERS, userList);
        router = new Router(PagePath.ADMIN_DASHBOARD, Router.Type.FORWARD);
        return router;
    }
}
