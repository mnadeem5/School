package cs2212.team4;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
*
* A class that mimics a report for a student and their grades in a course
*
* team4-gradebook application
*
* @author Zaid Albirawi
* @version 1.6 3/25/2014
*/

public class Report {

	private Course course;
	private Student student;

	/**
	 * Constructor that creates report for a student in a given course
	 * 
	 * @param student The student for whom to create the report
	 * @param course The course from which the report is created
	 * 
	 */
	public Report(Course course, Student student) {
		this.course = course;
		this.student = student;
	}

	/**
	 * Get an input stream for the file where the grades are stored
	 * 
	 * @param filename the file path where the file with the grades is located
	 * @return An input stream based on the passed file
	 * 
	 */
	private static InputStream loadResource(String filename) {
		return GradebookGUI.class.getClassLoader()
				.getResourceAsStream(filename);
	}

	/**
	 * Collect and return a collection of student report data
	 * 
	 * @param student The student for whom to collect the report data
	 * @param course The course from which the report data is collected
	 * @return a collection of students' deliverables data
	 * 
	 */
	public static Collection<StudentReportData> reportData(Student student,
			Course course) {
		Collection<StudentReportData> temp = new ArrayList<StudentReportData>();
		temp.add(new StudentReportData(student, course, -1));
		for (int i = 0; i < course.getDeliverableListSize(); i++) {
			if (course.getDeliverable(i) != null) {
				temp.add(new StudentReportData(student, course, i));
			}
		}
		return temp;
	}

	/**
	 * Generate a report for the student in this class
	 * 
	 * @return empty string if report successfully generated. If cannot find report.jrxml or logo.png, will return appropriate message for either situation.
	 * @throws JRException A JR exception meaning the file is non-writable
	 * @throws IOException and input/output exception
	 * 
	 */
	public String generateReport() {
		try {
			InputStream logoStream = loadResource("cs2212/team4/logo.png");
			if (logoStream == null)
				return "Cannot find logo.png";
			InputStream reportStream = loadResource("cs2212/team4/report.jrxml");
			if (reportStream == null)
				return "Cannot find report.jrxml";
			BufferedImage logo = ImageIO.read(logoStream);
			Collection<StudentReportData> reportData = reportData(student,
					course);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					reportData);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("logo", logo);
			JasperDesign jasperDesign = JRXmlLoader.load(reportStream);
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, beanColDataSource);
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					"gradebook-files/report.pdf");
		} catch (JRException e) {
			return "File is non-writable";
		} catch (IOException e) {
			return "Gradebook was unable to import required files";
		}
		return "";
	}
}
