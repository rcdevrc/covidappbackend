package com.example.teafproject.springbootmongodbsecurity.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.teafproject.springbootmongodbsecurity.domain.EmployeeDetail;
import com.example.teafproject.springbootmongodbsecurity.service.CustomUserDetailsService;
import com.example.teafproject.springbootmongodbsecurity.service.EmployeeDetailService;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Controller
public class UploadController { 
    @Autowired
    private EmployeeDetailService employeeService;
	
    @PostMapping("/upload-csv-file")
    public ModelAndView uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        // validate file
        List<EmployeeDetail> employees = new ArrayList<EmployeeDetail>();
        if (file.isEmpty()) {
             model.addAttribute("message", "Please select a CSV file to upload.");
             model.addAttribute("status", false);
 //       	return new ModelAndView("status","message","Please select a file and try again")
        } else {
        	
        
                
             Reader reader;
			try {
				
				reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
	             strategy.setType(EmployeeDetail.class);
	             String[] memberFieldsToBindTo = {"alternateEmailaddresses","city","country","department","displayName","firstName","lastName","mobilePhone","office","phoneNumber","postalCode","preferredLanguage","state","streetAddress","title","usageLocation","userPrincipalName"};
	             strategy.setColumnMapping(memberFieldsToBindTo);
	            
	             CsvToBean<EmployeeDetail> csvToBean = new CsvToBeanBuilder(reader)
	                     .withMappingStrategy(strategy)
	                     .withSkipLines(1)
	                     .withIgnoreLeadingWhiteSpace(true)
	                     .build();
	             
            
	             Iterator<EmployeeDetail> myUserIterator = csvToBean.iterator();
	             /*
	             if(myUserIterator.hasNext())
	             {
	            	 employeeService.deleteAllEmployee();
	             }
	             */
	             while (myUserIterator.hasNext()) {
	                 EmployeeDetail employeeDetail = myUserIterator.next();
	                 // Add employee detail object
	                 System.out.println("user principle : " + employeeDetail.getUserPrincipalName());
	                 
	                 //create array list object with type of employees
                 
	                 employees.add(employeeDetail);
	                 employeeService.saveEmployeeDetail(employeeDetail);
      
	             }
	             modelAndView.addObject("employees",employees);
	             model.addAttribute("status",true);
	             System.out.println(employees);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             //CSVReader reader = new CSVReader(new FileReader(SAMPLE_CSV_FILE_PATH), ',' , '"' , 1);
          {
             
         }
        }
        modelAndView.addObject("employees", employees);
        modelAndView.setViewName("file-upload-status");
        System.out.println(employees);
        return modelAndView;
    }


}

