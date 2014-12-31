import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
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
  
  public void lighten()
  {
    //defines the pixel and color objects and the x pointer
    Pixel pixelObj;Color colorObj;
    int x=0;
    //while and for loops so the program is able to go throw every pixel in the picture and brighten it
    while (x < this.getWidth()) 
    {
      for (int y = 0; y < this.getHeight(); y++)  
      {
        //defines and sets the pixels and color values
        pixelObj = this.getPixel(x,y);
        colorObj = pixelObj.getColor();
        colorObj = colorObj.brighter();
        pixelObj.setColor(colorObj);
      }
      x++;
    }
  }
  
  public void darken()
  {
    //defines the pixel and color objects and the x pointer
    Pixel pixelObj;Color colorObj;
    int x=0;
    //while and for loops so the program is able to go throw every pixel in the picture and darken it
    while (x < this.getWidth()) 
    {
      for (int y = 0; y < this.getHeight(); y++)  
      {
        //defines and sets the pixels and color values
        pixelObj = this.getPixel(x,y);
        colorObj = pixelObj.getColor();
        colorObj = colorObj.darker();
        pixelObj.setColor(colorObj);
      } 
      x++;
    }
  }
  
  public void grayscale()
  {
    //defines the pixel and color objects and the x and z pointers
    Pixel pixelObj;Color colorObj;
    int x=0, z;
    //while and for loops so the program is able to go throw every pixel in the picture and scale the colors
    while (x < this.getWidth()) 
    {
      for (int y = 0; y < this.getHeight(); y++)  
      {
        //defines sets and calculates the pixels and color values
        pixelObj = this.getPixel(x,y);
        colorObj = pixelObj.getColor();
        z = (colorObj.getRed()+colorObj.getBlue()+colorObj.getGreen())/3;
        pixelObj.setColor(new Color(z,z,z));
      }
      x++;
    }
  }
  
  public void red()
  {
    //defines the pixel and color objects and the x pointer
    Pixel pixelObj;Color colorObj;
    int x=0;
    //while and for loops so the program is able to go throw every pixel in the picture and eliminate non-red colors
    while (x < this.getWidth()) 
    {
      for (int y = 0; y < this.getHeight(); y++)  
      {
        //defines and sets the pixels and color values
        pixelObj = this.getPixel(x,y);
        colorObj = pixelObj.getColor();
        pixelObj.setColor(new Color(colorObj.getRed(),0,0));
      }
      x++;
    }
  }
  
  public void blue()
  {
    //defines the pixel and color objects and the x pointer
    Pixel pixelObj;Color colorObj;
    int x=0;
        //while and for loops so the program is able to go throw every pixel in the picture and eliminate non-blue colors
    while (x < this.getWidth()) 
    {
      for (int y = 0; y < this.getHeight(); y++)  
      {
        //defines and sets the pixels and color values
        pixelObj = this.getPixel(x,y);
        colorObj = pixelObj.getColor();
        pixelObj.setColor(new Color(0,colorObj.getBlue(),0));
      }
      x++;
    }
  }
  
  public void green()
  {
    //defines the pixel and color objects and the x pointer
    Pixel pixelObj;Color colorObj;
    int x=0;
    //while and for loops so the program is able to go throw every pixel in the picture and eliminate non-green colors
    while (x < this.getWidth()) 
    {
      for (int y = 0; y < this.getHeight(); y++)  
      {
        //defines and sets the pixels and color values
        pixelObj = this.getPixel(x,y);
        colorObj = pixelObj.getColor();
        pixelObj.setColor(new Color(0,0,colorObj.getGreen()));
      }
      x++;
    }
  }
  
  public void blacknwhite()
  {
    //defines the pixel and color objects and the x and c pointers
    Pixel pixelObj, pixelObj1;Color colorObj, colorObj1;
    int x=0,c;
    //while and for loops so the program is able to go throw every pixel in the picture and approx the pixel color to either black or white
    while (x < this.getWidth()) 
    {
      for (int y = 0; y < this.getHeight(); y++)  
      {
        //defines sets calculates and compares the pixels and color values
        pixelObj = this.getPixel(x,y);
        colorObj = pixelObj.getColor();
        if ((colorObj.getRed()+colorObj.getBlue()+colorObj.getGreen())/3<128){c=0;} else {c=255;}
        pixelObj.setColor(new Color(c,c,c));
      }
      x++;
    }
  }
  
  public void black()
  {
    //defines the pixel and color objects and the x and c pointers
    Pixel pixelObj, pixelObj1;Color colorObj, colorObj1;
    int x=0,c;
    //while and for loops so the program is able to go throw every pixel in the picture and turns an pixels that are dark to black
    while (x < this.getWidth()) 
    {
      for (int y = 0; y < this.getHeight(); y++)  
      {
        //defines sets calculates and compares the pixels and color values
        pixelObj = this.getPixel(x,y);
        colorObj = pixelObj.getColor();
        if ((colorObj.getRed()+colorObj.getBlue()+colorObj.getGreen())/3<128)
        {
          c=0;
          pixelObj.setColor(new Color(c,c,c));
        }
      }
      x++;
    }
  }
  
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

} // end of class Picture, put all new methods before this
 
