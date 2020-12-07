package com.example.teafproject.springbootmongodbsecurity.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.teafproject.springbootmongodbsecurity.domain.HealthBoard;
import com.example.teafproject.springbootmongodbsecurity.domain.ViewTracingResponseBean;
import com.example.teafproject.springbootmongodbsecurity.service.KianaServices;
import com.google.gson.Gson;

public class KianaServicesImpl implements KianaServices {

	String key="AIzaSyC7BuvRRDIwYqXgiRLMOlI2NA6efp3CzxQ";


	public ResponseEntity getTrace(String userId,RestTemplate restTemplate) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);

		ViewTracingResponseBean tracseReponse=new ViewTracingResponseBean();
		String finalresult="";
		String res="";
		
		res= restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/proximityPerDayForOnePersonUpdatedHourly?personWithOrWithoutPosTest="+userId+"&key="+key, HttpMethod.GET, entity, String.class).getBody();  

		JSONObject json;
		JSONParser parser = new JSONParser();

		try {
				json = (JSONObject) parser.parse(res);
				ViewTracingResponseBean viewTrackResponse=new ViewTracingResponseBean();
				JSONObject object = (JSONObject) json.get("dates");			
				
				if(object!=null) {
			
					Iterator<JSONObject> iterator = object.values().iterator();
					Iterator<String> iteratordate=object.keySet().iterator();
					List<ViewTracingResponseBean> resList = new ArrayList();

					while (iterator.hasNext()) {

						String date=iteratordate.next();
						JSONObject jsonChildObject = iterator.next();
						JSONArray persons = new JSONArray();
						
						persons= (JSONArray) jsonChildObject.get("persons");
						Iterator<JSONObject> iterator2 = persons.iterator();

						while (iterator2.hasNext()) {

							JSONObject jsonChildObject2 = iterator2.next();
							ViewTracingResponseBean resBean=new ViewTracingResponseBean();
							resBean.setToFirstName(userId);
							// resBean.setToEmailId(userId+"@RoyalCyber.com");
							resBean.setFromFirstName(jsonChildObject2.get("personId").toString());
							//	 resBean.setFromEmailId(jsonChildObject2.get("personId").toString()+"@RoyalCyber.com");
							resBean.setDate(date);
							resList.add(resBean);				
						}

					}
					
					finalresult = new Gson().toJson(resList );
					System.out.println("Final result is "+finalresult);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> resultList= new ArrayList<String>();
		resultList.add(finalresult);
		return new ResponseEntity(resultList,HttpStatus.OK);

	}

	public ResponseEntity getRecomd(String userId,RestTemplate restTemplate) {
		
		System.out.println("User Id "+userId);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);

		Boolean infected=false;
		Boolean tracing=false;
		Boolean userExist=false;

		String recomd="";
		String res="";
		String date="";
		
		String listOfPerson=restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/listOfPersons?key="+key, HttpMethod.GET, entity, String.class).getBody();

		if(listOfPerson.contains(userId))
		{
			userExist=true;
			String infect =restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/listOfInfectedPersons?key="+key, HttpMethod.GET, entity, String.class).getBody();

			if(infect.contains(userId))
			{	infected=true;

				infect=infect.substring(infect.indexOf(userId)+userId.length()+20);
				infect=infect.substring(0,infect.indexOf("}"));
				date=infect.replaceAll("\\s+","").replaceAll("\"","");

				// convert the time	    	
				date=formateDate(date);		     
				System.out.println(date);
				System.out.println("Infect is "+date);
			}

			res= restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/proximityPerDayForOnePersonUpdatedHourly?personWithOrWithoutPosTest="+userId+"&key="+key, HttpMethod.GET, entity, String.class).getBody(); 

			if(res.contains("personId"))
			{
				tracing=true;
				System.out.println("Tracing avaible");
			}


		}
		
		List<String> resultList= new ArrayList<String>();
		String result="{ \"date\":"+"\""+date+"\",\"infected\":"+infected+",\"tracing\":"+tracing+",\"userExist\":"+userExist+"}";

		JSONObject jsonObject = new JSONObject();
		JSONParser parser = new JSONParser();
		
		try {
			jsonObject = (JSONObject) parser.parse(result);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity(jsonObject, HttpStatus.OK);
	}

	public ResponseEntity getFullTrace(RestTemplate restTemplate) {

		KianaServices kianaServicesObj=new KianaServicesImpl();
		List<String> resultList= new ArrayList<String>();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);

		ViewTracingResponseBean tracseReponse=new ViewTracingResponseBean();

		String finalresult="";
		String res="";
		List<ViewTracingResponseBean> resList = new ArrayList();
		
		String listOfPerson =restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/listOfPersons?key="+key, HttpMethod.GET, entity, String.class).getBody();

		try {

			int i=0;
			JSONArray ja = new JSONArray();
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(listOfPerson);
			Iterator<JSONArray> iterator = json.values().iterator();

			while (iterator.hasNext()) {	

				ja=iterator.next();
				Iterator<JSONObject> iterator2 = ja.iterator();

				while(iterator2.hasNext()) {

					System.out.println(i);
					i++;
					if(i>2)
						break;
					String userId=iterator2.next().get("personId").toString();

					res= restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/proximityPerDayForOnePersonUpdatedHourly?personWithOrWithoutPosTest="+userId+"&key="+key, HttpMethod.GET, entity, String.class).getBody();  

					try {
						json = (JSONObject) parser.parse(res);
						ViewTracingResponseBean viewTrackResponse=new ViewTracingResponseBean();


						JSONObject object = (JSONObject) json.get("dates");
						//System.out.println("Object values is "+object.values());
						if(object!=null)
						{
							Iterator<JSONObject> iterator3 = object.values().iterator();
							Iterator<String> iteratordate=object.keySet().iterator();

							while (iterator3.hasNext()) {

								String date=iteratordate.next();

								JSONObject jsonChildObject = iterator3.next();//(JSONObject) parser.parse(temp);
								JSONArray persons = new JSONArray();

								persons=(JSONArray) jsonChildObject.get("persons");
								Iterator<JSONObject> iterator4 = persons.iterator();

								while (iterator4.hasNext()) {

									//	System.out.println("Trace is "+iterator4.next());
									JSONObject jsonChildObject2 = iterator4.next();
									ViewTracingResponseBean resBean=new ViewTracingResponseBean();

									resBean.setToFirstName(userId);
									// resBean.setToEmailId(userId+"@RoyalCyber.com");
									resBean.setFromFirstName(jsonChildObject2.get("personId").toString());
									//	 resBean.setFromEmailId(jsonChildObject2.get("personId").toString()+"@RoyalCyber.com");
									resBean.setDate(date);
									resList.add(resBean);				

								}

							}

						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}

			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finalresult = new Gson().toJson(resList );
		resultList.add(finalresult);
		return new ResponseEntity(resultList,HttpStatus.OK);	

	}

	public ResponseEntity getDashBoardStats(RestTemplate restTemplate) {

		KianaServices kianaServicesObj=new KianaServicesImpl();
		List<String> resultList= new ArrayList<String>();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);

		ViewTracingResponseBean tracseReponse=new ViewTracingResponseBean();

		String finalresult="";
		String res="";

		String listOfPerson =restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/listOfPersons?key="+key, HttpMethod.GET, entity, String.class).getBody();
		JSONObject jsonObject = new JSONObject();

		try {

			int i=0;
			int totalUsers=0;
			int totalPositive=0;
			int feelingGood=0;

			JSONArray ja = new JSONArray();
			JSONParser parser = new JSONParser();
			JSONObject json =  (JSONObject) parser.parse(listOfPerson);
			Iterator<JSONArray> iterator = json.values().iterator();
			ja=(JSONArray)json.get("persons");
			totalUsers=ja.size();

			listOfPerson=restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/listOfInfectedPersons?key="+key, HttpMethod.GET, entity, String.class).getBody();
	
			json =  (JSONObject) parser.parse(listOfPerson);
			iterator = json.values().iterator();
			ja=(JSONArray)json.get("persons");
			totalPositive=ja.size();
			feelingGood=totalUsers-totalPositive;
		

			String result="{ \"totalUsers\":"+totalUsers+",\"totalPostive\":"+totalPositive+",\"feelingGood\":"+feelingGood+"}";


			try {
				jsonObject = (JSONObject) parser.parse(result);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity(jsonObject,HttpStatus.OK);	

	}   

	public ResponseEntity getDashBoardStatsMonth(RestTemplate restTemplate) {

		KianaServices kianaServicesObj=new KianaServicesImpl();
		List<String> resultList= new ArrayList<String>();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);

		ViewTracingResponseBean tracseReponse=new ViewTracingResponseBean();

		String finalresult="";
		String res="";

		String listOfPerson =restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/listOfPersons?key="+key, HttpMethod.GET, entity, String.class).getBody();
		String listOfInfectedPerson =restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/listOfInfectedPersons?key="+key, HttpMethod.GET, entity, String.class).getBody();
		JSONObject jsonObject = new JSONObject();
		int currentMonthCovid=0;
		int lastMonthCovid=0;
		int totalUsers=0;
		List<HealthBoard> resList = new ArrayList();

		try {

			int i=0;

			JSONArray ja = new JSONArray();
			JSONParser parser = new JSONParser();

			JSONObject json =  (JSONObject) parser.parse(listOfPerson);
			Iterator<JSONArray> iterator = json.values().iterator();
			ja=(JSONArray)json.get("persons");
			totalUsers=ja.size();
			json = (JSONObject) parser.parse(listOfInfectedPerson);

			
			iterator = json.values().iterator();
			GregorianCalendar currentDate = new GregorianCalendar();      
			int month = currentDate.get(Calendar.MONTH)+1;


			while (iterator.hasNext()) {	

				ja=iterator.next();
				Iterator<JSONObject> iterator2 = ja.iterator();

				while(iterator2.hasNext()) {


					JSONObject jsonObj=iterator2.next();
					String userId=jsonObj.get("personId").toString();

					String covidDetecteddate=formateDate(jsonObj.get("dateOfTest").toString());
					String dateMonth[]=covidDetecteddate.split("/");

					if(Integer.parseInt(dateMonth[1])==month)
						currentMonthCovid++;
					else if(Integer.parseInt(dateMonth[1])==month-1)
						lastMonthCovid++;

				}	
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int feelingGoodCurrentMonth=totalUsers-currentMonthCovid-lastMonthCovid;
		int feelingGoodLastMonth=totalUsers-lastMonthCovid;
		String result="{ \"thisMonthCovid\":"+currentMonthCovid+",\"lastMonthCovid\":"+lastMonthCovid+",\"feelingGoodCurrentMonth\":"+feelingGoodCurrentMonth+",\"feelingGoodLastMonth\":"+feelingGoodLastMonth+"}";


		JSONParser parser = new JSONParser();
		try {
			jsonObject = (JSONObject) parser.parse(result);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity(jsonObject,HttpStatus.OK);	
	}

	public ResponseEntity getDashBoardCovidProgress(RestTemplate restTemplate) {

		KianaServices kianaServicesObj=new KianaServicesImpl();
		List<String> resultList= new ArrayList<String>();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);

		ViewTracingResponseBean tracseReponse=new ViewTracingResponseBean();

		String finalresult="";
		String res="";

		String listOfPerson =restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/listOfPersons?key="+key, HttpMethod.GET, entity, String.class).getBody();
		JSONObject jsonObject = new JSONObject();

		try {

			int i=0;
			int totalUsers=0;
			int totalPositive=0;
			double newCases=0;
			double totalCovid=0.0;

			JSONArray ja = new JSONArray();
			JSONParser parser = new JSONParser();
			JSONObject json =  (JSONObject) parser.parse(listOfPerson);
			Iterator<JSONArray> iterator = json.values().iterator();
			ja=(JSONArray)json.get("persons");
			totalUsers=ja.size();

			listOfPerson=restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/listOfInfectedPersons?key="+key, HttpMethod.GET, entity, String.class).getBody();
			System.out.println("Number of users are "+ja.size());

			json =  (JSONObject) parser.parse(listOfPerson);
			iterator = json.values().iterator();
			ja=(JSONArray)json.get("persons");
			totalPositive=ja.size();
			totalCovid=totalPositive*100/(double)totalUsers;
			String sValue = (String) String.format("%.2f", totalCovid);
			totalCovid = Double.parseDouble(sValue);
			System.out.println("total positive are "+totalCovid);


			////-------------///


			try {

				json = (JSONObject) parser.parse(listOfPerson);						
				System.out.println("Infect list is "+json);
				iterator = json.values().iterator();
				GregorianCalendar currentDate = new GregorianCalendar();      
				int month = currentDate.get(Calendar.MONTH)+1;
				int day=currentDate.get(Calendar.DATE);
				int year=currentDate.get(Calendar.YEAR);		    

				while (iterator.hasNext()) {	

					ja=iterator.next();
					Iterator<JSONObject> iterator2 = ja.iterator();

					while(iterator2.hasNext()) {


						JSONObject jsonObj=iterator2.next();

						String userId=jsonObj.get("personId").toString();

						String covidDetecteddate=formateDate(jsonObj.get("dateOfTest").toString());
						String dateMonth[]=covidDetecteddate.split("/");

						if(Integer.parseInt(dateMonth[1])==month && Integer.parseInt(dateMonth[2])==day && Integer.parseInt(dateMonth[0])==year)
							newCases++;

					}	
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			newCases=newCases*100/(double)totalUsers;
			sValue = (String) String.format("%.2f", newCases);
			newCases = Double.parseDouble(sValue);
			System.out.println("New cases are "+newCases);

			//End///////////////


			String result="{ \"totalCovid\":"+totalCovid+",\"newCases\":"+newCases+",\"Recovered\":"+newCases+"}";
			try {
				jsonObject = (JSONObject) parser.parse(result);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity(jsonObject,HttpStatus.OK);	

	}

	public ResponseEntity getHealthBoard(String status,RestTemplate restTemplate) {

		KianaServices kianaServicesObj=new KianaServicesImpl();
		List<String> resultList= new ArrayList<String>();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);


		String finalresult="";
		String res="";
		List<HealthBoard> resList = new ArrayList();
		String listOfPerson="";

		listOfPerson =restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/listOfPersons?key="+key, HttpMethod.GET, entity, String.class).getBody();


		String covidPerson=restTemplate.exchange("https://exposure-api.kiana.io/_ah/api/exposureHr/v1/listOfInfectedPersons?key="+key, HttpMethod.GET, entity, String.class).getBody();
		resultList.add(getPersons(listOfPerson,"Feeling Good",covidPerson));

		resultList.add(getPersons(covidPerson,"Covid Affected",""));
		return new ResponseEntity(resultList,HttpStatus.OK);	

	}     

	@Override
	public String getTraceById(String userId) {
		return "";
	}

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
					healthBoard.setDate(date);
					healthBoard.setDepartment("");
					healthBoard.setLastName("");
					healthBoard.setStatus(status);
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


}
