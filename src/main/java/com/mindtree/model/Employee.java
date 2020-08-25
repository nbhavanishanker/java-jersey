package com.mindtree.model;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class Employee {

	String id;
	@NotNull
	String name;
	@NotNull
	String gender;
	@NotNull
	String DOB;
	String department;
	Address address;

	public Employee() {
		this.id = UUID.randomUUID().toString();
	}

	public Employee(String id, String name, String gender, String dOB, String department, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		DOB = dOB;
		this.department = department;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
