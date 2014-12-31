import java.awt.Color; 
public class DrawLine 
{ 
    public static void main (String[] args) 
  { 
                                         
   String filename = FileChooser.pickAFile();
   Picture pictureObj = new Picture (filename);
   pictureObj.show();
   int i = 0; 
         while(i<25) 
         { 
            pictureObj.getPixel(100+i,100+i).setColor(Color.black); 
            i++; 
         }
         i=0;
         while(i<25) 
         { 
            pictureObj.getPixel(100,100+i).setColor(Color.MAGENTA); 
            i++; 
         }
         i=0;
         while(i<25) 
         { 
            pictureObj.getPixel(100+i,100).setColor(Color.WHITE); 
            i++; 
         }
         pictureObj.repaint();
   }
}