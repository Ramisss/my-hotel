package com.example.myhotel.command.impl.admin;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.PagePath;
import com.example.myhotel.controller.RequestParameter;
import com.example.myhotel.controller.Router;
import com.example.myhotel.dto.RoomDto;
import com.example.myhotel.entity.Room;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import com.example.myhotel.service.impl.RoomServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddRoomCommand implements Command {
    public static final Logger logger = LogManager.getLogger();
    public final RoomServiceImpl roomService = RoomServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, ServiceException {
        HttpSession httpSession = request.getSession();
        Object hotelId = httpSession.getAttribute("hotelId");
        Object userId = httpSession.getAttribute("userId");


        Router router =null;

        String roomName = request.getParameter(RequestParameter.ROOM_NAME);
        String maxPerson = request.getParameter(RequestParameter.MAX_PERSON);
        Short paceToShort = Short.valueOf(maxPerson);

        RoomDto roomDto = RoomDto.builder()
                .name(roomName)
                .maxPerson(paceToShort)
                .userId((Integer) userId)
                .hotelId((Integer) hotelId)
                .isOrdered(false)
                .build();

        Room add = roomService.add(roomDto);


        return router=new Router(PagePath.ADMIN_PAGE,Router.Type.FORWARD);
    }

}
