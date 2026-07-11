package com.springapp;

import com.springapp.model.Customer;
import com.springapp.model.Ticket;
import com.springapp.model.Category;
import com.springapp.model.Product;
import com.springapp.model.Vendor;
import com.springapp.service.CustomerService;
import com.springapp.service.TicketService;
import com.springapp.service.ProductService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Load AppConfig.java
        // This is Reaching out to Spring's context and asking it to load AppConfig.
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);
        /* Spring looks into its context, and pulls out CustomerService object and gives it to us.
        CustomerService customerService = context.getBean(CustomerService.class);
        List<Customer> list =  customerService.getAllCustomers();
        list.forEach(System.out :: println);
        System.out.println("-------Fetch by Customer id-----------");
        try {
            Customer customer = customerService.getById(1);
            System.out.println(customer);
            customer = customerService.getById(10);
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
        }

        System.out.println("----------insert Customer----------");
        Customer customer = new Customer("Harry Potter", "harry@gmail.com", "london");
        customerService.insert(customer);
        System.out.println("Customer added to DB..");


        TicketService ticketService = context.getBean(TicketService.class);
        List<Ticket> list  = ticketService.getAllWithCustomerAndEmployee();
        list.forEach(System.out :: println);
        */

        ProductService productService = context.getBean(ProductService.class);

        //Task 1:Insert Product
//        Category category = new Category();
//        category.setId(5);
//
//        Vendor vendor = new Vendor();
//        vendor.setId(5);
//
//        Product product = new Product();
//        product.setName("Racket");
//        product.setPrice(699.00);
//        product.setStockQuantity(20);
//        product.setCategory(category);
//        product.setVendor(vendor);
//
//        productService.save(product);
//        System.out.println("Product inserted successfully...");

        //Task 2:Find Product By Id
//        try{
//            Product product = productService.findById(12);
//            System.out.println(product);
//        }
//        catch(RuntimeException e){
//            System.out.println(e.getMessage());
//        }

        // Task 3:Update Stock
//        productService.updateStock(6, 35);
//        System.out.println("Stock updated successfully...");

        //Task 4 : Count Products By Vendor

        Map<String, Integer> map = productService.countProductsByVendor();

        map.forEach((vendor, count) ->
                System.out.println(vendor + " : " + count));

        context.close();

    }
}
/*
If u want all these classes to get registered, you have to tell spring to scan them right at the beginning of the app
Spring Context
--------------
CustomerController
CustomerService
CustomerUtility

JdbcTemplate
DataSource
* */
