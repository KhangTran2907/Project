<%-- 
    Document   : shopping
    Created on : Oct 16, 2025, 4:08:51 PM
    Author     : MSII
--%>
<%@page import="fa25.shopping.ProductDAO"%>
<%@page import="fa25.User.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="fa25.shopping.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tiệm vàng Kim Khánh</title>
</head>
<body>
<%
    // Lấy user đăng nhập (nếu có)
    UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
    if (loginUser == null) {
        // Nếu chưa login thì không redirect, chỉ để tránh lỗi null
        loginUser = new UserDTO();
    }

    // Lấy message thông báo (ví dụ: "Đã thêm sản phẩm...") từ request
    String message = (String) request.getAttribute("MESSAGE");
    if (message == null) message = "";

    // Lấy danh sách sản phẩm
    ProductDAO dao = new ProductDAO();
    List<Product> listProduct = dao.getAllProducts();
%>

<h1>Chào mừng đến với tiệm vàng Kim Khánh</h1>

<!-- Hiển thị thông báo sau khi Add -->
<% if (!message.isEmpty()) { %>
    <p style="color:green;"><%= message %></p>
<% } %>

<!-- Bảng hiển thị danh sách sản phẩm -->
<table border="1" cellpadding="8">
    <tr>
        <th>No</th>
        <th>ID</th>
        <th>Name</th>
        <th>Price ($)</th>
        <th>Quantity Left</th>
        <th>Add</th>
    </tr>
    <% 
        if (listProduct != null && !listProduct.isEmpty()) {
            for (Product p : listProduct) { 
    %>
        <tr>
            <td><%= p.getNo() %></td>
            <td><%= p.getId() %></td>
            <td><%= p.getName() %></td>
            <td><%= p.getPrice() %></td>
            <td><%= p.getQuantity() %></td>
            <td>
                <!-- Nút Add sản phẩm -->
                <form action="MainController" method="POST" style="display:inline;">
                    <input type="hidden" name="cmbProduct" 
                           value="<%= p.getId() + "-" + p.getName() + "-" + p.getPrice() %>"/>
                    <select name="quantity">
                        <% for (int i = 1; i <= 5; i++) { %>
                            <option value="<%= i %>"><%= i %></option>
                        <% } %>
                    </select>
                    <input type="submit" name="action" value="Add"/>
                </form>
            </td>
        </tr>
    <% 
            } // end for
        } else { 
    %>
        <tr><td colspan="6">Không có sản phẩm</td></tr>
    <% } %>
</table>

<!-- Nút xem giỏ hàng -->
<form action="MainController" method="POST" style="margin-top:20px;">
    <input type="submit" name="action" value="View"/>
</form>

</body>
</html>






