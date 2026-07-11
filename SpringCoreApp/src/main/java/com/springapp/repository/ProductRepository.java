package com.springapp.repository;

import com.springapp.model.Product;
import com.springapp.utility.ProductUtility;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ProductUtility productUtility;

    public ProductRepository(JdbcTemplate jdbcTemplate,
                             ProductUtility productUtility) {
        this.jdbcTemplate = jdbcTemplate;
        this.productUtility = productUtility;
    }

    // Task 1
    public void save(Product product) {

        String sql = """
                insert into products
                (name,price,stock_quantity,category_id,vendor_id)
                values(?,?,?,?,?)
                """;

        Object[] values = new Object[]{
                product.getName(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory().getId(),
                product.getVendor().getId()
        };

        jdbcTemplate.update(sql, values);
    }

    // Task 2
    public Optional<Product> findById(int id) {

        String sql = """
                select p.id,
                       p.name,
                       p.price,
                       p.stock_quantity,
                       c.id as category_id,
                       c.name as category_name,
                       v.id as vendor_id,
                       v.name as vendor_name
                from products p
                join categories c
                on p.category_id=c.id
                join vendors v
                on p.vendor_id=v.id
                where p.id=?
                """;

        return jdbcTemplate.query(sql, productUtility, id)
                .stream()
                .findFirst();
    }

    // Task 3
    public void updateStock(int productId, int newQuantity) {

        String sql = """
                update products
                set stock_quantity=?
                where id=?
                """;

        jdbcTemplate.update(sql, newQuantity, productId);
    }

    // Task 4
    public Map<String, Integer> countProductsByVendor() {

        String sql = """
                select v.name,
                       count(p.id) as total
                from vendors v
                join products p
                on v.id=p.vendor_id
                group by v.name
                """;

        Map<String, Integer> map = new HashMap<>();

        jdbcTemplate.query(sql, rst -> {
            map.put(
                    rst.getString("name"),
                    rst.getInt("total")
            );
        });

        return map;
    }

}