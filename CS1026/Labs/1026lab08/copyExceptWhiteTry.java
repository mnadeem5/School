public class copyExceptWhiteTry
{ 
  public static void main (String[] args)  
  { 
    String moon = FileChooser.pickAFile() ; 
    Picture pictureMoon = new Picture(moon);
    String robot = FileChooser.pickAFile() ; 
    Picture pictureRobot = new Picture(robot);
    pictureRobot.copyExceptWhite(pictureMoon, 0, 0);
    pictureMoon.explore();
  }
}