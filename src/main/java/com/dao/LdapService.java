package com.dao;

import java.util.List;

public interface LdapService {

	public List<Contact> getAllContracts();

	public Contact findUserByCommonName(String commonName);

	public List<Contact> findUserByFirstName(String firstName);

	public List<Contact> findUserByLastName(String firstName);
	
	public boolean checkConnection();

}
