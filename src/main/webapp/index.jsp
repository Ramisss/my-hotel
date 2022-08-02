<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<style>
    body {
        font-family: Arial, Helvetica, sans-serif;
    }

    * {
        box-sizing: border-box
    }

    button {
        display: block;
        /*float: right;*/
        background-color: #04AA6D;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 20%;
        opacity: 0.9;
    }
</style>
<head>
    <title>Home page </title>
    <style>
        body {
            font-family: 'Comfortaa', cursive;
            /*color: #f1f1f1;*/
        }

        button {
            font-family: 'Comfortaa', cursive;
            display: inline-block;
            justify-content: center;
            text-transform: uppercase;
            outline: 0;
            background: #4b6cb7;
            width: 30%;
            border: 0;
            border-radius: 5px;
            padding: 15px;
            font-size: 16px;
            -webkit-transition: all 0.3 ease;
            transition: all 0.3 ease;
            cursor: pointer;
        }

        a {
            color: black;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1 style="font-weight: bold; color: #4b6cb7;"> Welcome to home page! </h1>
<br/>

<form action="${pageContext.request.contextPath}/controller" method="post">
<%--<a href="/login" style="color: darkcyan"> SIGN IN </a>--%>
<button type="submit" class="cancelbtn">
    <input type="hidden" name="command" value="sign_up">
<%--    <a href="${pageContext.request.contextPath}/signup"> SIGN UP </a>--%>
<%--    <a href="${pageContext.request.contextPath}/signup"> SIGN UP </a>--%>
</button>
<button type="button" class="cancelbtn">

    <a href="${pageContext.request.contextPath}/signIn"> SIGN IN </a>
</button>
</form>
</body>
</html>

<%--/my_hotel_war_exploded--%>