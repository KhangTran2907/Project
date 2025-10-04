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
        <%
          UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
          if (loginUser == null) {
              loginUser = new UserDTO();
          }
        %> 
        User ID <%= loginUser.getUserID()%></br>
        Full Name <%= loginUser.getFullName()%></br>
        Role ID <%= loginUser.getRoleID()%></br>
        Password <%= loginUser.getPassword()%>
    </body>
</html>
