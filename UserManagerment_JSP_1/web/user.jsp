<%-- 
    Document   : user
    Created on : Sep 22, 2025, 5:07:55 PM
    Author     : MSII
--%>

<%@page import="fa25.User.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USER Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'US'}">
            <c:redirect url="login.jsp"><c:redirect>
        </c:if>
            
        User ID: ${sessionScope.LOGIN_USER.userID}</br>
        Full Name: ${sessionScope.LOGIN_USER.fullName}</br>
        Role :ID ${sessionScope.LOGIN_USER.roleID}</br>
        Password: ${sessionScope.LOGIN_USER.password}
        <a href ="shopping.jsp">Shopping</a>
    </body>
</html>
