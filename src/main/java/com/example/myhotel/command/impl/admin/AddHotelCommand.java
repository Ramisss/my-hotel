package com.example.myhotel.command.impl.admin;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.PagePath;
import com.example.myhotel.controller.RequestParameter;
import com.example.myhotel.controller.Router;
import com.example.myhotel.dto.HotelDto;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import com.example.myhotel.service.HotelService;
import com.example.myhotel.service.impl.HotelServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddHotelCommand implements Command {

    public static final Logger logger = LogManager.getLogger();
    public final HotelService hotelService = HotelServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, ServiceException {
        HttpSession httpSession = request.getSession();

        String name = request.getParameter(RequestParameter.HOTEL_NAME);
        String address = request.getParameter(RequestParameter.HOTEL_ADDRESS);
        String country = request.getParameter(RequestParameter.HOTEL_COUNTRY);
        String phone = request.getParameter(RequestParameter.HOTEL_PHONE);
        String email = request.getParameter(RequestParameter.HOTEL_EMAIL);

        Router router = null;
        HotelDto hotelDto = HotelDto.builder()
                .name(name)
                .address(address)
                .country(country)
                .phoneNumber(phone)
                .email(email)
                .build();

        Object userRole = httpSession.getAttribute("userRole");
        logger.log(Level.INFO,userRole);

        if (hotelService.add(hotelDto)) {
            request.setAttribute("success_msg", "New hotel saved to DB with name " + hotelDto.getName());
//            return router = new Router(PagePath.ADMIN_PAGE, Router.Type.FORWARD);
        } else
            request.setAttribute("error_msg", "Email or phone  number not valid."
                    + hotelDto.getEmail() + hotelDto.getPhoneNumber());
        return router = new Router(PagePath.ADMIN_PAGE, Router.Type.FORWARD);
    }
}
