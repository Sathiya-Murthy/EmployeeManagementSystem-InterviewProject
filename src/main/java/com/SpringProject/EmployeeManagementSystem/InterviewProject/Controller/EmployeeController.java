package com.SpringProject.EmployeeManagementSystem.InterviewProject.Controller;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Exception.EmployeeIdNotFound;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Models.Employee;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Repository.EmployeeRepository;
import com.SpringProject.EmployeeManagementSystem.InterviewProject.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

//    public EmployeeController(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }
    @PostMapping("/{orgid}/add")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee, @PathVariable int orgid){
        if(employee.getFirstname().isEmpty()||employee.getLastname().isEmpty()||
                employee.getEmail().isEmpty()||employee.getPassword().isEmpty()||employee.getDesignation().isEmpty()){
            return new ResponseEntity<String>("The above fields cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if(!(employee.getFirstname().length()>=3&&employee.getFirstname().length()<=20))
            return new ResponseEntity<String>("The Firstname should be in the range of 3 t0 20", HttpStatus.BAD_REQUEST);

        if(!(employee.getLastname().length()>=1&&employee.getLastname().length()<=20))
            return new ResponseEntity<String>("The Lastname should be in the range of 1 t0 20", HttpStatus.BAD_REQUEST);

        if(employee.getEmail().length()>=9&&employee.getEmail().length()<=30) {
            String[] email = employee.getEmail().split("@");
            int count=0;
            if (email.length==2) {
                String emailPart1=email[0];
                String emailPart2=email[1];

                for (int i=0;i<emailPart1.length();i++){
                    if(!(Character.isDigit(emailPart1.charAt(i))||emailPart1.charAt(i)=='.'||
                            (emailPart1.charAt(i) >= 'A' && emailPart1.charAt(i) <= 'Z')||
                            (emailPart1.charAt(i) >= 'a' && emailPart1.charAt(i) <= 'z'))){
                        return new ResponseEntity<String>("Enter and valid email address", HttpStatus.BAD_REQUEST);
                    }

                }

                for (int i=0;i<emailPart2.length();i++){
                    if(!(Character.isDigit(emailPart2.charAt(i))||emailPart2.charAt(i)=='.'||
                            (emailPart2.charAt(i) >= 'A' && emailPart2.charAt(i) <= 'Z')||
                            (emailPart2.charAt(i) >= 'a' && emailPart2.charAt(i) <= 'z'))){
                        return new ResponseEntity<String>("Enter and valid email address", HttpStatus.BAD_REQUEST);
                    }
                    if (emailPart2.charAt(i)=='.'){
                        count++;
                    }

                }
                if(count!=1)
                  return new ResponseEntity<String>("Enter and valid email address", HttpStatus.BAD_REQUEST);


            } else {
                return new ResponseEntity<String>("Enter and valid email address", HttpStatus.BAD_REQUEST);
            }

        }else {
            return new ResponseEntity<String>("Enter and valid email address", HttpStatus.BAD_REQUEST);
        }

        if(employeeRepository.existsByEmail(employee.getEmail())){
            return new ResponseEntity<String>("This email address already exists", HttpStatus.BAD_REQUEST);
        }

        if (employee.getPassword().length()>=8&&employee.getPassword().length()<=15){
            String password=employee.getPassword();
            int UpperCaseCount=0,LowerCaseCount=0,DigitCount=0,SpecialCharCount=0;
            if(password.contains(" ")||password.contains("(")||password.contains(")")||password.contains("{")||
                    password.contains("}")){
                return new ResponseEntity<String>("A Password should not contain whitespace and parentheses", HttpStatus.BAD_REQUEST);
            }
            for(int i=0;i<password.length();i++){
                if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z')
                    UpperCaseCount++;
                else if(password.charAt(i) >= 'a' && password.charAt(i) <= 'z')
                    LowerCaseCount++;
                else if(Character.isDigit(password.charAt(i)))
                    DigitCount++;
                else
                    SpecialCharCount++;
            }
            if(UpperCaseCount==0|| LowerCaseCount==0 || DigitCount==0|| SpecialCharCount==0)
                return new ResponseEntity<String>("The Password must contain an Upper case,Lower case,Digit,Special Character", HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<String>("The Password must be in the range of 8 to 15", HttpStatus.BAD_REQUEST);
        }

        if(employee.getSalary()<5000)
            return new ResponseEntity<String>("The Salary must minimum 5000", HttpStatus.BAD_REQUEST);



        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee,orgid), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Employee> getEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/get/{empid}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int empid)  {
        Employee employee=employeeRepository.findById(empid).orElseThrow(()-> new EmployeeIdNotFound());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Employee AuthEmployee =employeeRepository.findByEmail(currentPrincipalName).orElseThrow(()-> new EmployeeIdNotFound());
        if(employee.getEmail().equals(currentPrincipalName) || AuthEmployee.getRole().equals("ROLE_MANAGER")){
            return new ResponseEntity<Employee>(employeeService.getEmployeeById(empid), HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Only the employee belong to the id and manager can get the data",HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{orgid}/update/{empid}")
    public ResponseEntity<?> updateEmployee(@PathVariable int empid,@RequestBody Employee employee,@PathVariable int orgid){
        Employee vEmployee=employeeRepository.findById(empid).orElseThrow(()-> new EmployeeIdNotFound());
        if(employee.getFirstname().isEmpty()||employee.getLastname().isEmpty()||
                employee.getPassword().isEmpty()||employee.getDesignation().isEmpty()){
            return new ResponseEntity<String>("The above fields cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if(!(employee.getFirstname().length()>=3&&employee.getFirstname().length()<=20))
            return new ResponseEntity<String>("The Firstname should be in the range of 3 t0 20", HttpStatus.BAD_REQUEST);

        if(!(employee.getLastname().length()>=1&&employee.getLastname().length()<=20))
            return new ResponseEntity<String>("The Lastname should be in the range of 1 t0 20", HttpStatus.BAD_REQUEST);

        if(employee.getEmail().length()>=9&&employee.getEmail().length()<=30) {
            String[] email = employee.getEmail().split("@");
            int count=0;
            if (email.length==2) {
                String emailPart1=email[0];
                String emailPart2=email[1];

                for (int i=0;i<emailPart1.length();i++){
                    if(!(Character.isDigit(emailPart1.charAt(i))||emailPart1.charAt(i)=='.'||
                            (emailPart1.charAt(i) >= 'A' && emailPart1.charAt(i) <= 'Z')||
                            (emailPart1.charAt(i) >= 'a' && emailPart1.charAt(i) <= 'z'))){
                        return new ResponseEntity<String>("Enter and valid email address", HttpStatus.BAD_REQUEST);
                    }

                }

                for (int i=0;i<emailPart2.length();i++){
                    if(!(Character.isDigit(emailPart2.charAt(i))||emailPart2.charAt(i)=='.'||
                            (emailPart2.charAt(i) >= 'A' && emailPart2.charAt(i) <= 'Z')||
                            (emailPart2.charAt(i) >= 'a' && emailPart2.charAt(i) <= 'z'))){
                        return new ResponseEntity<String>("Enter and valid email address", HttpStatus.BAD_REQUEST);
                    }
                    if (emailPart2.charAt(i)=='.'){
                        count++;
                    }

                }
                if(count!=1)
                    return new ResponseEntity<String>("Enter and valid email address", HttpStatus.BAD_REQUEST);


            } else {
                return new ResponseEntity<String>("Enter and valid email address", HttpStatus.BAD_REQUEST);
            }

        }else {
            return new ResponseEntity<String>("Enter and valid email address", HttpStatus.BAD_REQUEST);
        }

        if(!(employee.getEmail().equals(vEmployee.getEmail()))){
            if(employeeRepository.existsByEmail(employee.getEmail())){
                return new ResponseEntity<String>("This email address already exists", HttpStatus.BAD_REQUEST);
            }
        }


        if (employee.getPassword().length()>=8&&employee.getPassword().length()<=15){
            String password=employee.getPassword();
            int UpperCaseCount=0,LowerCaseCount=0,DigitCount=0,SpecialCharCount=0;
            if(password.contains(" ")||password.contains("(")||password.contains(")")||password.contains("{")||
                    password.contains("}")){
                return new ResponseEntity<String>("A Password should not contain whitespace and parentheses", HttpStatus.BAD_REQUEST);
            }
            for(int i=0;i<password.length();i++){
                if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z')
                    UpperCaseCount++;
                else if(password.charAt(i) >= 'a' && password.charAt(i) <= 'z')
                    LowerCaseCount++;
                else if(Character.isDigit(password.charAt(i)))
                    DigitCount++;
                else
                    SpecialCharCount++;
            }
            if(UpperCaseCount==0|| LowerCaseCount==0 || DigitCount==0|| SpecialCharCount==0)
                return new ResponseEntity<String>("The Password must contain an Upper case,Lower case,Digit,Special Character", HttpStatus.BAD_REQUEST);

        }else {
            return new ResponseEntity<String>("The Password must be in the range of 8 to 15", HttpStatus.BAD_REQUEST);
        }

        if(employee.getSalary()<5000)
            return new ResponseEntity<String>("The Salary must minimum 5000", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,empid,orgid),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empid}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int empid){
        employeeService.deleteEmployee(empid);
        return new ResponseEntity<String>("Employee Data Deleted",HttpStatus.OK);
    }
}
