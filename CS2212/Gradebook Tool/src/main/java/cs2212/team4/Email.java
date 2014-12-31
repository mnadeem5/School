package cs2212.team4;

import java.io.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

/**
 * This class contains functionality to send emails containing student reports.
 * 
 * @author Zaid Albirawi
 * @version 2.0 3/31/2014
 */
public class Email {

	/* ************************************************************
	 * Instance Variables
	 ************************************************************ */
	
	// message subject for email
	private static String msgSubject = "", msg = "";
	// properties regarding smtp for email sender
	private static Properties properties;
	// student email is being sent to
	private static Student student;
	// course student is in
	private static Course course;
	// existence of report
	private static boolean boolReport = false;
	
	/**
	 * Constructor creates an email object, containing specifications of how email should be sent
	 * 
	 * @param properties containing the specs for email sending
	 */
	public Email(Properties properties) {
		Email.properties = properties;
	}

	/**
	 * Constructor creates an email object, containing specifications of how email should be sent
	 * 
	 * @param course of student email is being sent to 
	 * @param student email is being sent to
	 * @param msgSubject subject of email
	 * @param msg message of email
	 * @param boolReport status of report
	 * @param properties containing the specs for email sending
	 */
	public Email(Course course, Student student, String msgSubject, String msg,
			boolean boolReport, Properties properties) {
		Email.msgSubject = msgSubject;
		Email.msg = msg;
		Email.student = student;
		Email.course = course;
		Email.boolReport = boolReport;
		Email.properties = properties;
	}
	
	/**
	 * sendEmail method sends email based on specifications from email object
	 * 
	 * @return returnMsg String returned from sendMessage method if error exists
	 */
	public String sendEmail() {
		String returnMsg;

		Session session = getSession(properties);
		if (!(returnMsg = sendMessage(session, properties, student.getEmail()))
				.equals(""))
			return returnMsg;
		File reportFile = new File("gradebook-files/report.pdf");
		if (reportFile.exists())
			if (!reportFile.delete())
				return "Error, temprary file cannot be removed";
		return "";
	}

	/**
	 * authenUser method authenticates user
	 * 
	 * @return returnMsg string returned from sendMessage method if error exists
	 */
	public String authenUser() {
		Email.msgSubject = "Gradebook has authenticated your email";
		Email.msg = "Gradebook has authenticated your email address, and you are now ready to go!";
		Email.boolReport = false;
		String returnMsg;

		Session session = getSession(properties);
		if (!(returnMsg = sendMessage(session, properties,
				properties.getProperty("smtp.username"))).equals(""))
			return returnMsg;
		return "";
	}
	
	/**
	 * getSession method gets session based on specifications from properties
	 * @param properties containing username and password
	 * 
	 * @return session object containing specs from properties
	 */
	private static Session getSession(final Properties properties) {
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						String username = properties
								.getProperty("smtp.username");
						String password = properties
								.getProperty("smtp.password");
						return new PasswordAuthentication(username, password);
					}
				});
		return session;
	}
	
	/**
	 * sendMessage method sends message based on session object, properties object, and student email to be sent to
	 * @param session object for email sending specs
	 * @param properties detailing email sending specs
	 * @param studentEmail email of student message is being sent to
	 * 
	 * @return error message if message failed
	 */
	private static String sendMessage(Session session, Properties properties,
			String studentEmail) {
		try {
			String returnMsg;

			Message msg = new MimeMessage(session);
			String username = properties.getProperty("smtp.username");
			Address sender = new InternetAddress(username, username);

			msg.setFrom(sender);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					studentEmail));
			msg.setSubject(msgSubject);

			Multipart multiPart = new MimeMultipart();

			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText(Email.msg);
			multiPart.addBodyPart(textPart);

			if (Email.boolReport) {
				returnMsg = generateReport();
				
				if (!returnMsg.equals(""))
					return returnMsg;

				MimeBodyPart fileAttachmentPart = new MimeBodyPart();
				File attachmentFile = new File(
						"gradebook-files/report.pdf");
				 
				if (!attachmentFile.exists())
					return "Not able to find the generated report";
				DataSource source = new FileDataSource(attachmentFile);
				fileAttachmentPart.setDataHandler(new DataHandler(source));
				fileAttachmentPart.setFileName("report.pdf");

				multiPart.addBodyPart(fileAttachmentPart);
			}
			msg.setContent(multiPart);
			Transport.send(msg);
		} catch (UnsupportedEncodingException e) {
			return "Failed to create a new sender internet address";
		} catch (MessagingException e) {
			System.out.print(e);
			return "Failed to create a message";
		}
		return "";
	}
	
	/**
	 * generateReport method generates a report for the email
	 * 
	 * @return returnMsg if report generation error
	 */
	private static String generateReport() {
		String returnMsg;
		Report report = new Report(course, student);
		if (!(returnMsg = report.generateReport()).equals(""))
			return returnMsg;
		return "";
	}
}
