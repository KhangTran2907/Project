<%-- 
    Document   : login
    Created on : Sep 22, 2025, 4:15:12 PM
    Author     : MSII
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            User ID:<input type="text" name="userID" required=""/></br>
            Password<input type="password" name="password" required=""/></br>
            <input type="submit" name="action" value="Login"/>
            <input type="reset" value="Reset"/>
            <input type="hidden" name="pass" value="12345"/>
        </form>
        ${requestScope.ERROR}
        <form action="MainController" method="POST">
            <input type="submit" name="action" value="Create View"/>
        </form>
        <a href="MainController?action=Create View">Create User</a>
    </body>
</html>
