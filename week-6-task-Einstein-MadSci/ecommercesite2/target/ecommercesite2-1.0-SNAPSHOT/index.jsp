<%@ page import="week6task.ecommercesite2.connection.DBConnection" %>
<%@ page import="week6task.ecommercesite2.entity.*" %>
<%@ page import="week6task.ecommercesite2.dao.ProductDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- - check if user is authenticated (logged in)
     retrieve auth attribute from user request
     if auth is not null (meaning user is logged in)
     sets "auth" of current request object to "auth" retrieved from the HttpSession
     method of the "request" object.

     - instantiate productDAO and pass DBConnection to it

     - create list of products and set it to all products gotten from DAO
     --%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth", auth);
    }

    ProductDAO productDao = new ProductDAO(DBConnection.getConnection());
    List<Product> productsList = productDao.getAllProducts();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Akinjide's E-Commerce Site</title>
<%--    include head jsp (css and fontawesome imports)--%>
    <%@ include file="includes/head.jsp"%>

</head>
<body>
<%--    include navbar jsp--%>
    <%@ include file="includes/navbar.jsp"%>

<%--    container div--%>
    <div class = "container">
<%--        card header div--%>
        <div class = "card-header my-3">
            Our Products
        </div>
<%--     div for each row of products--%>
        <div class = "row">
<%--    if product list is not empty, populate page with product image, name, category & price
        Also, when "add to cart" is clicked, set product_id to selected product's id--%>
            <%  if(!productsList.isEmpty()){
                for(Product product : productsList){%>
                    <div class = "col-md-5 my-3">
                        <div class="card w-100" style="width: 18rem;">
                            <img class="card-img-top" src="productimages/<%= product.getImage() %>" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title"> <%= product.getName() %> </h5>
                                <h6 class = "price">Price: $<%= product.getPrice() %> </h6>
                                <h6 class = "category">Category: <%= product.getCategory() %> </h6>
                                <div class= "mt-3 d-flex justify-content-between">
                                    <a href = "add-to-cart-servlet?product_id=<%=product.getProductId()%>" class="btn btn-primary">Add to Cart</a>
                                    <a href="#" class="btn btn-primary">Buy</a>
                                    <a href="#" class="btn btn-primary">Add to Wishlist</a>
                                </div>
                            </div>
                        </div>
                    </div>
                <%}
            }
        %>

        </div>
    </div>

<%--    <%@ include file="includes/footer.jsp"%>--%>
</body>
</html>