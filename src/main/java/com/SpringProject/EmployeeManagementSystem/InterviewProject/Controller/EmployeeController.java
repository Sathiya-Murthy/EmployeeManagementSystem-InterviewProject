package com.SpringProject.EmployeeManagementSystem.InterviewProject.Controller;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Employee;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("/{orgid}/add")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee, @PathVariable int orgid){

        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee,orgid), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Employee> getEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/get/{empid}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int empid) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(empid), HttpStatus.OK);
    }

    @PutMapping("/{orgid}/update/{empid}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int empid,@RequestBody Employee employee,@PathVariable int orgid){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,empid,orgid),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empid}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int empid){
        employeeService.deleteEmployee(empid);
        return new ResponseEntity<String>("Employee Data Deleted",HttpStatus.OK);
    }
}
