package org.charith.javalin.services;

import org.charith.javalin.models.Product;
import org.charith.javalin.dao.ProductDAO;
import org.charith.javalin.exceptions.NotFoundException;

import java.sql.*;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    public Product getProductById(int id) throws SQLException {
        Product product = productDAO.getProductById(id);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        return product;
    }

    public void addProduct(Product product) throws SQLException {
        productDAO.addProduct(product);
    }

    public void updateProduct(int id, Product updatedProduct) throws SQLException {
        Product product = getProductById(id);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        updatedProduct.setId(id);
        productDAO.updateProduct(updatedProduct);
    }

    public void deleteProduct(int id) throws SQLException {
        Product product = getProductById(id);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        productDAO.deleteProduct(id);
    }

    public List<Product> getAllProductsByCategory(String category) throws SQLException {
        return productDAO.getProductsByCategory(category);
    }
}