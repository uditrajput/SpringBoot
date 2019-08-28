package com.mypack.restfullwebservice.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All Details about the User.")
public class User {

	private Integer id;
	@Size(min = 2, message = "Name shoud have at min 2 character long!")
	@ApiModelProperty(notes = "Name shoud have at min 2 character long.")
	private String name;
	@Past
	@ApiModelProperty(notes = "Birthdate should not be in past.")
	private Date birthdate;
	private String address;
	
	public User() {
		
	}
	public User(Integer id, String name, Date birthdate, String address) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
