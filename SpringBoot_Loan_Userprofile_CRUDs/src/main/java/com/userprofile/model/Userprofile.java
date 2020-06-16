package com.userprofile.model;

import java.util.Date;

public class Userprofile {
	private int id;
	private String name;
	private String type;
	
	
	private String phone;
	private String email;
	private String ssn;
	private String account;
	private Date recentLogin;
	private String location;
	
	public Userprofile() { 
		
	}

	public Userprofile(int id, String name, String type, String phone, String email, String ssn, String account,
			Date recentLogin, String location) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.phone = phone;
		this.email = email;
		this.ssn = ssn;
		this.account = account;
		this.recentLogin = recentLogin;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getRecentLogin() {
		return recentLogin;
	}

	public void setRecentLogin(Date recentLogin) {
		this.recentLogin = recentLogin;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}



	
	
}
