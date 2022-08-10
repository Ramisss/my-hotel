package com.example.myhotel.command.impl.admin;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.PagePath;
import com.example.myhotel.controller.RequestParameter;
import com.example.myhotel.controller.Router;
import com.example.myhotel.dto.HotelDto;
import com.example.myhotel.dto.RoomDto;
import com.example.myhotel.entity.Hotel;
import com.example.myhotel.entity.type.Role;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import com.example.myhotel.service.impl.HotelServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AddHotelCommand implements Command {

    public static final Logger logger = LogManager.getLogger();
    public final HotelServiceImpl hotelService = HotelServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession httpSession = request.getSession();

        String name = request.getParameter(RequestParameter.HOTEL_NAME);
        String address = request.getParameter(RequestParameter.HOTEL_ADDRESS);
        String country = request.getParameter(RequestParameter.HOTEL_COUNTRY);
        String phone = request.getParameter(RequestParameter.HOTEL_PHONE);
        String email = request.getParameter(RequestParameter.HOTEL_EMAIL);
//ROOM
        String roomName = request.getParameter(RequestParameter.ROOM_NAME);
        String maxPerson = request.getParameter(RequestParameter.MAX_PERSON);
        String rate = request.getParameter(RequestParameter.RATE);
        Short paceToShort = Short.valueOf(maxPerson);

        Router router = null;
        HotelDto hotelDto = HotelDto.builder()
                .name(name)
                .address(address)
                .country(country)
                .phoneNumber(phone)
                .email(email)
                .build();

        Object userRole = httpSession.getAttribute("userRole");
        logger.log(Level.INFO, userRole);
        if (userRole == null) {
            userRole = new Object();
        }
        logger.log(Level.INFO, Role.ROLE_ADMIN);

        try {
            if (!(userRole.equals(Role.ROLE_ADMIN.toString()))) {
                return router = new Router(PagePath.HOME_PAGE, Router.Type.REDIRECT);
            }
            Hotel addHotel = hotelService.add(hotelDto);
            Integer hotelId = addHotel.getId();
            httpSession.setAttribute("hotelId", hotelId);
            Optional<Hotel> optionalHotel = hotelService.findById(hotelId);

            if (optionalHotel.isPresent()) {
                request.setAttribute("success_msg", "New hotel saved to DB with name " + hotelDto.getName());
                hotelService.getHotel();


                RoomDto roomDto = RoomDto.builder()
                        .name(roomName)
                        .maxPerson(paceToShort)
//                        .highSessionRate()
//                        .lowSeasonRate()
                        .build();


                return router = new Router(PagePath.ADMIN_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Hotel not saved", e);
            throw new CommandException(e);
        }
        request.setAttribute("error_msg", "Email or phone  number not valid."
                + hotelDto.getEmail() + hotelDto.getPhoneNumber());
        return router = new Router(PagePath.ADMIN_PAGE, Router.Type.FORWARD);
    }
}