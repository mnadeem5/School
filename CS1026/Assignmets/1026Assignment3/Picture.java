import java.awt.*;

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

 public void morphStage(Picture startPicture, Picture endPicture, int numStages, int k)
 {
   //initializing all the intger values for the start and end RBG colors
   int colourValueRed,colourValueBlue,colourValueGreen,startValueRed,startValueBlue,startValueGreen,
     endValueRed, endValueBlue, endValueGreen;
   //initializing the pixel variables start end and final 
   Pixel pixelStart, pixelEnd, colourValue;
   
   //nested forloop to go through the pictures 
   for (int i =0; i<startPicture.getWidth(); i++)
   {
     for (int j=0; j<startPicture.getHeight(); j++)
     {
       //giving values the starting pixels and setting the vales of the starting RBG colors 
       pixelStart=startPicture.getPixel(i,j);
       startValueRed=pixelStart.getRed();
       startValueBlue=pixelStart.getBlue();
       startValueGreen=pixelStart.getGreen();
       
       //giving values the ending pixels and setting the vales of the ending RBG colors 
       pixelEnd=endPicture.getPixel(i,j);
       endValueRed=pixelEnd.getRed();
       endValueBlue=pixelEnd.getBlue();
       endValueGreen=pixelEnd.getGreen();
       
       //the calculation of RBG color values for the intermediate pictures 
       colourValueRed=startValueRed+((endValueRed-startValueRed)/(numStages+1))*k;
       colourValueBlue=startValueBlue+((endValueBlue-startValueBlue)/(numStages+1))*k;
       colourValueGreen=startValueGreen+((endValueGreen-startValueGreen)/(numStages+1))*k;
       
       //setting the color values for each pixel in the final intermediate picture
       colourValue = this.getPixel(i,j);
       colourValue.setRed(colourValueRed);
       colourValue.setBlue(colourValueBlue);
       colourValue.setGreen(colourValueGreen);
     }
   }
 }
 
} // end of class Picture, put all new methods before this