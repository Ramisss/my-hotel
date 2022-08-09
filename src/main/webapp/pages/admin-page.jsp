<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.08.2022
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Comfortaa&display=swap');

        body {
            /*font-family: Calibri, Helvetica, sans-serif;*/
            font-family: 'Comfortaa', cursive;
        }

        body::after {
            background-repeat: no-repeat;

            background-size: cover;

            background-position: center center;

            content: "";

            filter: blur(2px);

            position: absolute;

            top: 0px;

            left: 0px;

            height: 100vh;

            width: 100vw;

            z-index: -1;
        }

        .container {
            padding: 35px;
        }

        h1 {
            color: #4b6cb7;
        }

        input[type=text], input[type=password], textarea {
            width: 100%;
            padding: 15px;
            margin: 5px 0 10px 0;
            border: 0;
            border-radius: 5px;
            display: inline-block;
            background-color: #f2f2f2;
            font-family: 'Comfortaa', cursive;
        }

        input[type=text]:focus, input[type=password]:focus {
            background-color: #e4ffc8;
            outline: 0;
        }

        div {
            padding: 10px 0;
        }

        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 20px;
        }

        .registerbtn {
            font-family: 'Comfortaa', cursive;
            text-transform: uppercase;
            outline: 0;
            background: #4b6cb7;
            width: 100%;
            border: 0;
            border-radius: 5px;
            padding: 15px;
            color: #FFFFFF;
            font-size: 14px;
            -webkit-transition: all 0.3 ease;
            transition: all 0.3 ease;
            cursor: pointer;
        }


        .registerbtn:hover {
            opacity: 1;
        }
    </style>
    <title>ADMIN PAGE</title>
</head>
<body>
<%----%>

<button type="button" class="registerbtn">
    <a href="${pageContext.request.contextPath}/pages/admin-dashboard.jsp"> SIGN IN </a>
</button>

<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="add_hotel">
    <div class="container">
        <h1> Add Hotel Form</h1>
        <hr>
        <label> <b>Name</b> </label>
        <input type="text" name="hotelName" placeholder="Name" size="15" required/>

        <label> <b>Address:</b> </label>
        <input type="text" name="hotelAddress" placeholder="Address" required/>

        <label> <b>Country:</b> </label>
        <input type="text" name="hotelCountry" placeholder="Country" required/>


        <label> <b>Phone :</b>
        </label>
        <input type="text" name="phone" placeholder="Phone Number (+998 00-000-00-00)" required/>

        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Email" name="email" id="email" required>


        <button type="submit" class="registerbtn">Add Hotel</button>


</form>

<h2 style="color: greenyellow;text-align: center">${success_msg}</h2>
<h2 style="color: red;text-align: center">${error_msg}</h2>

<%--ADD ROOM--%>
<%----%>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="add_room">
    <div class="container1">
        <h1> Add Room Form</h1>
        <hr>
        <label> <b>Name</b> </label>
        <input type="text" name="roomName" placeholder="Name" size="15" required/>

        <label> <b>Max person:</b> </label>
        <input type="text" name="maxPerson" placeholder="only number" required/>

        <label> <b>Rate:</b> </label>
        <input type="text" name="rate" placeholder="low or high rate" required />





        <button type="submit" class="registerbtn">Add Room </button>

        <%--        ERROR--%>
<%--        <h2 style="color: red; text-align: center">${emailAndPhoneNumber}</h2>--%>
</form>
<%----%>

<h2 style="color: #e4ffc8; text-align: center">Hello ${user_name} !!

</h2>
</body>
</html>
