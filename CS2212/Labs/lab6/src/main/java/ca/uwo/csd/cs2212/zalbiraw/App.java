package ca.uwo.csd.cs2212.zalbiraw; 
import java.io.*; 
import java.util.*; 
import net.sf.jasperreports.engine.*; 
import net.sf.jasperreports.engine.data.*; 
import net.sf.jasperreports.engine.design.*; 
import net.sf.jasperreports.engine.xml.*; 
import javax.imageio.ImageIO; 
import java.awt.image.BufferedImage;

public class App { 
  
  private final static String REPORT_FILENAME = "accounts_receivable_report"; 
  
  private static InputStream loadResource(String filename) { 
    return App.class.getClassLoader().getResourceAsStream(filename); } 
  
  
  public static void main(String[] args) throws Exception 
  { 
    InputStream reportStream = loadResource(REPORT_FILENAME + ".jrxml"); 
    InputStream logoStream = loadResource("logo.png"); 
    BufferedImage logo = ImageIO.read(logoStream);
    Collection<Customer> customers = CustomerProvider.loadCustomers(); 
    JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(customers);
    Map<String, Object> parameters = new HashMap<String, Object>(); 
    parameters.put("logo", logo);
    JasperDesign jasperDesign = JRXmlLoader.load(reportStream); 
    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign); 
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport , parameters , beanColDataSource); 
    JasperExportManager.exportReportToPdfFile(jasperPrint , REPORT_FILENAME + ".pdf"); 
    JasperExportManager.exportReportToHtmlFile(jasperPrint , REPORT_FILENAME + ".html");
  }
}