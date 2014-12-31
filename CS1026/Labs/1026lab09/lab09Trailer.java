public class lab09Trailer
{
  public static void main (String[] args)
  {
    String fileName = FileChooser.pickAFile();
    Picture picObj = new Picture(fileName);
    
    //System.out.println("The number of White pixels are: " +picObj.countWhitePixels());
    //System.out.println("The number of non White pixels are: " +picObj.countNonWhitePixels());
    //System.out.println("The number of non White2 pixels are: " +picObj.countNonWhitePixels());
    
    //String fileName1 = FileChooser.pickAFile();
    //Picture picObj1 = new Picture(fileName1);
    //String match="Do not match";
    //if (picObj.equalPictureSize(picObj1)) match="Match";
    //System.out.println("The sizes of the pictues "+match);
    
    //Picture picObjInvoke = new Picture (picObj.getWidth(),picObj.getHeight());
    //picObjInvoke.copyLeftHalf(picObj);
    //picObjInvoke.repaint();
    
    Picture picObjInvoked = new Picture (picObj.copyLeftHalf());
    picObjInvoked.repaint();
  }
}