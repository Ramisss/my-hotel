<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07.08.2022
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500</title>
</head>
<body>
<h2 style="color: #4b6cb7">500</h2>
<h2 style="color: #4b6cb7">Server Error.</h2>

Request from : ${pageContext.errorData.requestURI} is failed <br/>
Servlet name : ${pageContext.errorData.servletName} <br/>
Status code : ${pageContext.errorData.statusCode} <br/>
Exception : ${pageContext.exception} <br/>
Exception : ${pageContext.exception.message} <br/>
</body>
</html>
