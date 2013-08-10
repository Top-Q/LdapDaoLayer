package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
// com.dao.LDAPContactDAOImpl
//import org.springframework.ldap.AttributesMapper;
//import org.springframework.ldap.LdapTemplate;
//import org.springframework.ldap.support.DistinguishedName;
//import org.springframework.ldap.support.filter.AndFilter;
//import org.springframework.ldap.support.filter.EqualsFilter;

public class LdapServiceImpl implements LdapService {

	private LdapTemplate ldapTemplate;
	private ContactAttributeMapper mapper;

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	public LdapServiceImpl() {
		mapper = new ContactAttributeMapper(this);
	}

	public  boolean checkConnection() {
		boolean check=false;
		try {
			List search = ldapTemplate.search("", "(objectClass=person)", mapper);
			 if(ldapTemplate.search("", "(objectClass=person)", mapper).size()>0){
				 check=true;
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public List<Contact> getAllContracts() {
		List<Contact> contacts = new ArrayList<Contact>();
		try {

			// TODO:init from code and not from context
			// TODO:check connection
			// TODO:Attributes (get the map from outside of the
			// ContactAttributeMapper)
			// TODO:Image -save to file
			List search = ldapTemplate.search("", "(objectClass=person)", mapper);

			/*
			 * String objectClass = "samAccountName=king";
			 * LinkedList<Map<String, String>> list = (LinkedList<Map<String,
			 * String>>) ldapTemplate.search("", objectClass, new
			 * ContactAttributeMapper());
			 */

			contacts.addAll(search);
		} catch (Exception e) {
			int x = 5;
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		}

		return contacts;
	}

	public Contact findUser(String commonName) {
		Object lookup = ldapTemplate.lookup("cn=" + commonName, mapper);
		return (Contact) lookup;
	}

	public Contact findUserByCommonName(String commonName) {
		try {
			AndFilter filter = new AndFilter();
			filter.and(new EqualsFilter("objectClass", "person"));
			filter.and(new EqualsFilter("cn", commonName));
			List search = ldapTemplate.search("", filter.encode(), mapper);
			if (search != null && search.size() > 0)
				return (Contact) search.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Contact> findUserByFirstName(String firstName) {
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			AndFilter filter = new AndFilter();
			filter.and(new EqualsFilter("objectClass", "person"));
			filter.and(new EqualsFilter("givenName", firstName));
			List search = ldapTemplate.search("", filter.encode(), mapper);
			contacts.addAll(search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;

	}

	public List<Contact> findUserByLastName(String firstName) {
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			AndFilter filter = new AndFilter();
			filter.and(new EqualsFilter("objectClass", "person"));
			filter.and(new EqualsFilter("sn", firstName));
			List search = ldapTemplate.search("", filter.encode(), mapper);
			contacts.addAll(search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}

}
