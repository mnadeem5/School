public class TestMirroring 
{ 
  public static void main(String[] args) 
  { 
    String fileName = FileChooser.pickAFile() ; 
    Picture pictureObj = new Picture(fileName), pictureObj2 = new Picture(fileName); 
    pictureObj.explore(); 
    pictureObj.mirrorVertical();
    pictureObj.explore();
    pictureObj2.mirrorVerticalRightToLeft();
    pictureObj2.explore();
  } 
} 