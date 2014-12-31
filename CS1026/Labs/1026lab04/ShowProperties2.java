import java.awt.*;
public class ShowProperties2
{
  public static void main (String[] args)
  {
    String fileName = FileChooser.pickAFile();
    Picture picObj = new Picture(fileName);
    picObj.show();
    System.out.println("The picture is " + picObj.getWidth() +" pixels wide and " + picObj.getHeight() + " pixels high");
    int xcoord = 320;
    int ycoord = 240;
    Pixel pixelObj = picObj.getPixel(xcoord,ycoord);
    System.out.println(" Colors of pixel at (" + xcoord + "," + ycoord +") are: red = "+pixelObj.getRed()+" green = "+pixelObj.getGreen()+ " blue = "+pixelObj.getBlue());
  }
}