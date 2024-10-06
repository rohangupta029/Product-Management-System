package com.product.Product.Management.System.RESTImpl;

import com.product.Product.Management.System.REST.ProductRest;
import com.product.Product.Management.System.Service.ProductSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
}
