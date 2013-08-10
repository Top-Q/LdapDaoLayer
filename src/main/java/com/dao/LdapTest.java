package com.dao;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;

public class LdapTest {

	
	public static void main(String[] args) {
		try {
			Resource resource = new ClassPathResource("applicationContext.xml");
			BeanFactory factory = new XmlBeanFactory(resource);
			System.out.println(factory.toString() + "\n");
			
			
			org.springframework.ldap.core.LdapTemplate ldap = (org.springframework.ldap.core.LdapTemplate) factory.getBean("ldapTemplate");
 
			LdapService ldapContact = (LdapService)factory.getBean("ldapContact");	
			
			boolean checkConnection = ldapContact.checkConnection();
			List<Contact> allContracts = ldapContact.getAllContracts();
			
			List<Contact> findUserByFirstName = ldapContact.findUserByFirstName("Liel Kosem");
			List<Contact> findUserByLastName = ldapContact.findUserByLastName("Ran Last");
			Contact findUserByCommonName = ldapContact.findUserByCommonName("Liel Ran");
			
			
 
		} catch (DataAccessException e) {
			System.out.println("Error occured " + e.getCause());
		}
	}
}
