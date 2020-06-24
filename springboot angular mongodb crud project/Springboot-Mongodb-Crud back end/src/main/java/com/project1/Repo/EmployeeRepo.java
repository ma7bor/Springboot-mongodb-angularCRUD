package com.project1.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project1.model.Employee;


@Repository
public interface  EmployeeRepo  extends MongoRepository<Employee, Long>{

}
