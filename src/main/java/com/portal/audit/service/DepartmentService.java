package com.portal.audit.service;

import com.portal.audit.aspect.LogEvent;
import com.portal.audit.entity.Department;
import com.portal.audit.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @LogEvent(action = "ADD DEPARTMENT")
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @LogEvent(action = "UPDATE DEPARTMENT")
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @LogEvent(action = "UPDATE DEPARTMENT")
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
