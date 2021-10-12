package com.kindsonthegenius.fleetapp_v2.parameters.services;

import com.kindsonthegenius.fleetapp_v2.parameters.models.Department;
import com.kindsonthegenius.fleetapp_v2.parameters.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    //Get All Departments
    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    //Get Department By Id
    public Optional<Department> findById(int id) {
        return departmentRepository.findById(id);
    }

    //Delete Department
    public void delete(int id) {
        departmentRepository.deleteById(id);
    }

    //Update Department
    public void save( Department department) {
        departmentRepository.save(department);
    }

}
