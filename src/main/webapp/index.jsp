<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<style>
    * {
        box-sizing: border-box
    }

    body {
        font-family: Comfortaa, Arial, Helvetica, sans-serif;
    }

    body::after {
        /*background-image: url("https://images.unsplash.com/photo-1444201983204-c43cbd584d93?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");*/
        background-image: url("img/backgroundImage.jpg");

        background-repeat: no-repeat;

        background-size: cover;

        background-position: center center;

        content: "";

        filter: alpha(5px);

        position: absolute;

        top: 0px;

        left: 0px;

        height: 100vh;

        width: 100vw;

        z-index: -1;
    }


    /*button {*/
    /*    display: block;*/
    /*    !*float: right;*!*/
    /*    background-color: #04AA6D;*/
    /*    color: white;*/
    /*    padding: 14px 20px;*/
    /*    margin: 8px 0;*/
    /*    border: none;*/
    /*    cursor: pointer;*/
    /*    width: 30%;*/
    /*    opacity: 0.9;*/
    /*}*/

    button {
        font-family: 'Comfortaa', cursive;
        display: inline-block;
        justify-content: center;
        text-transform: uppercase;
        outline: 0;
        background: #4b6cb7;
        width: 15%;
        border: 0;
        border-radius: 5px;
        padding: 15px;
        font-size: 16px;
        -webkit-transition: all 0.3 ease;
        transition: all 0.3 ease;
        cursor: pointer;
        color: #f2f2f2;
        font-weight: normal;
    }

    a {
        color: white;
        font-weight: normal;
        font-family: 'Comfortaa', cursive;
    }
</style>
<head>
    <title>Home page </title>
</head>
<body>
<h1 style="font-weight: bold; font-family: 'Comfortaa', cursive;font-style: italic"> Welcome </h1>
<h2 style="font-weight: bold; font-family: 'Comfortaa', cursive;font-style: italic">to the TASHKENT HOTEL </h2>
<br/>

<%--<form action="${pageContext.request.contextPath}/controller" method="post">--%>
<%--<a href="/login" style="color: darkcyan"> SIGN IN </a>--%>
<button type="submit" class="cancelbtn">
    <a href="${pageContext.request.contextPath}/pages/signup.jsp"> SIGN UP </a>
</button>
<button type="button" class="cancelbtn">
    <a href="${pageContext.request.contextPath}/pages/signin.jsp"> SIGN IN </a>
</button>
<%--</form>--%>
</body>
</html>

<%--/my_hotel_war_exploded--%>