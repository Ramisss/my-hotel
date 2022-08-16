package com.example.myhotel.command.impl.admin;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.PagePath;
import com.example.myhotel.controller.RequestParameter;
import com.example.myhotel.controller.Router;
import com.example.myhotel.dto.HotelDto;
import com.example.myhotel.dto.RoomDto;
import com.example.myhotel.entity.type.Role;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import com.example.myhotel.service.RoomService;
import com.example.myhotel.service.impl.RoomServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditRoomCommand implements Command {
    public static final Logger logger = LogManager.getLogger();
    private final RoomService roomService = RoomServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, ServiceException {
        HttpSession httpSession = request.getSession();


        Router router;
//        Role parameter = Role.valueOf(request.getParameter(RequestParameter.USER_ROLE));
        Integer roomId = Integer.valueOf(request.getParameter(RequestParameter.ROOM_ID));
        String hotelName = request.getParameter(RequestParameter.HOTEL_NAME);
        String roomName = request.getParameter(RequestParameter.ROOM_NAME);
        Integer roomNumber = Integer.valueOf(request.getParameter(RequestParameter.ROOM_NUMBER));
        Short maxPerson = Short.valueOf(request.getParameter(RequestParameter.MAX_PERSON));

        logger.log(Level.INFO,roomId+"ROOM_ID");

       RoomDto roomDto =RoomDto.builder()
               .id(roomId)
               .name(roomName)
               .number(roomNumber)
               .maxPerson(maxPerson)
               .build();


        roomService.update(roomDto);

        logger.log(Level.INFO,roomDto);
        return router =new Router(PagePath.ADMIN_EDIT_ROOM, Router.Type.FORWARD);
    }
}
