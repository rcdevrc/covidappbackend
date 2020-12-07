package com.example.teafproject.springbootmongodbsecurity.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teafproject.springbootmongodbsecurity.domain.EmployeeDetail;
import com.example.teafproject.springbootmongodbsecurity.domain.MacAddress;
import com.example.teafproject.springbootmongodbsecurity.repository.EmployeeDAO;
import com.example.teafproject.springbootmongodbsecurity.service.impl.EmployeeDetailServiceImpl;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	  @Autowired
	  EmployeeDAO employeeRepository;

	  @Autowired
      EmployeeDetailServiceImpl employeeDetailService;


	  @GetMapping("/greet")
	  public String greeting(){
            return "Hello There!";
      }
	  
  
	  @GetMapping("/employee/{emailid}")
	  public ResponseEntity<EmployeeDetail> getEmployeeByEmailId(@PathVariable("emailid") String email) {
		  //Optional<EmployeeDetail> employeeDetail = employeeRepository.findById(email);
          EmployeeDetail employeeDetail=employeeDetailService.findByEmail(email);
		  if (employeeDetail !=null) {
		    return new ResponseEntity(employeeDetail, HttpStatus.OK);
		  } else {
		    return new ResponseEntity("no record found with emailid= "+email,HttpStatus.NOT_FOUND);
		  }
		}
	  
	  @GetMapping("/employee/getemployeeslist")
	  public ResponseEntity<EmployeeDetail> getAllEmployee() {
          List<EmployeeDetail> employeeDetailList=employeeDetailService.findAll();
          System.out.println("list size = "+ employeeDetailList.size());
		  if (employeeDetailList !=null) {
		    return new ResponseEntity(employeeDetailList, HttpStatus.OK);
		  } else {
		    return new ResponseEntity("no record found ",HttpStatus.NOT_FOUND);
		  }
		}
	  
	  
	  @PutMapping("/employee/{emailid}")
	  public ResponseEntity<EmployeeDetail> updateEmployee(@PathVariable("emailid") String id, @RequestBody EmployeeDetail employeeDetail) {
		  Optional<EmployeeDetail> employeeData = employeeRepository.findById(id);
	        System.out.println("In PUT");
		  if (employeeData.isPresent()) {
		    EmployeeDetail _employee = employeeData.get();
		    _employee.setFirstName(employeeDetail.getFirstName());
		    _employee.setLastName(employeeDetail.getLastName());
		    _employee.setDepartment(employeeDetail.getDepartment());
		    _employee.setOffice(employeeDetail.getOffice());
		    _employee.setMobilePhone(employeeDetail.getMobilePhone());
		    return new ResponseEntity<>(employeeRepository.save(_employee), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
		}
	  
 
	  @PostMapping("/employee")
	  public ResponseEntity<EmployeeDetail> createTutorial(@RequestBody EmployeeDetail employee) {
	        System.out.println("In Post API");
	    try {
	      EmployeeDetail _employee = employeeRepository.save(new EmployeeDetail(employee.getFirstName(),
	    		  																employee.getLastName(),
	    		  																employee.getDepartment(),
	    		  																employee.getUserPrincipalName(),
	    		  																employee.getMobilePhone(),
	    		  																employee.getOffice()));
	      return new ResponseEntity<>(_employee, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	  
	  @PostMapping("employee/macaddress")
	  public ResponseEntity<EmployeeDetail> registerMacAddress(@RequestBody MacAddress macAddress) {

	    try {
	    	
	    	EmployeeDetail employeeData = employeeDetailService.findByEmail(macAddress.getEmail());	 
	    	    	
	    	if(employeeData.getMacAddress()!=null)
	    	{	    		    		
	    		Optional<MacAddress> hasMacAddress=employeeData.getMacAddress().stream()
	    		   .filter(character -> character.getMacAddress().equals(macAddress.getMacAddress()))
	    		   .findFirst();
	    		
		    	if(hasMacAddress.isPresent())
		    	{
		    		employeeData.getMacAddress().remove(hasMacAddress.get());
		    		
		    	}	
		 
		    	employeeData.getMacAddress().add(macAddress);
		    	employeeDetailService.saveOrUpdateEmployee(employeeData);
	    	
	    	}
	    	else
	    	{
	    		HashSet<MacAddress> macAddressSet = new HashSet<MacAddress>();
	    		macAddressSet.add(macAddress);
	    		employeeData.setMacAddress(macAddressSet);
	    		employeeDetailService.saveOrUpdateEmployee(employeeData);
	    		
	    	}
	    	 return new ResponseEntity<>(null, HttpStatus.OK);
	
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	  /*
	  @GetMapping("employee/macaddress")
	  public ResponseEntity<MacAddress> getEmployeeByEmailId() {
		  
		  List<EmployeeDetail> employeeDetailList=employeeDetailService.findAll();
	
		  
		  List<HashSet<MacAddress>> list = new ArrayList<HashSet<MacAddress>>();
		  employeeDetailList.forEach((n) -> list.add(n.getMacAddress()));

		    return new ResponseEntity(list, HttpStatus.OK);
		  
		}
*/
}
