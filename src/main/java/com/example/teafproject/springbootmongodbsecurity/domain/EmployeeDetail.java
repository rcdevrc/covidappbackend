package com.example.teafproject.springbootmongodbsecurity.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.opencsv.bean.CsvBindByName;



@Document(collection = "employees")
public class EmployeeDetail  {

	@Id
   private String id;

    @CsvBindByName (column = "FirstName" )
    private String firstName;

    @CsvBindByName (column = "LastName")
    private String lastName;


    @CsvBindByName (column = "UserPrincipalName" )
    private String userPrincipalName;
    
    @CsvBindByName (column = "MobilePhone")
    private String mobilePhone;
    
    @CsvBindByName(column = "Department" )
    private String department;
    
    @CsvBindByName(column = "Office")
    private String office;

    private HashSet<MacAddress> macAddress=new HashSet<MacAddress>();
    

    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashSet<MacAddress> getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(HashSet<MacAddress> macAddress) {
		this.macAddress =  macAddress;
	}

	public EmployeeDetail() {
    }

    public EmployeeDetail(String firstname, String lastname, String department, String email, String mobile, String office)
    {
        this.firstName = firstname;
        this.lastName = lastname;
        this.userPrincipalName = email;
        this.department = department;
        this.mobilePhone = mobile;
        this.office = office;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}