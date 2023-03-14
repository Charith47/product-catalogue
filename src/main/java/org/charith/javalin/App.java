package org.charith.javalin;

import io.javalin.Javalin;

import org.charith.javalin.controllers.ProductController;
import org.charith.javalin.dao.ProductDAO;
import org.charith.javalin.services.ProductService;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> {
                cors.add(it -> {
                    it.anyHost();
                });
            });
        });

        app.get("/", ctx -> ctx.html("Hello from Server!"));

        ProductController productController = new ProductController(new ProductService(new ProductDAO()));

        app.get("/products", productController::getAllProducts);
        app.get("/products/{id}", productController::getProductById);
        app.post("/products", productController::addProduct);
        app.put("/products/{id}", productController::updateProduct);
        app.delete("/products/{id}", productController::deleteProduct);

        app.get("/summary", productController::getProductSummary);

        app.start(3000);
    }
}