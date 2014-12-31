public class blueExtremesTry
{ 
  public static void main (String[] args)  
  { 
    String fileName = FileChooser.pickAFile() ; 
    Picture pictureObj = new Picture(fileName);
    pictureObj.blueExtremes();
    pictureObj.explore();
  }
}