package com.example.customermanagement.service;

import com.example.customermanagement.entity.Customer;
import com.example.customermanagement.repository.CustomerRepository;
import com.example.customermanagement.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseData<Customer> createCustomer(Customer customer){
        ResponseData<Customer> responseData = new ResponseData<Customer>();

        Customer existsEmail = customerRepository.findByEmail(customer.getEmail());
        if (existsEmail != null){
            responseData.setStatus(false);
            responseData.setMessage("Email already exists!");
            responseData.setData(null);
        } else {
            customer.setCreated_at(new Date());
            customer.setUpdated_at(new Date());
            customerRepository.save(customer);
            responseData.setStatus(true);
            responseData.setMessage("Customer created successfully!!");
            responseData.setData(customer);
        }

        return responseData;
    }

    public ResponseData<Customer> getCustomerById(int id){
        ResponseData<Customer> responseData = new ResponseData<Customer>();

       Optional<Customer>  customer = customerRepository.findById(id);
       if(customer.isPresent()){
           responseData.setStatus(true);
           responseData.setMessage("Customer Found!");
           responseData.setData(customer.get());
       }else{
           responseData.setStatus(false);
           responseData.setMessage("Customer Not Found!");
       }
       return responseData;
    }

    public ResponseData<Customer> updateCustomer(int id,Customer customer){
        ResponseData<Customer> responseData = new ResponseData<Customer>();

        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null){
            existingCustomer.setF_name(customer.getF_name());
            existingCustomer.setL_name(customer.getL_name());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone_number(customer.getPhone_number());
            existingCustomer.setUpdated_at(new Date());
            customerRepository.save(existingCustomer);

            responseData.setStatus(true);
            responseData.setMessage("Customer updated successfully!");
            responseData.setData(customer);
        } else {
            responseData.setStatus(false);
            responseData.setMessage("Customer not found!");
            responseData.setData(null);
        }

        return responseData;
    }

    public ResponseData<Customer> deleteCustomer(int id){
        ResponseData<Customer> responseData = new ResponseData<Customer>();
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isEmpty()){
            customerRepository.deleteById(id);
            responseData.setStatus(true);
            responseData.setMessage("Customer deleted successfully!");
        }else{
            responseData.setMessage("Customer Not Found!");
            responseData.setStatus(false);
        }

        return responseData;
    }

    public ResponseData<List<Customer>> getAllCustomers(){
        ResponseData<List<Customer>> responseData = new ResponseData<List<Customer>>();
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()){
            responseData.setStatus(false);
            responseData.setMessage("No customers");
        } else {
            responseData.setStatus(true);
            responseData.setMessage("Customer list");
            responseData.setData(customers);
        }
        return responseData;
    }

    public ResponseData<List<Customer>> getCustomerByName(String f_name){
        ResponseData<List<Customer>> responseData = new ResponseData<List<Customer>>();
        List<Customer> customers = customerRepository.findProductByName(f_name);
        if (customers.isEmpty()){
            List<Customer> customerList = customerRepository.findAll();
            responseData.setData(customerList);
            responseData.setStatus(true);
            responseData.setMessage("All Customer List");
        } else {
            responseData.setStatus(true);
            responseData.setMessage("Customer list by name");
            responseData.setData(customers);
        }
        return responseData;
    }

    public ResponseData<Long> getTotalCustomerCount(){
        ResponseData<Long> responseData = new ResponseData<Long>();
        long totalCustomerCount = customerRepository.count();
        responseData.setData(totalCustomerCount);
        responseData.setMessage("Total customer count");
        responseData.setStatus(true);

        return responseData;
    }

}
