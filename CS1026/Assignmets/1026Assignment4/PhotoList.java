/**
 * PhotoList.java
 *
 *
 * Zaid Albirawi 
 * 2012/12/6
 */

import java.util.*;
import java.io.*;

public class PhotoList 
{
  //defines instance variables
  private PhotoInfo[] photoArray;
  private int numPhoto = 0;
  
   /**
 * Purpose: Exctract the information from the text file and creat a new array of PhotoInfo.
 * Parameters: fileName (String)
 * Return Value:None.
 */
  public PhotoList(String fileName)
  {
    //reads the text file
    SimpleReader reader = new SimpleReader(fileName); 
    //stores the text file lines in any array
    String [] lineArray = reader.readFile();
    //sets the instance variable numPhoto value to the file length value
    this.numPhoto=reader.getFileLength();
    //gives the array a value
    this.photoArray=new PhotoInfo[this.numPhoto];
    
    //defines the tokenizer and information variables
    StringTokenizer tokenizer;
    String id, category, caption, photoFile, year, month, day;
    //goes through the read lines and extracts information
    for (int i = 0; i < reader.getFileLength() ; i++)
    {
      //extracts the info through the nextToken object
      tokenizer= new StringTokenizer(lineArray[i]); 
      id=tokenizer.nextToken();
      day=tokenizer.nextToken();
      month=tokenizer.nextToken();
      year=tokenizer.nextToken();
      category=tokenizer.nextToken();
      caption=tokenizer.nextToken();
      photoFile=tokenizer.nextToken();
      //creats multiple PhotoInfo objects and stores them in the array
      this.photoArray[i]=new PhotoInfo(id, Integer.valueOf(day), Integer.valueOf(month), Integer.valueOf(year), category, caption, photoFile);
    }
  }
  
   /**
 * Purpose: Lists the photos that are in the file
 * Parameters: None.
 * Return Value: None.
 */
  public void listPhotos()
  {
    for (int i=0; i<this.numPhoto; i++)
    {
      //prints the file name
      System.out.println(this.photoArray[i].getPhotoFile());
    }
  }
  
    /**
 * Purpose: Displays all the labeled photos
 * Parameters: None.
 * Return Value: None.
 */
  public void showPhotos()
  {
    for (int i=0; i<this.numPhoto; i++)
    {
      //shows the photos
      (this.photoArray[i].getLabeledPhoto()).show();
    }
  }
  
    /**
 * Purpose: Store the labeled photos in a file that the user chooses.
 * Parameters: directory (String)
 * Return Value: None.
 */
  public void storePhotos(String directory)
  {
    //defines the id and cat variables as a string
    String id,cat;
    char end=directory.charAt(directory.length()-1);
    if (end != '/' || end != '\\')
    {
      directory = directory + '/'; 
    }
    File directoryFile = new File(directory);
    if (!directoryFile.exists())
    {
      directoryFile.mkdirs();
    }
    
    for (int i=0; i<this.numPhoto; i++)
    {
      //gives the id and cat values
      id=this.photoArray[i].getIdentifier();
      cat=this.photoArray[i].getCategory();
      //writes the photos to the directory
      (this.photoArray[i].getLabeledPhoto()).write(directory+id+"_"+cat+".jpg");
    }
  }
  
   /**
 * Purpose: stores the photos by date.
 * Parameters: None.
 * Return Value: None.
 */
  public void sortPhotosByDate()
  {
    //defines the strings used in the method
    String month, day, date, id, cat, datePrint;
    //defines the dateAry that will be sorted
    String [] dateAry=new String [this.numPhoto];
    for (int i=0; i<this.numPhoto; i++)
    {
      //checks if the month is one or two numbers, if less than 2 numbers adds a 0 infront of that number
      if ((this.photoArray[i].getMonth())<10)month="0"+this.photoArray[i].getMonth();
      else month=this.photoArray[i].getMonth()+"";
      
      //checks if the month is one or two numbers, if less than 2 numbers adds a 0 infront of that number
      if ((this.photoArray[i].getDay())<10)day="0"+this.photoArray[i].getDay();
      else day=this.photoArray[i].getDay()+"";
      
      //fills the dateAry
      dateAry[i]=this.photoArray[i].getYear()+month+day;
    }
    //sorts the dateAry
    Arrays.sort(dateAry);
    for (int j=0; j<this.numPhoto; j++)
    {
      for (int k=0; k<this.numPhoto; k++)
      {
        //checks if the month is one or two numbers, if less than 2 numbers adds a 0 infront of that number
        if ((this.photoArray[k].getMonth())<10)month="0"+this.photoArray[k].getMonth();
        else month=this.photoArray[k].getMonth()+"";
        //checks if the month is one or two numbers, if less than 2 numbers adds a 0 infront of that number       
        if ((this.photoArray[k].getDay())<10)day="0"+this.photoArray[k].getDay();
        else day=this.photoArray[k].getDay()+"";
        
        //makes a date string to compare with the date string from dateAry
        date=this.photoArray[k].getYear()+month+day;
        //gives string values to the id and cat variables
        id=this.photoArray[k].getIdentifier();
        cat=this.photoArray[k].getCategory();
        //gives a value for the date that will be printed
        datePrint=this.photoArray[k].getDate();
        //checks if the date value is equal to the sorted dateAry for example (20110521==20110521) 
        if (date.equals(dateAry[j]))
        {
          //prints the sorted photos in order
          System.out.println("Date: "+datePrint+"\t File Name: "+id+"_"+cat);
        }
      }
    }
  }
  
  //public static void main (String[] args) 
  //{
    //PhotoList test = new PhotoList ("myPhotoList.txt");
    //test.listPhotos();
    //test.showPhotos();
    //test.storePhotos("zalbiraw");
    //test.sortPhotosByDate();
  //}
}
