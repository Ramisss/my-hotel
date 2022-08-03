<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Comfortaa&display=swap');

        body {
            /*background: linear-gradient(90deg, #4b6cb7 0%, #182848 100%);*/
            font-family: 'Comfortaa', cursive;

        }

        body::after {
            /*background-image: url("https://images.unsplash.com/photo-1444201983204-c43cbd584d93?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");*/
            background-image: url("/img/backgroundImage.jpg");
            background-repeat: no-repeat;

            background-size: cover;

            background-position: center center;

            content: "";

            filter: blur(4px);

            position: absolute;

            top: 0px;

            left: 0px;

            height: 100vh;

            width: 100vw;

            z-index: -1;
        }

        .login {
            width: 360px;
            padding: 8% 0 0;
            margin: auto;
            font-family: 'Comfortaa', cursive;
        }

        .form {
            position: relative;
            z-index: 1;
            background: #FFFFFF;
            border-radius: 10px;
            max-width: 360px;
            margin: 0 auto 100px;
            padding: 45px;
            text-align: center;
        }

        .form input {
            outline: 0;
            background: #f2f2f2;
            width: 100%;
            border: 0;
            border-radius: 5px;
            margin: 0 0 15px;
            padding: 15px;
            box-sizing: border-box;
            font-size: 14px;
            font-family: 'Comfortaa', cursive;
        }

        .form input:focus {
            background: #dbdbdb;
        }

        .form button {
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

        .form button:active {
            background: #395591;
        }

        .form span {
            font-size: 75px;
            color: #4b6cb7;
        }
    </style>

</head>
<body>
<div class="login">
    <div class="form">
        <form class="login-form" action="${pageContext.request.contextPath}/controller" method="post">
            <span class="material-icons">Log In</span>
            <input type="hidden" name="command" value="sign_in">
            <input type="text" name="email" placeholder="email" required/>
            <input type="password" name="password" placeholder="password" required/>
            <button type="submit" style="font-weight: normal;">login</button>
        </form>
        <a href="../index.jsp">
            <button type="button" style="font-weight: normal">Back to home page...</button>
        </a>
    </div>
</div>
</body>
</html>
