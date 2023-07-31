package week6task.ecommercesite2.dao;

import week6task.ecommercesite2.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;
    private String query;
//    prepared statement to execute queries
    private PreparedStatement preparedStatement;
//    result set to manipulate data returned by prepared statement
    private ResultSet resultSet;

//    constructor to pass connection to DAO
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

//    user login method
    public User userLogin(String email, String name, String password, String is_admin){
        User user = null;
        try{
//            query to retrieve email and password from database
            query = "SELECT * FROM users WHERE email = ? AND password = ?";
//            create prepared statement
            preparedStatement = this.connection.prepareStatement(query);
//            set values for email and password
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
//            As long as resultset has a next value, set email, name and is_admin based on data retrieved from Database
            if(resultSet.next()){
                user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setName(resultSet.getString("name"));
                user.setName(resultSet.getString("is_admin"));
            }
//            else call userSignUp method to register user
            else {
                userSignUp(email, name, password, is_admin);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
        return user;
    }

    public User userSignUp(String email, String name, String password, String is_admin){
        User user = null;
        try {
//            query to insert user data into database
            query = "INSERT INTO users (email, name, password, is_admin) VALUES (?, ?, ?, ?)";
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, is_admin);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}