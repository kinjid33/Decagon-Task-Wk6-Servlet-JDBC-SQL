package week6task.ecommercesite2.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogoutServlet", value = "/logout-servlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        print writer to communicate with the client
        try (PrintWriter out = response.getWriter()){
//            check if user is logged in (when auth is not null)
            if(request.getSession().getAttribute("auth") != null){ // if user is signed in,
//                remove auth attribute from request
                request.getSession().removeAttribute("auth");
//                then redirect to login page
                response.sendRedirect("login.jsp");
            } else {
//                if not, just redirect to index page
                response.sendRedirect("index.jsp");
            }
        }
    }
}