<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Comfortaa&display=swap');

        body {
            /*font-family: Calibri, Helvetica, sans-serif;*/
            font-family: 'Comfortaa', cursive;
        }

        .container {
            padding: 35px;
            /*background-color: #20ff9f;*/
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

        /*.form input {*/
        /*    outline: 0;*/
        /*    background: #f2f2f2;*/
        /*    width: 100%;*/
        /*    border: 0;*/
        /*    border-radius: 5px;*/
        /*    margin: 0 0 15px;*/
        /*    padding: 15px;*/
        /*    box-sizing: border-box;*/
        /*    font-size: 14px;*/
        /*    font-family: 'Comfortaa', cursive;*/
        /*}*/

        .registerbtn:hover {
            opacity: 1;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/signup" method="post">
    <div class="container">
        <h1> Registration Form</h1>
        <hr>
        <label> <b>Firstname</b> </label>
        <input type="text" name="firstname" placeholder="Firstname" size="15" required/>

        <label> <b>Lastname:</b> </label>
        <input type="text" name="lastname" placeholder="Lastname" size="15" required/>

        <label> <b>Login: </b></label>
        <input type="text" name="login" placeholder="Login" size="15" required/>


        <label>
            <b>Phone :</b>
        </label>
        <input type="text" name="phone" placeholder="Phone Number (+900 00-000-00-00)" required/>

        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="email" id="email" required>

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" id="password" required>

        <button type="submit" class="registerbtn">Register</button>
</form>
</body>
</html>

<%--pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"--%>
