<%--
  Created by IntelliJ IDEA.
  User: dec
  Date: 22/03/2023
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>

<%--Cart page--%>

<%--setting page content type--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--importing everything from entity package--%>
<%@ page import="week6task.ecommercesite2.entity.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--importing product DAO--%>
<%@ page import="week6task.ecommercesite2.dao.ProductDAO" %>
<%--importing database connection--%>
<%@ page import="week6task.ecommercesite2.connection.DBConnection" %>

<%-- (line 24 - 27) Check if user is auhtenticated, if not redirect to index page--%>
<%-- (line 30 - 36) retrieves list of products added to cart and sets them as an attribute
of the current request object.--%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth == null) {
        response.sendRedirect("index.jsp");
    }

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if (cart_list != null) {
        ProductDAO productDao = new ProductDAO(DBConnection.getConnection());
        cartProduct = productDao.getCartProducts(cart_list);
        request.setAttribute("cart_list", cart_list);
    }
%>
<html>
<head>
    <title>Akinjide's | Cart</title>
<%--    Line 32 imports the head jsp file--%>
    <%@ include file="includes/head.jsp" %>
    <style type="text/css">
        .table tbody td {
            vertical-align: middle;
        }

        .btn-incre, .btn-decre {
            box-shadow: none;
            font-size: 25px;
        }
    </style>
</head>
<body>
<%--    Line 46 imports the navbar jsp file--%>
<%@ include file="includes/navbar.jsp" %>

<%--    Line 49 is a div that contains teh cart page--%>
<div class="container">
    <div class="d-flex py-3">
<%--        Total price header and checlout button--%>
        <h3>Total Price: $</h3><a class="mx-3 btn-primary btn" href="#">Check Out</a>
    </div>
<%--    table to contain cart items--%>
    <table class="table table-light">
        <thead>
<%--        columns of table to represent Name, Category etc--%>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Price</th>
            <th scope="col">Buy</th>
            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
<%--        If cart is not empty, loop through cartProduct and display items in cart --%>
        <%
            if (cart_list != null) {
                for (Cart carted3 : cartProduct) {
        %>
        <tr>
            <td>
                <%=carted3.getName()%>
<%--                Name--%>
            </td>
            <td>
                <%=carted3.getCategory()%>
<%--                Category--%>
            </td>
            <td>
                $<%=carted3.getPrice()%>
<%--                Price--%>
            </td>
            <td>
<%--                This form shows the number of items in the cart and has th increment and decrement buttons for qty to buy--%>
                <form action="" method="post" class="form-inline">
                    <input type="hidden" name="id" value="1" class="form-input"/>
                    <div class="form-group d-flex justify-content-between">
                        <a class="btn btn-sm btn-incre" href=""><i class="fas fa-plus-square"></i></a>
                        <input type="text" name="quantity" class="form-control" value="1" readonly/>
                        <a class="btn btn-sm btn-decre" href=""><i class="fas fa-minus-square"></i></a>
                    </div>
                </form>
            </td>
<%--            Remove item from cart button--%>
            <td>
                <a class="btn btn-sm btn-danger" href="">Remove</a>
            </td>
        </tr>

        <%
                }
            }
        %>
        </tbody>
    </table>
</div>

<%--<%@ include file="includes/footer.jsp" %>--%>
</body>
</html>
