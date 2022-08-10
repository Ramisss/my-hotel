package com.example.myhotel.command.impl.admin;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.RequestParameter;
import com.example.myhotel.controller.Router;
import com.example.myhotel.dto.RoomDto;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AddRoomCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException, ServiceException {
        HttpSession httpSession = request.getSession();

        String roomName = request.getParameter(RequestParameter.ROOM_NAME);
        String maxPerson = request.getParameter(RequestParameter.MAX_PERSON);
        Short paceToShort = Short.valueOf(maxPerson);

        RoomDto roomDto = RoomDto.builder()
                .name(roomName)
                .maxPerson(paceToShort)
                .build();


        return null;
    }
}
