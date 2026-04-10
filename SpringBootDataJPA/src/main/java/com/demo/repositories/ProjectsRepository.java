package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Projects;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects,String> {

}
