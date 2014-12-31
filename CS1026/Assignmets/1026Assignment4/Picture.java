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
  
  public void drawString(String text, int xPos, int yPos, int fontSize) 
  { 
    Graphics g = this.getGraphics(); 
    g.setColor(Color.BLACK); 
    g.setFont(new Font("Arial", Font.BOLD, fontSize)); 
    g.drawString(text, xPos, yPos); 
  }
  
} // end of class Picture, put all new methods before this

