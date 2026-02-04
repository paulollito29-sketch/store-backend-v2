package com.example.service;

import com.example.dto.*;
import com.example.entity.EmployeeEntity;
import com.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeFindAll> findAll() {
        return employeeRepository.findAllByEnabledIsTrueOrderByIdEmployeeDesc().stream()
                .map(item -> new EmployeeFindAll(item.getIdEmployee(), item.getFirstName(), item.getLastName(), item.getAge(), item.getSalary()))
                .toList();
    }

    public EmployeeCreated create(EmployeeCreate dto) {
        if (employeeRepository.existsByEnabledIsTrueAndFirstNameIgnoreCase(dto.firstName())) {
            throw new RuntimeException("this name: " + dto.firstName() + " already exists");
        }
        var employeeEntity = EmployeeEntity.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .age(dto.age())
                .salary(dto.salary())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
        var employeeSaved = employeeRepository.save(employeeEntity);
        return new EmployeeCreated(employeeSaved.getIdEmployee(), employeeSaved.getFirstName(), employeeSaved.getLastName(), employeeSaved.getAge(), employeeSaved.getSalary());
    }

    public EmployeeFindOne findOne(Long id) {
        var employee = employeeRepository.findFirstByEnabledIsTrueAndIdEmployee(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return new EmployeeFindOne(employee.getIdEmployee(), employee.getFirstName(), employee.getLastName(), employee.getAge(), employee.getSalary());
    }

    public EmployeeUpdated update(long id, EmployeeUpdate dto) {
        var employeeUpdated = employeeRepository.findFirstByEnabledIsTrueAndIdEmployee(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        if (employeeRepository.existsByEnabledIsTrueAndIdEmployeeNotAndFirstNameIgnoreCaseAndLastNameIgnoreCase(id, employeeUpdated.getFirstName(), employeeUpdated.getLastName())) {
            throw new RuntimeException("this name" + dto.firstName() + " " + dto.lastName() + " already exists");
        }
        employeeUpdated.setFirstName(dto.firstName());
        employeeUpdated.setLastName(dto.lastName());
        employeeUpdated.setAge(dto.age());
        employeeUpdated.setSalary(dto.salary());
        employeeUpdated.setUpdatedAt(LocalDateTime.now());
        var employeeSaved = employeeRepository.save(employeeUpdated);
        return new EmployeeUpdated(employeeUpdated.getIdEmployee(), employeeUpdated.getFirstName(), employeeUpdated.getLastName(), employeeUpdated.getAge(), employeeUpdated.getSalary());
    }

    public void delete(Long id) {
        var employee = employeeRepository.findFirstByEnabledIsTrueAndIdEmployee(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setEnabled(false);
        employee.setDeletedAt(LocalDateTime.now());
        employeeRepository.save(employee);
    }

    public void enable(Long id) {
        var employee = employeeRepository.findFirstByEnabledIsTrueAndIdEmployee(id)
                .orElseThrow(() -> new RuntimeException("Employeee not found"));
        employee.setEnabled(true);
        employee.setUpdatedAt(LocalDateTime.now());
        employeeRepository.save(employee);
    }

}