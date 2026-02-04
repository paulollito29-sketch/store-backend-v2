package com.example.controller;

import com.example.dto.*;
import com.example.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeFindAll>> getEmployee() {
        var employee = employeeService.findAll();
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<EmployeeCreated> postEmployee(@RequestBody EmployeeCreate dto) {
        var employeeCreated = employeeService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employeeCreated.employeeId())
                .toUri();
        return ResponseEntity.created(location).body(employeeCreated);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeUpdated> updateEmployee(@PathVariable Long id
            , @RequestBody EmployeeUpdate dto) {
        var employeeUpdated = employeeService.update(id, dto);
        return ResponseEntity.ok(employeeUpdated);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeFindOne> findOneEmployee(@PathVariable Long id) {
        var employee = employeeService.findOne(id);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
