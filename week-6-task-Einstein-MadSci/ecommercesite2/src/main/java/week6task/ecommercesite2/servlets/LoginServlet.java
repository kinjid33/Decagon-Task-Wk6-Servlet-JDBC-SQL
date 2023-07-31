package week6task.ecommercesite2.servlets;

import week6task.ecommercesite2.connection.DBConnection;
import week6task.ecommercesite2.dao.UserDAO;
import week6task.ecommercesite2.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
//    send user to login page if not authenticated
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
//      printwrite object to write back to client
        try (PrintWriter out = response.getWriter()){
//            retrieve user information
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String isAdmin = request.getParameter("as-admin");
//            user dao to perform login operation
            UserDAO userDao = new UserDAO(DBConnection.getConnection());
//            performing login operation
            User user = userDao.userLogin(email, name, password, isAdmin);
// if login is successful meaning user is not null,
// set session attribute auth to user object and redirect ot index page
            if(user != null){
//                out.print("user login successful");
                request.getSession().setAttribute("auth", user);
                response.sendRedirect("/index.jsp");
            }
//            else log out that user login failed
            else {
                out.print("user login failed");
            }
        }
    }
}
