package com.dao;

public class Contact {

	private String mail;
	private String domainAccountName;
	private String department;
	private byte[] picture;
	private String jobTitle;
	private String managerName;
	private Contact manager;
	private String firstName;
	private String lastName;
	private String displayname;
	private String name;
	private String managerEmail;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getDomainAccountName() {
		return domainAccountName;
	}

	public void setDomainAccountName(String domainAccountName) {
		this.domainAccountName = domainAccountName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
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

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Contact getManager() {
		return manager;
	}

	public void setManager(Contact manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Contact [mail=" + mail + ", domainAccountName=" + domainAccountName + ", department=" + department + ", jobTitle="
				+ jobTitle + ", managerName=" + managerName + ", manager=" + manager + ", firstName=" + firstName + ", lastName="
				+ lastName + ", displayname=" + displayname + ", name=" + name + ", managerEmail=" + managerEmail + "]";
	}

}