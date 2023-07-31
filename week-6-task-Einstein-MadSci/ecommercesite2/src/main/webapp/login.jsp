<%--
  Created by IntelliJ IDEA.
  User: dec
  Date: 22/03/2023
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="week6task.ecommercesite2.entity.*" %>

<%--retrieve auth attribute from user request,
     check if auth is not null (meaning user is logged in), if auth is not null,
     redirect user to index page--%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
<head>
    <title>Title</title>
    <%@ include file="includes/head.jsp"%>
</head>
<body>
    <%@ include file="includes/navbar.jsp"%>
    <div class = "container">

    <div class = "card w-50 mx-auto my-5">

<%--        <div class = "card-header text-center">--%>
<%--            <h1>Welcome to my Store</h1>--%>
<%--        </div>--%>

<%--    form for login / register--%>
        <div class = "card-body">
            <form action = "/login-servlet" method = "post">
                <div class = "text-center">
                    <h4>What is your email address?</h4>
                    <p>Type an email to login or register</p>
                </div>

                <div class = "form-group">
                    <label for = "email">Email</label>
                    <br/>
                    <input class = "form-control" type = "email" name = "email" id = "email" placeholder = "Email" required/>

                    <br/>

                    <label for = "name">Name</label>
                    <br/>
                    <input class = "form-control" type = "text" name = "name" id = "name" placeholder = "Enter your name" required/>

                    <br/>

                    <label for = "password">Password</label>
                    <br/>
                    <input class = "form-control" type = "password" name = "password" id = "password" placeholder = "Password"/>

                    <br/>

                    <p>Login or register as
                        <label for = "as-admin"></label>
                        <select id = "as-admin" name = "as-admin">
                            <option value = "admin">Administrator</option>
                            <option value = "customer">Customer</option>
                        </select>
                    </p>

                </div>

                <div class = "text-center">
                    <button class = "form-control btn btn-primary" type = "submit">Continue</button>
                </div>


            </form>

<%--            blocks of code below check if user data is valid or not and alerts accordingly--%>
            <%
                String completionStatusMsg = request.getParameter("msg");
                if("valid".equals(completionStatusMsg)){ %>
            <h1>Account Successfully Created</h1>
            <%
                }
            %>

            <%
                if("invalid".equals(completionStatusMsg)){ %>
            <h1>Something went wrong! Try again!</h1>
            <%  }

            %>
        </div>

    </div>
<%--    <%@ include file="includes/footer.jsp"%>--%>
</body>
</html>
