package com.example.myhotel.command.impl;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.PagePath;
import com.example.myhotel.controller.RequestParameter;
import com.example.myhotel.controller.Router;
import com.example.myhotel.dto.UserDto;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import com.example.myhotel.service.UserService;
import com.example.myhotel.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpCommand implements Command {
    public static final Logger logger = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String login = request.getParameter(RequestParameter.LOGIN);
        String phone = request.getParameter(RequestParameter.PHONE);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);

        Router router = new Router();

        UserDto userDto = UserDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phone)
                .login(login)
                .email(email)
                .password(password)
//                .roleId(Role.ROLE_USER.ordinal())
                .build();
        try {
            if (userService.create(userDto)) {
                router = new Router(PagePath.SIGN_IN, Router.Type.FORWARD);
            } else {
                request.setAttribute("emailAndPhoneNumber", "email or phone number not valid.");
                router = new Router(PagePath.SIGN_UP, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}