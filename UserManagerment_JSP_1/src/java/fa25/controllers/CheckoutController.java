/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fa25.controllers;

import fa25.shopping.Cart;
import fa25.shopping.OrderDAO;
import fa25.shopping.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MSII
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})

public class CheckoutController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "shopping.jsp";
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");

            if (cart == null || cart.getCart().isEmpty()) {
                request.setAttribute("MESSAGE", "Giỏ hàng trống, không thể thanh toán!");
            } else {
                List<Product> items = new ArrayList<>(cart.getCart().values());
                double total = cart.getTotal();

                OrderDAO dao = new OrderDAO();
                int orderId = dao.createOrder(total);
                if (orderId > 0) {
                    dao.saveOrderDetails(orderId, items);
                    session.removeAttribute("CART");
                    request.setAttribute("MESSAGE", "Thanh toán thành công! Mã đơn hàng: " + orderId);
                } else {
                    request.setAttribute("MESSAGE", "Lỗi khi tạo đơn hàng!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("MESSAGE", "Đã xảy ra lỗi khi thanh toán!");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
