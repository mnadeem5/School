import java.io.*; 
public class ReadandPrintFromFile 
{  
  public static void main(String[] args) 
  { 
    SimpleReader reader = new SimpleReader("test.txt"); 
    String [] lineArray = reader.readFile(); 
    for (int i = 0; i < reader.getFileLength() ; i++)     
      System.out.println(lineArray[i] +  "\n"); 
  } 
}