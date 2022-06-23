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
    <title>JSP - Hello World</title>
</head>
<body>
<h1 style="font-weight: bold;">Home Page
</h1>
<br/>

<%--<a href="/login" style="color: darkcyan"> SIGN IN </a>--%>
<button type="button" class="cancelbtn">
    <a href="/signup"> SIGN UP </a>
</button>
<button type="button" class="cancelbtn">
    <a href="/signin"> SIGN IN </a>
</button>

</body>
</html>

<%--/my_hotel_war_exploded--%>