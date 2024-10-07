package com.product.Product.Management.System.serviceimpl;

import com.product.Product.Management.System.DAO.ProductDAO;
import com.product.Product.Management.System.POJO.Product;
import com.product.Product.Management.System.Service.ProductSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductServiceImpl implements ProductSerice {

    @Autowired
    ProductDAO productDAO;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try {
            if (validateNewProduct(requestMap)) {
                Product product = getProductFromMap(requestMap, false);
                productDAO.save(product);
                return new ResponseEntity<String>("{\"message\":\"" + "Product added successfully" + "\"}", HttpStatus.OK);


            } else {
                return new ResponseEntity<String>("{\"message\":\"" + "Invalid Data" + "\"}", HttpStatus.BAD_REQUEST);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<String>("{\"message\":\"" + "Something went wrong." + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateNewProduct(Map<String, String> requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("price") && requestMap.containsKey("description")) {
            return true;
        }
        return false;
    }

    private Product getProductFromMap(Map<String, String> requestMap, Boolean isAdd) {
        Product product = new Product();
        if (isAdd) {
            product.setId(Integer.parseInt(requestMap.get("productId")));
        }
        product.setName(requestMap.get("name"));
        product.setPrice(Float.parseFloat(requestMap.get("price")));
        product.setDescription(requestMap.get("description"));
        return product;
    }

}
