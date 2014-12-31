/**
 * PhotoInfo.java
 *
 *
 * Zaid Albirawi 
 * 2012/12/6
 */
public class PhotoInfo
{
  //defines instance variables
  public String id, category, caption, photoFile;
  public int year, month, day;
  public Picture photoPic;
  
  /**
 * Purpose:Gives the instance variables values, creats the labeled photo and adds the text to it.
 * Parameters: ident (String), nday (int), nmonth (int), nyear (int), cat (String), cap (String), picFile (String)
 * Return Value:None.
 */
  public PhotoInfo(String ident, int nday, int nmonth, int nyear, String cat, String cap, String picFile)
  {
    Picture picObj=new Picture (picFile);
    Picture photoObj=new Picture(picObj.getWidth(),picObj.getHeight()+80);
    //gives values to the instance variables
    this.id=ident;
    this.year=nyear;
    this.month=nmonth;
    this.day=nday;
    this.category=cat;
    this.caption=cap;
    this.photoFile=picFile;
    //copys the orignal photo and a bigger one
    photoObj.copyPictureTo(picObj,0,0);
    //adds the text to the white space under the new photo
    photoObj.drawString(getCategory(), 10, photoObj.getHeight()-60, 14);
    photoObj.drawString(getCaption(), 10, photoObj.getHeight()-40, 14);
    photoObj.drawString(getDate(), 10, photoObj.getHeight()-20, 14);
    //gives a valuve to the instance variable photoPic
    this.photoPic=photoObj;
  }
  
  /**
 * Purpose: A getter for the photo Identifier.
 * Parameters: None.
 * Return Value:A string that contains the identifier
 */
  public String getIdentifier(){return this.id;}
  
  /**
 * Purpose: A getter for the photo Day.
 * Parameters: None.
 * Return Value:An integer that contains the day
 */
  public int getDay(){return this.day;}
  
  /**
 * Purpose: A getter for the photo Month.
 * Parameters: None.
 * Return Value:An integer that contains the month
 */
  public int getMonth(){return this.month;}
  
  /**
 * Purpose: A getter for the photo Year.
 * Parameters: None.
 * Return Value:An integer that contains the year
 */
  public int getYear(){return this.year;}
  
  /**
 * Purpose: A getter for the photo Date.
 * Parameters: None.
 * Return Value:A string that contains the date
 */
  public String getDate()
  {
    //puts the year, month, and day in one string
    return this.getYear()+", "+this.getMonth()+", "+this.getDay();
  }
  
  /**
 * Purpose: A getter for the photo Category.
 * Parameters: None.
 * Return Value:A string that contains the category type
 */
  public String getCategory(){return this.category;}
  
  /**
 * Purpose: A getter for the photo Caption.
 * Parameters: None.
 * Return Value:A string that contains the caption
 */
  public String getCaption(){return this.caption;}
  
   /**
 * Purpose: A getter for the PhotoFile.
 * Parameters: None.
 * Return Value:A string that contains the photo text file name
 */
  public String getPhotoFile(){return this.photoFile;}
  
  /**
 * Purpose: A getter for the photoPic.
 * Parameters: None.
 * Return Value:A picture object that contains the labeled photo
 */
  public Picture getLabeledPhoto(){return photoPic;}
  
   /**
 * Purpose: A getter for the photo information.
 * Parameters: None.
 * Return Value:A string that contains the information of the photo
 */
  public String toString()
  {
    //defines a new variable
    String returnInfo;
    //gives returnInfo a value
    returnInfo=this.caption+"("+this.id+", "+this.category+", "+this.photoFile+", "+getDate()+")";
    //returns the value of returnInfo
    return returnInfo;
  }
  
  //public static void main (String[] args) 
  //{
    //PhotoInfo test = new PhotoInfo("cat01", 17, 06, 2012, "Pets", "Where's_my_glass_cutter!", "cat_squirrel.jpg");
    //System.out.println(test.getIdentifier());
    //System.out.println(test.getDay());
    //System.out.println(test.getMonth());
    //System.out.println(test.getYear());
    //System.out.println(test.getCategory());
    //System.out.println(test.getCaption());
    //System.out.println(test.getPhotoFile());
    //System.out.println(test.toString());
    //(test.getLabeledPhoto()).show();
  //}
}