package com.exampleCA218.demo;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final JdbcTemplate jdbcTemplate;

    public CustomerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //operations
    //add customer
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customer values(? , ?)";
        jdbcTemplate.update(sql, customer.getId(), customer.getName() );
    }

    //select all customers
    public List<Customer> getAllCustomers(){
        String sql = "SELECT * FROM customer";
      return   jdbcTemplate.query(sql ,
                new BeanPropertyRowMapper<>(Customer.class));
    }

    //select customer by id
    public Customer getCustomerById(int id){
        String sql = "SELECT * FROM customer where id = ?";
        try {
            return   jdbcTemplate.queryForObject(sql ,
                    new Object[]{id},
                    new BeanPropertyRowMapper<>(Customer.class));
        } catch (EmptyResultDataAccessException e) {
           return null;
        }
    }
    //delete customer
    public void deleteCustomer(int id){
        String sql = "DELETE FROM customer WHERE id = ?";
        jdbcTemplate.update(sql , id);
    }

    //update customer
    public void updateCustomer(Customer customer){
        String sql = "UPDATE customer SET name = ? where id =?";
        jdbcTemplate.update(sql , customer.getName(), customer.getId());
    }
}
