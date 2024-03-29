package com.example.myhotel.command.impl.admin;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.PagePath;
import com.example.myhotel.controller.RequestParameter;
import com.example.myhotel.controller.Router;
import com.example.myhotel.entity.Hotel;
import com.example.myhotel.entity.Room;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import com.example.myhotel.service.HotelService;
import com.example.myhotel.service.RoomService;
import com.example.myhotel.service.impl.HotelServiceImpl;
import com.example.myhotel.service.impl.RoomServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class EditRoomPageCommand implements Command {
    public static final Logger logger = LogManager.getLogger();
    private final RoomService roomService = RoomServiceImpl.getInstance();
    private final HotelService hotelService = HotelServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, ServiceException {
        HttpSession httpSession = request.getSession();

        Router router;
        Hotel hotel;
        Room room;

        Integer roomId = Integer.valueOf(request.getParameter(RequestParameter.ROOM_ID));
        logger.log(Level.INFO, roomId + "room ----id");

        Optional<Room> optionalRoom = roomService.findById(roomId);
        if (!optionalRoom.isPresent()) {
            request.setAttribute("room_id", "Room not found!");
            router = new Router(PagePath.ADMIN_FIND_ALL_ROOMS, Router.Type.FORWARD);
            return router;
        }

        room = roomService.findById(roomId).get();
        hotel = hotelService.findById(room.getHotelId()).get();

//        request.setAttribute(RequestParameter.HOTEL_NAME,hotel);
//        request.setAttribute(RequestParameter.ROOM_NAME,room);

        request.setAttribute("room", room);
        logger.log(Level.INFO, room.toString());
        request.setAttribute("hotel", hotel);
        logger.log(Level.INFO, hotel.toString());




//        roomService.editRoom(room, hotel);


        router = new Router(PagePath.ADMIN_EDIT_ROOM_PAGE, Router.Type.FORWARD);
        return router;
    }
}
