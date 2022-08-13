package com.example.myhotel.command.impl.admin;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.PagePath;
import com.example.myhotel.controller.RequestParameter;
import com.example.myhotel.controller.Router;
import com.example.myhotel.entity.Room;
import com.example.myhotel.entity.type.Role;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.service.impl.RoomServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FindAllRoomsCommand implements Command {
    public static final Logger logger = LogManager.getLogger();
    private final RoomServiceImpl roomService = RoomServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession httpSession = request.getSession();
        Role role = (Role) httpSession.getAttribute(RequestParameter.USER_ROLE);

        Router router;
        if (role.equals(Role.ROLE_ADMIN)) {
            List<Room> roomList = new ArrayList<>();
            try {
                roomList = roomService.findAllUsers();
                httpSession.setAttribute(RequestParameter.ROOMS, roomList);
                logger.log(Level.INFO,roomList);
                router = new Router(PagePath.ADMIN_FIND_ALL_ROOMS, Router.Type.FORWARD);
                return router;
            } catch (Exception e) {
                throw new CommandException(e);
            }
        }
        router = new Router(PagePath.HOME_PAGE, Router.Type.REDIRECT);
        return router;
    }
}
