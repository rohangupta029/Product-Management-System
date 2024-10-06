package com.product.Product.Management.System.Service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ProductSerice {

    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap);
}
