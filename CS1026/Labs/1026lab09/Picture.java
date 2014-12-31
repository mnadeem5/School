import java.awt.*;
import java.text.*;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * Copyright Georgia Institute of Technology 2004-2005
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Picture(int width, int height)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
 /**
  * Modified version of method from page 154 of the textbook for copying pictures
  */
 public void copyPictureTo(Picture sourcePicture, int xStart, int yStart)
 {
   Pixel sourcePixel = null;
   Pixel targetPixel = null;
   
   //loop through the columns
   try{
   for (int sourceX = 0, targetX = xStart;
        sourceX < sourcePicture.getWidth();
        sourceX++, targetX++)
   {
     //loop through the rows
     for (int sourceY = 0,
          targetY = yStart;
          sourceY < sourcePicture.getHeight();
          sourceY++, targetY++)
     {
       sourcePixel = sourcePicture.getPixel(sourceX,sourceY);
       targetPixel = this.getPixel(targetX,targetY);
       targetPixel.setColor(sourcePixel.getColor());
     } 
   }
  }catch(IndexOutOfBoundsException ex){System.out.println("Either xStart or yStart is out of bounds");System.exit(0);}
} 

 public int countWhitePixels() 
 { 
   Pixel pixelObj; 
   int counter = 0;  
   // loop through the columns (x direction) 
   for (int x = 0; x < this.getWidth(); x++)  
   { 
     // loop through the rows (y direction) 
     for (int y = 0; y < this.getHeight(); y++)   
     { 
       // get the pixel at the x and y location 
       pixelObj = this.getPixel(x,y); 
       
       if (pixelObj.getRed()==255 && pixelObj.getGreen()==255 
             && pixelObj.getBlue()== 255) 
         counter = counter + 1;           
     }  
   }  
   return counter; 
 }

 public int countNonWhitePixels()
 {
   Pixel pixelObj; 
   int counter = 0;  
   for (int x = 0; x < this.getWidth(); x++)  
   { 
     for (int y = 0; y < this.getHeight(); y++)   
     { 
       pixelObj = this.getPixel(x,y); 
       //pixelObj.setColor(Color.WHITE);
       if (pixelObj.getColor()!=Color.WHITE)
         counter++;
     }                   
     
   }  
   return counter; 
 }
 
 public int countNonWhitePixels2(int numPixels) 
 { 
   int numNotWhite=numPixels-this.countWhitePixels();return numNotWhite; 
 }
 
 public boolean equalPictureSize(Picture otherPicture)
 {
   if (this.getWidth()==otherPicture.getWidth()&&this.getHeight()==otherPicture.getHeight())return true;
   else return false;    
 }
 
 public void copyLeftHalf(Picture sourcePicture)
 {
   Pixel sourcePixel = null; 
   Pixel targetPixel = null;
   for (int sourceX = 0, targetX = 0; sourceX < sourcePicture.getWidth()/2; sourceX++, targetX++)    
   { 
     for (int sourceY = 0, targetY = 0;sourceY < sourcePicture.getHeight();sourceY++, targetY++)    
     { 
       sourcePixel = sourcePicture.getPixel(sourceX,sourceY); 
       targetPixel = this.getPixel(targetX,targetY); 
       targetPixel.setColor(sourcePixel.getColor()); 
     } 
   }
 }
 
 public Picture copyLeftHalf()
 {
   Pixel sourcePixel = null; 
   Pixel targetPixel = null;
   Picture sourcePictrue = new Picture (this.getWidth()/2, this.getHeight());
   for (int sourceX = 0, targetX = 0; sourceX < this.getWidth()/2; sourceX++, targetX++)    
   { 
     for (int sourceY = 0, targetY = 0;sourceY < this.getHeight();sourceY++, targetY++)    
     { 
       targetPixel = this.getPixel(targetX,targetY); 
       sourcePixel = sourcePictrue.getPixel(sourceX,sourceY);
       sourcePixel.setColor(targetPixel.getColor()); 
     } 
   }
   return sourcePictrue;
 }
 
} // end of class Picture, put all new methods before this
 
