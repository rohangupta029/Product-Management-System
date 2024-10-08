package com.product.Product.Management.System.serviceimpl;

import com.product.Product.Management.System.DAO.ProductDAO;
import com.product.Product.Management.System.POJO.Product;
import com.product.Product.Management.System.Service.ProductSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductSerice {

    @Autowired
    ProductDAO productDAO;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try {
            if (validateNewProduct(requestMap, false)) {
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

    @Override
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            return new ResponseEntity<List<Product>>(productDAO.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Product>>(new ArrayList<Product>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try {
            if (validateNewProduct(requestMap, true)) {
                Optional optional = productDAO.findById(Integer.parseInt(requestMap.get("productId")));
                if (!optional.isEmpty()) {
                    productDAO.save(getProductFromMap(requestMap, true));
                    return new ResponseEntity<String>("{\"message\":\"" + "Product updated successfully" + "\"}", HttpStatus.OK);

                } else {
                    return new ResponseEntity<String>("{\"message\":\"" + "Product doesn't exist" + "\"}", HttpStatus.BAD_REQUEST);

                }


            } else {
                return new ResponseEntity<String>("{\"message\":\"" + "Invalid Data" + "\"}", HttpStatus.BAD_REQUEST);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<String>("{\"message\":\"" + "Something went wrong." + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(int productId) {
        try {
            if (productId >= 0) {
                productDAO.deleteById(productId);
                return new ResponseEntity<String>("{\"message\":\"" + "Product deleted successfully" + "\"}", HttpStatus.OK);

            } else {
                return new ResponseEntity<String>("{\"message\":\"" + "Product ID is not valid" + "\"}", HttpStatus.BAD_REQUEST);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<String>("{\"message\":\"" + "Something went wrong." + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateNewProduct(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name") && requestMap.containsKey("price") && requestMap.containsKey("description")) {
            if (validateId && requestMap.containsKey("productId")) {
                return true;
            } else if (!validateId)
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
