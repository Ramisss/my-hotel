package com.example.myhotel.controller;

import com.example.myhotel.dto.UserDto;
import com.example.myhotel.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/signup")
public class SignUp extends HttpServlet {

    private final UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/WEB-INF/pages/signup.jsp").forward(request, response);

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String firstname = req.getParameter(RequestParametr.FIRST_NAME);
        String lastname = req.getParameter(RequestParametr.LAST_NAME);
        String login = req.getParameter(RequestParametr.LOGIN);
        String phone = req.getParameter(RequestParametr.PHONE);
        String email = req.getParameter(RequestParametr.EMAIL);
        String password = req.getParameter(RequestParametr.PASSWORD);


        UserDto userDto = UserDto.builder()
                .firstName(firstname)
                .lastName(lastname)
                .phoneNumber(phone)
                .login(login)
                .email(email)
                .password(password)
//                .roleId(Role.ROLE_USER.ordinal())
                .build();
        if (userServiceImpl.create(userDto)) {
            resp.sendRedirect("/signIn");
        } else req.getRequestDispatcher("index.jsp").forward(req, resp);
        ;
//        req.getRequestDispatcher("/signIn").forward(req,resp);
        // TODO

        System.out.println(firstname + lastname);
    }


}