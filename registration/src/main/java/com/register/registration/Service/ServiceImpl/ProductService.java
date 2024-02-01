package com.register.registration.Service.ServiceImpl;

import com.register.registration.dto.ProductDTO;
import com.register.registration.entity.Product;
import com.register.registration.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductCost(productDTO.getProductCost());
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDTO updatedProductDTO) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setProductName(updatedProductDTO.getProductName());
            existingProduct.setProductCost(updatedProductDTO.getProductCost());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Deleted Successfully");
        } else {
            return ResponseEntity.status(404).body("Product not found");
        }
    }
}
