package com.product.Product.Management.System.RESTImpl;

import com.product.Product.Management.System.POJO.Product;
import com.product.Product.Management.System.REST.ProductRest;
import com.product.Product.Management.System.Service.ProductSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ProductRestImpl implements ProductRest {

    @Autowired
    ProductSerice productSerice;
    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try {
            return productSerice.addNewProduct(requestMap);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return new ResponseEntity<String>( "{\"message\":\""+ "Something went wrong at product service." +"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProduct() {

        try {
                return productSerice.getAllProduct();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return new ResponseEntity<List<Product>>(new ArrayList<Product>(), HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try {
            return productSerice.updateProduct(requestMap);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return new ResponseEntity<String>( "{\"message\":\""+ "Something went wrong at product service." +"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(int productId) {
        try {
               return productSerice.deleteProduct(productId);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return new ResponseEntity<String>( "{\"message\":\""+ "Something went wrong at product service." +"\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
