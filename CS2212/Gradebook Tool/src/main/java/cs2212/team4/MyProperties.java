package cs2212.team4;

import java.util.Properties;

/**
*
* A class that creates properties for an email
*
* team4-gradebook application
*
* @author Zaid Albirawi
* @version 1.6 3/25/2014
*/
public class MyProperties {

	private Properties properties;

	/**
	 * Constructor that creates properties required for an email class
	 * 
	 * @param username Username for the smtp
	 * @param password password for the smtp
	 * @param provider The email provider
	 * 
	 */
	public MyProperties(String username, String password, String provider) {
		properties = new Properties();

		switch (provider) {
		case "gmail":
			properties.put("mail.smtp.host", "smtp.gmail.com");
			break;
		case "yahoo":
			properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
			break;
		case "hotmail":
			properties.put("mail.smtp.host", "smtp.live.com");
			break;
		}
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("smtp.username", username);
		properties.put("smtp.password", password);
	}
	
	/**
	 * Constructor that creates properties required for an email class
	 * 
	 * @param mailHost The mailing host
	 * @param socketPort The socket port for the email
	 * @param port The port for which to send the email
	 * @param username Username for the smtp
	 * @param password password for the smtp
	 * 
	 */
	public MyProperties(String mailHost, String port,
			String username, String password) {
		properties = new Properties();

		properties.put("mail.smtp.host", mailHost);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.socketFactory.port", port);
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", port);
		properties.put("smtp.username", username);
		properties.put("smtp.password", password);
	}

	/**
	 * Get properties method that gives the properties we set up
	 * 
	 * @return The MyProperties object we defined in this class
	 * 
	 */
	public Properties getProperties() {
		return properties;
	}
}
