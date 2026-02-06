package com.example.service;

import com.example.dto.*;
import com.example.entity.EmployeeEntity;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.EmployeeMapper;
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
                .map(EmployeeMapper::toEmployeeFindAll)
                .toList();
    }

    public EmployeeCreated create(EmployeeCreate dto) {
        if (employeeRepository.existsByEnabledIsTrueAndFirstNameIgnoreCase(dto.firstName())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.firstName() + " already exists");
        }
        var employeeEntity = EmployeeMapper.toEntityCreate(dto);
        var employeeSaved = employeeRepository.save(employeeEntity);
        return EmployeeMapper.toEmployeeCreated(employeeSaved);
    }

    public EmployeeFindOne findOne(Long id) {
        var employee = employeeRepository.findFirstByEnabledIsTrueAndIdEmployee(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return EmployeeMapper.toEmployeeFindOne(employee);
    }

    public EmployeeUpdated update(long id, EmployeeUpdate dto) {
        var employee = employeeRepository.findFirstByEnabledIsTrueAndIdEmployee(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (employeeRepository.existsByEnabledIsTrueAndIdEmployeeNotAndFirstNameIgnoreCaseAndLastNameIgnoreCase(employee.getIdEmployee(), employee.getFirstName(), employee.getLastName())) {
            throw new ResourceAlreadyExistsException("this name" + dto.firstName() + " " + dto.lastName() + " already exists");
        }
        var employeeUpdated = EmployeeMapper.toEntityUpdate(employee, dto);
        var employeeSaved = employeeRepository.save(employeeUpdated);
        return EmployeeMapper.toEmployeeUpdated(employeeSaved);
    }

    public void delete(Long id) {
        var employee = employeeRepository.findFirstByEnabledIsTrueAndIdEmployee(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        var employeeDeleted = EmployeeMapper.toEntityDeleted(employee);
        employeeRepository.save(employeeDeleted);
    }

}