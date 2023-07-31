package week6task.ecommercesite2.servlets;

import week6task.ecommercesite2.entity.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AddToCartServlet", value = "/add-to-cart-servlet")
public class AddToCartServlet extends HttpServlet {
    private static final Long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");

        try(PrintWriter out = response.getWriter()){
//            array list to store products added to cart
            ArrayList<Cart> cartList = new ArrayList<>();

//            convert product id to int
            int productId = Integer.parseInt(request.getParameter("product_id"));
//            create new Cart instance
            Cart cart = new Cart();
//            set product Id to converted int above
            cart.setProductId(productId);
//            set initial quantity to 1
            cart.setQuantity(1);

//            Create new HttpSession object
            HttpSession session = request.getSession();
//            retrieve object stored in session object and cast it to Arraylist of carts
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
//            if cart_list is empty
            if(cart_list == null){
//                add cart instantiated above
                cartList.add(cart);
//                set session attribute "cart-list" to cartList
                session.setAttribute("cart-list", cartList);
//                then redirect user to index page so they can keep shopping
                response.sendRedirect("index.jsp");
            }
//            if cart_list is not empty
            else {
//               set cartList to cart_list
                cartList = cart_list;
//                create boolean to track if item added to cart already exists in the cat
                boolean exists = false;
//                loop through cartList to check if product added to cart is already in cart
                for(Cart carted : cartList){
//                    if product is already in cart,
                    if(carted.getProductId() == productId){
//                        set exists to true
                        exists = true;
//                        display error message and redirect to cart page if wanted
                        out.println("<h3 style = 'color:crimson; text-align:center'>Item already exists in Cart! <a href = 'cart.jsp'>Go to Cart</a></h3>");
                    }
                }
//                but if it doesn't exist in the cart already, add it to cart
//                then redirect to index page
                if(!exists){
                    cartList.add(cart);
                    response.sendRedirect("index.jsp");
                }
            }
        }
    }
}
