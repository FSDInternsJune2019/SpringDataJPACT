package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Customers;

@Repository
public interface CustomersRepository extends CrudRepository<Customers,Integer>{

}
