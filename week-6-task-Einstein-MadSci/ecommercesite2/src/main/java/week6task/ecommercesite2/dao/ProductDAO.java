package week6task.ecommercesite2.dao;

import week6task.ecommercesite2.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
//    connection to reach the database
    private Connection connection;
//    query for CRUD operations with database
    private String query;
//    prepared statement to acccept query
    private PreparedStatement preparedStatement;
//    result set to manipulate data retrieved from database
    private ResultSet resultSet;

//    constructor to allow connection to be made
    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

//    get all products method
    public List<Product> getAllProducts(){
//        array list of products to contain products
        List<Product> productsList = new ArrayList<>();
        try {
//            query to retrieve all products in the database
            query = "SELECT * FROM products";
//            create prepared statement to communicate with the database
            preparedStatement = this.connection.prepareStatement(query);
//            execute the query
            resultSet = preparedStatement.executeQuery();
//            while there are values in the result set,
            while(resultSet.next()){
//                create new product object to contain each product's details
                Product row = new Product();
//                populate row with values based on the column names in the database table
                row.setProductId(resultSet.getInt("product_id"));
                row.setCategory(resultSet.getString("category"));
                row.setName(resultSet.getString("name"));
                row.setPrice(resultSet.getDouble("price"));
                row.setImage(resultSet.getString("image"));

//               add row to productList array list
                productsList.add(row);
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return productsList;
    }

//    get cart products method
    public List<Cart> getCartProducts(List<Cart> cartList){
//        array list of type cart to hold details of products in cart
        List<Cart> productsInCart = new ArrayList<>();

        try{
//            if cartList size is greater than zero, it means there is something in the cart, therefore execute below
            if(cartList.size() > 0){
//                loop through cartList
                for(Cart item : cartList){
//                    query to retrieve all products from product table where id is dynamically given
                    query = "SELECT * FROM products WHERE id = ?";
//                    create prepared statement to communicate with the database
                    preparedStatement = this.connection.prepareStatement(query);
//                    provide value for ? above
                    preparedStatement.setInt(1, item.getProductId());
//                    execute the query
                    resultSet = preparedStatement.executeQuery();

//                    while there are still values in the result set
                    while(resultSet.next()){
//                        create new cart object called row
                        Cart row = new Cart();
//                        set values for fields in row
                        row.setProductId(resultSet.getInt("product_id"));
                        row.setCategory(resultSet.getString("category"));
                        row.setName(resultSet.getString("name"));
                        row.setPrice(resultSet.getDouble("price") * item.getQuantity());
                        row.setQuantity(item.getQuantity());
//                        add row to products in cart list
                        productsInCart.add(row);
                    }
                }
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }

        return productsInCart;
    }
}
