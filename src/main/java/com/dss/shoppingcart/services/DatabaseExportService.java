package com.dss.shoppingcart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dss.shoppingcart.models.Product;
import com.dss.shoppingcart.repositories.ProductRepo;

import java.util.List;

@Service
public class DatabaseExportService {

    @Autowired
    private ProductRepo productRepo;

    public String exportDatabaseToSql() {
        List<Product> products = productRepo.findAll();
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("-- Script SQL para exportar datos de la tabla PRODUCT\n");
        sqlBuilder.append("INSERT INTO PRODUCT (ID, NAME, PRICE) VALUES\n");

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            sqlBuilder.append(String.format("(%d, '%s', %.2f)", 
                    product.getId(), 
                    product.getName().replace("'", "''"),
                    product.getPrice()));
            if (i < products.size() - 1) {
                sqlBuilder.append(",\n");
            } else {
                sqlBuilder.append(";\n");
            }
        }

        return sqlBuilder.toString();
    }
}
