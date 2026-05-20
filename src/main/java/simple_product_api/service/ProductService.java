package simple_product_api.service;

import org.springframework.stereotype.Service;
import simple_product_api.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {

        products.add(new Product(1, "Laptop", 50000));
        products.add(new Product(2, "Phone", 25000));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {

        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product addProduct(Product product) {

        products.add(product);
        return product;
    }
}

