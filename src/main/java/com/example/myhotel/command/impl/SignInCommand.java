package com.example.myhotel.command.impl;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.PagePath;
import com.example.myhotel.controller.RequestParameter;
import com.example.myhotel.controller.Router;
import com.example.myhotel.entity.User;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import com.example.myhotel.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignInCommand implements Command {

    public static final Logger logger = LogManager.getLogger();
    private final UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession httpSession = request.getSession();
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        Router router = new Router();
        User user = null;

        try {
            if (userServiceImpl.authEmailAndPassword(email, password)) {
                user = userServiceImpl.getByEmail(email);
                httpSession.setAttribute("userRole",user.getRole().name());
                logger.log(Level.INFO,"user role from session"+httpSession.getAttribute("userRole"));

                String role = user.getRole().name();

                if ("ROLE_USER".equals(role)) {
                    logger.log(Level.INFO, "email and password is correct. " + user.getRole().name());
                    router = new Router(PagePath.USER_PAGE, Router.Type.REDIRECT);
                } else if ("ROLE_ADMIN".equals(role)) {
                    logger.log(Level.INFO, "email and password is correct. " + user.getRole().name());
                    router = new Router(PagePath.ADMIN_PAGE, Router.Type.REDIRECT);
                }

            } else {
                request.setAttribute("login_error", "Login or password incorrect! ");
                router = new Router(PagePath.HOME_PAGE, Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
