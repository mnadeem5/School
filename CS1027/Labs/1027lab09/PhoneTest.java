import java.io.*;
import java.util.Iterator;
/**
 * PhoneTest.java:
 * This class creates an Ordered List of Phone objects.
 * @author CS1027 for Lab
 */

public class PhoneTest {

   public static void main(String[] args) throws Exception {

   // get the filename from the user
     
      BufferedReader keyboard = new BufferedReader
                                 (new InputStreamReader(System.in),1);       
      System.out.println("Enter name of the input file: ");
      String filename= keyboard.readLine();
    
   // create object that controls file reading and opens the file
         
      InStringFile reader = new InStringFile(filename);
      System.out.println("\nReading from file " + filename + "\n");

    // your code to create (empty) ordered list here

      ArrayOrderedList <Phone> ol= new ArrayOrderedList <Phone>();
      
   // read data from file two lines at a time (name and phone number)
     
      String name, phone;
      do {
        name = (reader.read());
        phone = (reader.read());

        // your code to add the entry to your ordered list here
        
        Phone element = new Phone (name, phone);
        ol.add(element);
        
      } while (!reader.endOfFile()); 
   
      //System.out.println("Here is my phone book:");
      //System.out.println(ol.toString());
      //Iterator<Phone> iter = ol.iterator();
      //while(iter.hasNext() )
      //{
    	  //System.out.println(iter.next().getName());
      //}
      //C:\Users\ZiZo\Desktop\phone.txt
      ol.toString2();
      
   // close file
      
      reader.close();
      System.out.println("\nFile " + filename + " is closed.");
      
     
   }
}
