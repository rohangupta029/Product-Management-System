package com.product.Product.Management.System.DAO;

import com.product.Product.Management.System.POJO.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Integer> {
}
