import java.awt.Color;
public class MakeCollage
{
  public static void main(String[] args)
  {
    Picture background = new Picture(576,480);
    background.show();
    
    String fileName = FileChooser.pickAFile();
    Picture image = new Picture (fileName);
    background.copyPictureTo(image,192,120);
    
    Picture image1 = new Picture (fileName);
    image1.lighten();
    background.copyPictureTo(image1,96,0);
    
    Picture image2 = new Picture (fileName);
    image2.darken();
    background.copyPictureTo(image2,288,0);
    
    Picture image3 = new Picture (fileName);
    image3.blacknwhite();
    background.copyPictureTo(image3,96,240);
    
    Picture image4 = new Picture (fileName);
    image4.black();
    background.copyPictureTo(image4,288,240);
    
    Picture image5 = new Picture (fileName);
    image5.red();
    background.copyPictureTo(image5,0,360);
    
    Picture image6 = new Picture (fileName);
    image6.blue();
    background.copyPictureTo(image6,192,360);
    
    Picture image7 = new Picture (fileName);
    image7.green();
    background.copyPictureTo(image7,384,360);
    
    background.repaint();
    
    background.write("MskeCollage.jpg");
  }
}