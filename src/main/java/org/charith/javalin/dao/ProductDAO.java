package org.charith.javalin.dao;

import org.charith.javalin.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.charith.javalin.database.DBHelper;

public class ProductDAO {


    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();

        Connection connection = DBHelper.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM products";

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int price = resultSet.getInt("price");
            int quantity = resultSet.getInt("quantity");
            String image_url = resultSet.getString("image_url");
            String category = resultSet.getString("category");
            String brand = resultSet.getString("brand");
            Timestamp created_at = resultSet.getTimestamp("created_at");

            Product product = new Product(id, name, description, price, quantity, image_url, category, brand, created_at);

            products.add(product);
        }

        connection.close();
        return products;
    }

    public Product getProductById(int id) throws SQLException {
        Product product = null;
        String query = "SELECT * FROM products WHERE id = ?";

        Connection connection = DBHelper.getConnection();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            int price = resultSet.getInt("price");
            String description = resultSet.getString("description");
            int quantity = resultSet.getInt("quantity");
            String image_url = resultSet.getString("image_url");
            String category = resultSet.getString("category");
            String brand = resultSet.getString("brand");
            Timestamp created_at = resultSet.getTimestamp("created_at");

            product = new Product(id, name, description, price, quantity, image_url, category, brand, created_at);
        }

        connection.close();
        return product;
    }

    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO products (name, description, price, quantity, image_url, category, brand, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DBHelper.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, product.getName());
        statement.setString(2,product.getDescription());
        statement.setInt(3, product.getPrice());
        statement.setInt(4, product.getQuantity());
        statement.setString(5,product.getImageUrl());
        statement.setString(6, product.getCategory());
        statement.setString(7, product.getBrand());
        statement.setTimestamp(8, product.getCreatedAt());

        statement.executeUpdate();
        connection.close();
    }

    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE products SET name = ?, description = ?, price = ?, quantity = ?, image_url = ?, category = ?, brand = ?, created_at = ? WHERE id = ?";

        Connection connection = DBHelper.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, product.getName());
        statement.setString(2,product.getDescription());
        statement.setInt(3, product.getPrice());
        statement.setInt(4, product.getQuantity());
        statement.setString(5,product.getImageUrl());
        statement.setString(6, product.getCategory());
        statement.setString(7, product.getBrand());
        statement.setTimestamp(8, product.getCreatedAt());
        statement.setInt(9, product.getId());

        statement.executeUpdate();
        connection.close();
    }

    public void deleteProduct(int id) throws SQLException {
        String query = "DELETE FROM products WHERE id = ?";

        Connection connection = DBHelper.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        statement.executeUpdate();
        connection.close();
    }

    public List<Product> getProductsByCategory(String productCategory) throws SQLException{
        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products WHERE category = ?";
        Connection connection = DBHelper.getConnection();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, productCategory);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int price = resultSet.getInt("price");
            int quantity = resultSet.getInt("quantity");
            String image_url = resultSet.getString("image_url");
            String category = resultSet.getString("category");
            String brand = resultSet.getString("brand");
            Timestamp created_at = resultSet.getTimestamp("created_at");

            Product product = new Product(id, name, description, price, quantity, image_url, category, brand, created_at);

            products.add(product);
        }

        connection.close();
        return products;
    }
}

