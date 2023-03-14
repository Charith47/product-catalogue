package org.charith.javalin.controllers;

import com.google.gson.JsonObject;
import io.javalin.http.Context;

import org.charith.javalin.exceptions.NotFoundException;
import org.charith.javalin.models.Product;
import org.charith.javalin.services.ProductService;

import java.sql.SQLException;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void getAllProducts(Context ctx) {
        String category = ctx.queryParam("category");

        try {
            if (category != null) {
                System.out.println(category);
                ctx.json(productService.getAllProductsByCategory(category));
                return;
            }
            ctx.json(productService.getAllProducts());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ctx.status(500);
        }
    }

    public void getProductById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        try {
            ctx.json(productService.getProductById(id));
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            ctx.status(404);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ctx.status(500);
        }
    }

    public void addProduct(Context ctx) {
        try {
            Product product = ctx.bodyAsClass(Product.class);
            productService.addProduct(product);
            ctx.status(201);
        } catch (SQLException e) {
            ctx.status(500);
        }
    }

    public void updateProduct(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Product product = ctx.bodyAsClass(Product.class);
        try {
            productService.updateProduct(id, product);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            ctx.status(404);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ctx.status(500);
        }
    }

    public void deleteProduct(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        try {
            productService.deleteProduct(id);
            ctx.status(204);
        } catch (NotFoundException e) {
            ctx.status(404);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ctx.status(500);
        }
    }

    public void getProductSummary(Context ctx) {
        try {
            JsonObject rs = productService.getProductSummary();
            ctx.status(200);
            ctx.json(rs.toString()); // Fix this weirdness
        } catch (NotFoundException e) {
            ctx.status(404);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ctx.status(500);
        }
    }
}
