package com.dao;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

public class LdapManualTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		org.springframework.ldap.core.support.LdapContextSource contextSource =new LdapContextSource();
		contextSource.setUrl("dap://10.1.1.155:389");
		contextSource.setBase(" DC=skyfence-test,DC=com");
		contextSource.setUserDn("SKYFENCE-TEST\\Administrator");
		contextSource.setPassword("Barbapapa1@");
		
		
		org.springframework.ldap.core.LdapTemplate ldap = new LdapTemplate(contextSource);
		ldap.setIgnorePartialResultException(true);
		
		com.dao.LdapServiceImpl service = new com.dao.LdapServiceImpl();
		service.setLdapTemplate(ldap);
		
		//start the test
		boolean checkConnection = service.checkConnection();
		
		

	}

}
