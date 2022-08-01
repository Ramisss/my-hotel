package com.example.myhotel.controller;

import com.example.myhotel.dto.UserDto;
import com.example.myhotel.service.UserService;
import com.example.myhotel.validation.Parametrs;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/signup")
public class SignUp extends HttpServlet {

    private final UserService userService = UserService.getInstance();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/WEB-INF/pages/signup.jsp").forward(request, response);

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String firstname = req.getParameter(Parametrs.FIRST_NAME);
        String lastname = req.getParameter(Parametrs.LAST_NAME);
        String login = req.getParameter(Parametrs.LOGIN);
        String phone = req.getParameter(Parametrs.PHONE);
        String email = req.getParameter(Parametrs.EMAIL);
        String password = req.getParameter(Parametrs.PASSWORD);


        UserDto userDto = UserDto.builder()
                .firstName(firstname)
                .lastName(lastname)
                .phoneNumber(phone)
                .login(login)
                .email(email)
                .password(password)
//                .roleId(Role.ROLE_USER.ordinal())
                .build();
        if (userService.create(userDto)) {
            resp.sendRedirect("/signIn");
        } else req.getRequestDispatcher("index.jsp").forward(req, resp);
        ;
//        req.getRequestDispatcher("/signIn").forward(req,resp);
        // TODO

        System.out.println(firstname + lastname);
    }


}