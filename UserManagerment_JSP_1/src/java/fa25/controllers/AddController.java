/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fa25.controllers;

import fa25.shopping.Cart;
import fa25.shopping.Product;
import fa25.shopping.ProductDAO;
import java.io.IOException;
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
@WebServlet(name = "AddController", urlPatterns = {"/AddController"})
public class AddController extends HttpServlet {

    private static final String ERROR = "shopping.jsp";
    private static final String SUCCESS = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            // Lấy thông tin sản phẩm từ form
            String cmbProduct = request.getParameter("cmbProduct");
            if (cmbProduct == null || cmbProduct.trim().isEmpty()) {
                request.setAttribute("MESSAGE", "Không có sản phẩm nào được chọn!");
            } else {
                String[] tmp = cmbProduct.split("-");
                String id = tmp[0];
                String name = tmp[1];
                double price = Double.parseDouble(tmp[2]);
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                // Lấy giỏ hàng từ session
                HttpSession session = request.getSession();
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart == null) {
                    cart = new Cart();
                }

                // Tạo sản phẩm mới
                Product product = new Product(0, id, name, price, quantity);

                // Thêm sản phẩm vào giỏ
                boolean added = cart.add(product);

                if (added) {
                    session.setAttribute("CART", cart);
                    request.setAttribute("MESSAGE", "Đã thêm <b>" + name + "</b> với số lượng <b>" + quantity + "</b> vào giỏ hàng!");
                } else {
                    request.setAttribute("MESSAGE", "Không đủ hàng trong kho!");
                }

                // Load lại danh sách sản phẩm
                ProductDAO dao = new ProductDAO();
                List<Product> list = dao.getAllProducts();
                request.setAttribute("PRODUCT_LIST", list);
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at AddController: " + e.toString());
            request.setAttribute("MESSAGE", "Đã xảy ra lỗi khi thêm sản phẩm!");
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
