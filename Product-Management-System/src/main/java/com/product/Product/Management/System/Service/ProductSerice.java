package com.product.Product.Management.System.Service;

import com.product.Product.Management.System.POJO.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductSerice {

    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap);

    public ResponseEntity<List<Product>> getAllProduct();

    public ResponseEntity<String> updateProduct(Map<String, String> requestMap);

    public ResponseEntity<String> deleteProduct(int productId);
}
