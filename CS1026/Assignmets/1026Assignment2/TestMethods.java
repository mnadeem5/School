import java.awt.Color;
public class TestMethods
{
  public static void main(String[] args)
  {
    String fileName = FileChooser.pickAFile();
    Picture image = new Picture (fileName);
    image.show();
    image.lighten();image.repaint();
    
    String fileName2 = FileChooser.pickAFile();
    Picture image2 = new Picture (fileName2);
    image2.show();
    image2.darken();image2.repaint();
  }
}