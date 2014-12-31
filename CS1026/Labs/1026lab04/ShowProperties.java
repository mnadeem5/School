import java.awt.*;
public class ShowProperties
{
  public static void main (String[] args)
  {
    String fileName = FileChooser.pickAFile();
    Picture picObj = new Picture(fileName);
    picObj.show();
    int picWidth = picObj.getWidth();
    int picHeight = picObj.getHeight();
    System.out.println("The picture is " + picWidth +" pixels wide and " + picHeight + " pixels high");
    int xcoord = 320;
    int ycoord = 240;
    Pixel pixelObj = picObj.getPixel(xcoord,ycoord);
    int redValue = pixelObj.getRed();
    int greenValue = pixelObj.getGreen();
    int blueValue = pixelObj.getBlue();
    System.out.println(" Colors of pixel at (" + xcoord + "," + ycoord +") are: red = "+redValue+" green = "+greenValue+ " blue = "+blueValue );
  }
}