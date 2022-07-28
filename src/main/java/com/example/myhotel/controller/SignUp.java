package com.example.myhotel.controller;

import com.example.myhotel.dto.UserDto;
import com.example.myhotel.entity.enums.Role;
import com.example.myhotel.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/signup")
public class SignUp extends HttpServlet {

    private final UserService userService = UserService.getInstance();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/WEB-INF/pages/signup.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDto userDto = UserDto.builder()
                .firstName(firstname)
                .lastName(lastname)
                .phoneNumber(phone)
                .login(login)
                .email(email)
                .password(password)
                .roleId(Role.ROLE_USER.ordinal())
                .build();
        userService.create(userDto);
//        req.getRequestDispatcher("/signIn").forward(req,resp);
        // TODO
        resp.sendRedirect("/signIn");

        System.out.println(firstname + lastname);
    }


}