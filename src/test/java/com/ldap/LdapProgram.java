//package com.ldap;
//
//import java.util.Hashtable;
//
//import javax.naming.Context;
//import javax.naming.NamingException;
//import javax.naming.directory.Attribute;
//import javax.naming.directory.Attributes;
//import javax.naming.directory.BasicAttribute;
//import javax.naming.directory.BasicAttributes;
//import javax.naming.directory.DirContext;
//import javax.naming.directory.InitialDirContext;
//import javax.naming.Context;
//import javax.naming.NamingEnumeration;
//import javax.naming.directory.*;
//import java.util.Hashtable;
//
//public class LdapProgram {
//
//	// Attribute names
//	private static final String USER_ACCOUNT_CONTROL_ATTR_NAME = "userAccountControl";
//	private static final String PASSWORD_ATTR_NAME = "unicodepwd";
//	private static final String DISTINGUISHED_NAME_ATTR_NAME = "distinguishedname";
//	private static final String MEMBER_ATTR_NAME = "member";
//
//	// usercontrol params
//	private static final int FLAG_TO_DISABLE_USER = 0x2;
//	private static final int ADS_UF_DONT_EXPIRE_PASSWD = 0x10000;
//	private static final int USER_CONTROL_NORMAL_USER = 512;
//
//	private static int getUserAccountControl(User user) {
//		int userAccounControl = USER_CONTROL_NORMAL_USER;
//		if (!user.isExpirePasswd()) {
//			userAccounControl |= ADS_UF_DONT_EXPIRE_PASSWD;
//		}
//		return userAccounControl;
//	}
//
//	private static byte[] encodePassword(String password) throws Exception {
//		String newQuotedPassword = "\"" + password + "\"";
//		return newQuotedPassword.getBytes("UTF-16LE");
//	}
//
//	public static void main(String[] args) {
//
//		/*
//		 * <property name="url" value="ldap://10.1.1.155:389" /> <property
//		 * name="base" value="dc=skyfence-test,dc=com" /> <property
//		 * name="userDn" value="SKYFENCE-TEST\Administrator" /> <property
//		 * name="password" value="Barbapapa1@" />
//		 */
//
//		Hashtable env = new Hashtable();
//		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//		env.put(Context.PROVIDER_URL, "ldap://10.1.1.155:389");
//		env.put(Context.SECURITY_AUTHENTICATION, "simple");
//		env.put(Context.SECURITY_PRINCIPAL, "SKYFENCE-TEST\\Administrator");// specify
//		// the
//		// username
//
//		env.put(Context.SECURITY_CREDENTIALS, "Barbapapa1@");// specify the
//																// password
//		// TODO code application logic here
//
//		// entry's DN
//		String entryDN = "uid=user2,ou=system";
//
//		// entry's attributes
//		// Attribute dn = new BasicAttribute("dn", "dc=skyfence-test,dc=com");
//		Attribute cn = new BasicAttribute("cn", "CN=Users,DC=skyfence-test,DC=com");
//		Attribute sn = new BasicAttribute("sn", "Test7");
//		Attribute mail = new BasicAttribute("mail", "newuser@foo.com");
//		Attribute phone = new BasicAttribute("telephoneNumber", "+1 222 3334444");
//		Attribute oc = new BasicAttribute("objectClass");
//		oc.add("top");
//		oc.add("person");
//		oc.add("organizationalPerson");
//		oc.add("inetOrgPerson");
//		DirContext ctx = null;
//		
//		User user = new User();
//		user.setUserName("test1");
//		user.setDisplayName("lielRan1");
//		user.setFirstName("liel1");
//		user.setLastName("ran1");
//		user.setPassword("password");
//		user.setUserName("lielran1");
//		
//		
//		
//		 try {
//	            Attributes userAttributes = new BasicAttributes();
//	            userAttributes.put("objectclass", "person");
//	            userAttributes.put("objectclass", "user");
//	            userAttributes.put("userPrincipalName", user.getEmailAddress());
//	            userAttributes.put("sAMAccountName", user.getUserName());
//	            userAttributes.put("givenName", user.getFirstName());
//	            userAttributes.put("sn", user.getLastName());
//	            userAttributes.put("displayName", user.getDisplayName());
//	            int userAccounControl = getUserAccountControl(user);
//	            userAttributes.put("userAccountControl", "" + userAccounControl);
//	            userAttributes.put("unicodepwd", encodePassword(user.getPassword()));
//	            
//	         //   ldapTemplate.bind(getDnFrom(user.getUserName()), null, userAttributes);
//	            
//	            
//	            
//	   /*     } catch (NameAlreadyBoundException e) {
//	            throw new DuplicateUserException("User:[" + user.getUserName() + "] allready exists in AD.", e);
//	        } catch (OperationNotSupportedException e) {
//	            throw new PasswordStrengthException("Password:[" + user.getPassword() + "] does not pass the strength password validation.", e);
//	        } catch (Throwable e) {	            throw new LdapException("Problems creating user.", e);
//	        }
//		*/
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//
//		try {
//			final String accountName = "Administrator";
//			final String ldapSearchBase = "CN=Users,DC=skyfence-test,DC=com";
//			// get a handle to an Initial DirContext
//			ctx = new InitialDirContext(env);
//
//			String searchFilter = "(&(objectClass=user)(sAMAccountName=" + accountName + "))";
//
//			SearchControls searchControls = new SearchControls();
//			searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//
//			NamingEnumeration<SearchResult> results = ctx.search(ldapSearchBase, searchFilter, searchControls);
//
//			SearchResult searchResult = null;
//			if (results.hasMoreElements()) {
//				searchResult = (SearchResult) results.nextElement();
//				System.out.println(searchResult);
//				// make sure there is not another item available, there should
//				// be only 1 match
//				if (results.hasMoreElements()) {
//					System.err.println("Matched multiple users for the accountName: " + accountName);
//				}
//			}
//
//			
//			
//			
//			/*
//			 * SearchControls controls = new SearchControls();
//			 * controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//			 * NamingEnumeration results = ctx.search("",
//			 * "(objectclass=Administrator)", controls); while
//			 * (results.hasMore()) { SearchResult searchResult = (SearchResult)
//			 * results.next(); Attributes attributes =
//			 * searchResult.getAttributes(); Attribute attr =
//			 * attributes.get("cn"); String cn1 = (String) attr.get();
//			 * System.out.println(" Person Common Name = " +
//			 * attributes.get("cn"));
//			 * System.out.println(" Person Display Name = " +
//			 * attributes.get("displayName"));
//			 * System.out.println(" Person logonhours = " +
//			 * attributes.get("logonhours"));
//			 * System.out.println(" Person MemberOf = " +
//			 * attributes.get("memberOf")); }
//			 */
//
//			// build the entry
//			BasicAttributes entry = new BasicAttributes();
//			entry.put(cn);
//			entry.put(sn);
//			entry.put(mail);
//			entry.put(phone);
//
//			entry.put(oc);
//
//			// Add the entry
//			ctx.createSubcontext(entryDN, entry);
//			System.out.println("AddUser: added entry " + entryDN + ".");
//
//		} catch (NamingException e) {
//			System.err.println("AddUser: error adding entry." + e);
//		}
//	}
//}