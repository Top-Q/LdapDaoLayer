package com.dao;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.swing.ImageIcon;

import org.springframework.ldap.core.AttributesMapper;

public class ContactAttributeMapper implements AttributesMapper {

	public ContactAttributeMapper(LdapService ldapService) {
		super();
		this.ldapService = ldapService;
	}

	private LdapService ldapService;

	private final String commonNameAtr = "cn";

	public Object mapFromAttributes(Attributes attributes) throws NamingException {
		Contact contact = new Contact();
		Attribute mail = attributes.get("mail");
		if (mail != null)
			contact.setMail((String) mail.get());

		Attribute manager = attributes.get("manager");
		if (manager != null && ldapService != null) {
			String managerStr = (String) manager.get();
			managerStr = managerStr.toLowerCase();
			String cnManagerName = managerStr.substring(managerStr.indexOf(commonNameAtr) + commonNameAtr.length() + "=".length(),
					managerStr.indexOf(","));
			Contact theManager = ldapService.findUserByCommonName(cnManagerName);
			contact.setManager(theManager);
			contact.setManagerEmail(theManager.getMail());
			contact.setManagerName(theManager.getName());

		} else {
			if (ldapService == null && manager != null) {
				System.err.println("[Error]:The ldapService was not set, there is no way to get the manager data(the data exist on the AD");
				System.err.println("[Error]:Validate the ldapService was set properly");
			}

		}

		// TODO: save as byte[]
		Attribute pic = attributes.get("thumbnailphoto");
		if (pic != null) {
			Object object = pic.get();
			contact.setPicture((byte[]) pic.get());
			ImageIcon imageIcon = new ImageIcon((byte[]) ( object)  );
			
			Image image = imageIcon.getImage();

			BufferedImage bufferedImage = new BufferedImage(120, 120, BufferedImage.TYPE_BYTE_INDEXED);
			Graphics2D g2 = bufferedImage.createGraphics();
			g2.drawImage(image, null, null);

			File imageFile = new File("C:\\pic.jpeg");
			try {
				ImageIO.write(bufferedImage, "jpeg", imageFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

		Attribute title = attributes.get("title");
		if (title != null) {
			contact.setJobTitle((String) title.get());
		}

		Attribute department = attributes.get("department");
		if (department != null) {
			contact.setDepartment((String) department.get());
		}

		Attribute firstName = attributes.get("givenname");
		if (firstName != null)
			contact.setFirstName((String) firstName.get());

		Attribute lasttName = attributes.get("sn");
		if (lasttName != null)
			contact.setLastName((String) lasttName.get());

		Attribute samaccountname = attributes.get("samaccountname");
		if (samaccountname != null)
			contact.setDomainAccountName((String) samaccountname.get());

		Attribute displayname = attributes.get("displayname");
		if (displayname != null)
			contact.setDisplayname((String) displayname.get());

		Attribute name = attributes.get("name");
		if (displayname != null)
			contact.setName((String) name.get());

		System.out.println(contact.toString());

		return contact;
	}

	public LdapService getLdapService() {
		return ldapService;
	}

	public void setLdapService(LdapService ldapService) {
		this.ldapService = ldapService;
	}

}
