package com.example.myhotel.command.impl.admin;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.PagePath;
import com.example.myhotel.controller.RequestParameter;
import com.example.myhotel.controller.Router;
import com.example.myhotel.entity.Room;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import com.example.myhotel.service.RoomService;
import com.example.myhotel.service.impl.RoomServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class DeleteRoomCommand implements Command {
    public static final Logger logger = LogManager.getLogger();
    private final RoomService roomService = RoomServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, ServiceException {
        HttpSession httpSession = request.getSession();

        Router router;
        Integer roomId = Integer.valueOf(request.getParameter(RequestParameter.ROOM_ID));
        logger.log(Level.INFO, roomId + "room id from all-rooms.jsp");
        Optional<Room> optionalRoom = roomService.findById(roomId);
        if (!optionalRoom.isPresent()) {
            request.setAttribute(RequestParameter.ERROR_MSG, "room not found!");
            router = new Router(PagePath.ADMIN_FIND_ALL_ROOMS, Router.Type.FORWARD);
            return router;
        }

        router = new Router(PagePath.ADMIN_FIND_ALL_ROOMS, Router.Type.FORWARD);

        return router;
    }
}
