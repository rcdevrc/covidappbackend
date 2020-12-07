package com.example.teafproject.springbootmongodbsecurity.domain;

import java.io.Serializable;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

public class EmployeeDetail_Orig implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4859053347565962117L;

	@CsvBindByName
    private String FirstName;
	
	@CsvBindByName
    private String LastName;
	
	@CsvBindByName
    private String UserPrincipalName;
	
	@CsvBindByName
    private String PhoneNumber;
	
	@CsvBindByName
    private String Department;
	
	@CsvBindByName
    private String Office;
	
	private List<MacAddress>macAddresses;

	public List<MacAddress> getMacAddresses() {
		return macAddresses;
	}

	public void setMacAddresses(List<MacAddress> macAddresses) {
		this.macAddresses = macAddresses;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getUserPrincipalName() {
		return UserPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		UserPrincipalName = userPrincipalName;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getOffice() {
		return Office;
	}

	public void setOffice(String office) {
		Office = office;
	}


	
	public EmployeeDetail_Orig(String empid,String empfirstname, String emplastname, String empemail, String empphonenumber, String empdept, String emplocation )
	{
		this.FirstName = empfirstname;
		this.LastName = emplastname;
		this.UserPrincipalName = empemail;
		this.PhoneNumber = empphonenumber;
		this.Department = empdept;
		this.Office = emplocation;
	}
	
    @Override
    public String toString() {
        return "EmployeeDetail{" +
                ", empFirstName='" + FirstName + '\'' +
                ", empLastName='" + LastName + '\'' +
                ", empEmailAddress='" + UserPrincipalName + '\'' +
                ", empPhoneNumber='" + PhoneNumber + '\'' +
                ", empDept='" + Department + '\'' +
                ", empLocation='" + Office + 
                '}';
    }

}
