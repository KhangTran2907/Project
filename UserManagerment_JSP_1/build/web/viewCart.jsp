<%-- 
    Document   : viewCart
    Created on : Oct 20, 2025, 3:13:40 PM
    Author     : MSII
--%>

<%@page import="fa25.shopping.Product"%>
<%@page import="fa25.shopping.Cart"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Giỏ hàng của bạn</title>
</head>
<body>

<h1>Giỏ hàng của bạn</h1>

<%
    Cart cart = (Cart) session.getAttribute("CART");
    String message = (String) request.getAttribute("MESSAGE");
    if (message == null) message = "";
%>

<!-- Hiển thị thông báo -->
<p style="color:green;"><%= message %></p>

<% if (cart == null || cart.getCart().isEmpty()) { %>
    <h3>Giỏ hàng hiện đang trống!</h3>
    <form action="MainController" method="POST">
        <input type="submit" name="action" value="LoadProduct"/>
    </form>
<% } else { %>
    <table border="1" cellpadding="8">
        <tr>
            <th>No</th>
            <th>ID</th>
            <th>Name</th>
            <th>Price ($)</th>
            <th>Quantity</th>
            <th>Total ($)</th>
            <th>Action</th>
        </tr>

        <%
            int index = 1;
            double grandTotal = 0;
            for (Map.Entry<String, Product> entry : cart.getCart().entrySet()) {
                Product p = entry.getValue();
                double total = p.getPrice() * p.getQuantity();
                grandTotal += total;
        %>
            <tr>
                <td><%= index++ %></td>
                <td><%= p.getId() %></td>
                <td><%= p.getName() %></td>
                <td><%= p.getPrice() %></td>
                <td><%= p.getQuantity() %></td>
                <td><%= total %></td>
                <td>
                    <form action="MainController" method="POST">
                        <input type="hidden" name="id" value="<%= p.getId() %>"/>
                        <input type="submit" name="action" value="Remove"/>
                    </form>
                </td>
            </tr>
        <% } %>

        <tr>
            <td colspan="5" align="right"><strong>Tổng cộng:</strong></td>
            <td colspan="2"><strong><%= grandTotal %> $</strong></td>
        </tr>
    </table>

    <br>
    <!-- Nút Checkout -->
    <form action="MainController" method="POST">
        <input type="submit" name="action" value="Checkout"/>
    </form>

    <!-- Nút quay lại mua hàng -->
    <form action="MainController" method="POST">
        <input type="submit" name="action" value="LoadProduct"/>
    </form>
<% } %>

</body>
</html>

