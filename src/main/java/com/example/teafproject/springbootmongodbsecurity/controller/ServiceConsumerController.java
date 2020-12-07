package com.example.teafproject.springbootmongodbsecurity.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.teafproject.springbootmongodbsecurity.domain.HealthBoard;
import com.example.teafproject.springbootmongodbsecurity.domain.ViewTracingResponseBean;
import com.example.teafproject.springbootmongodbsecurity.service.KianaServices;
import com.example.teafproject.springbootmongodbsecurity.service.impl.KianaServicesImpl;
import com.google.gson.Gson;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ServiceConsumerController {

	@Autowired
	RestTemplate restTemplate;  

	@GetMapping("/trace/{userId}")
	public ResponseEntity getTrace(@PathVariable("userId") String userId) {

		KianaServicesImpl kianaServicesObj=new KianaServicesImpl();		  
		return kianaServicesObj.getTrace(userId,restTemplate);

	}


	@GetMapping("/getRecomd/{userId}")
	public ResponseEntity getRecomd(@PathVariable("userId") String userId) {

		KianaServicesImpl kianaServicesObj=new KianaServicesImpl();
		return kianaServicesObj.getRecomd(userId, restTemplate); 
	}


	@GetMapping("/trace")
	public ResponseEntity getFullTrace() {

		KianaServicesImpl kianaServicesObj=new KianaServicesImpl();
		return kianaServicesObj.getFullTrace(restTemplate);

	}  

	@GetMapping("/dashBoardStats")
	public ResponseEntity getDashBoardStats() {

		KianaServicesImpl kianaServicesObj=new KianaServicesImpl();
		return kianaServicesObj.getDashBoardStats(restTemplate);	

	}


	@GetMapping("/dashBoardStats/month")
	public ResponseEntity getDashBoardStatsMonth() {

		KianaServicesImpl kianaServicesObj=new KianaServicesImpl();
		return kianaServicesObj.getDashBoardStatsMonth(restTemplate);	

	}


	@GetMapping("/dashBoardStats/covidProgress")
	public ResponseEntity getDashBoardCovidProgress() {

		KianaServicesImpl kianaServicesObj=new KianaServicesImpl();
		return kianaServicesObj.getDashBoardCovidProgress(restTemplate);

	}

	@GetMapping("/healthBoard/{status}")
	public ResponseEntity getHealthBoard(@PathVariable("status") String status) {

		KianaServicesImpl kianaServicesObj=new KianaServicesImpl();
		return kianaServicesObj.getHealthBoard(status,restTemplate);

	}  
	/*
	  String formateDate(String date) {

		     long unix_seconds = Long.parseLong(date);
		     //convert seconds to milliseconds
		     Date datee = new Date(unix_seconds*1000L); 
		     // format of the date
		     SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		     jdf.setTimeZone(TimeZone.getTimeZone("CST"));
		     String java_date = jdf.format(datee);
		     String dateSplit[]=java_date.split(" ");

		     return dateSplit[0].replaceAll("-", "/");

	  }

  String getPersons(String listOfPerson,String status,String exclude)
  {

	  String finalresult="";
	  String res="";
	  List<HealthBoard> resList = new ArrayList();

	   try {

		   	int i=0;

			JSONArray ja = new JSONArray();
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(listOfPerson);

			System.out.println("Infect list is "+json);
			Iterator<JSONArray> iterator = json.values().iterator();



			while (iterator.hasNext()) {	

				ja=iterator.next();
				Iterator<JSONObject> iterator2 = ja.iterator();

				while(iterator2.hasNext()) {


					if(i>10)
						break;

					JSONObject jsonObj=iterator2.next();

					String userId=jsonObj.get("personId").toString();

					String date="";

					if(status.equals("Covid Affected"))
						date=formateDate(jsonObj.get("dateOfTest").toString());

				if(!exclude.contains(userId) || status.equals("Covid Affected"))
				{	i++;
					HealthBoard healthBoard=new HealthBoard();
					healthBoard.setFirstName(userId);
					healthBoard.setOffice("Chicago");
					healthBoard.setComments("");
					healthBoard.setDate(date);
					healthBoard.setDepartment("");
					healthBoard.setLastName("");
					healthBoard.setMobilePhone("");
					healthBoard.setStatus(status);
					healthBoard.setUserPrincipalName("");
					resList.add(healthBoard);

					}
				}	
				}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finalresult = new Gson().toJson(resList );
		return finalresult;	  
  }
	 */
}
