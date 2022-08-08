package com.example.myhotel.command.impl.admin;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.RequestParameter;
import com.example.myhotel.controller.Router;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AddHotelCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException, ServiceException {
        HttpSession httpSession = request.getSession();

        String name = request.getParameter(RequestParameter.HOTEL_NAME);
        String address = request.getParameter(RequestParameter.HOTEL_ADDRESS);
        String country = request.getParameter(RequestParameter.HOTEL_COUNTRY);
        String phone = request.getParameter(RequestParameter.HOTEL_PHONE);
        String email = request.getParameter(RequestParameter.HOTEL_EMAIL);






        return null;
    }
}
