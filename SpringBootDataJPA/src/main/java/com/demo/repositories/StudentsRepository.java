package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Students;

@Repository
public interface StudentsRepository extends CrudRepository<Students,Integer> {

}
