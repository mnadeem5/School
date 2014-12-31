import java.io.*;
import java.util.StringTokenizer;
public class ReadPrintandOrganizeFromFile 
{  
  public static void main(String[] args) 
  { 
    SimpleReader reader = new SimpleReader("test.txt"); 
    String [] lineArray = reader.readFile();
    StringTokenizer tokenizer;
    String firstName, lastName, userName;
    for (int i = 0; i < reader.getFileLength() ; i++)
    {
      tokenizer= new StringTokenizer(lineArray[i]); 
      firstName = tokenizer.nextToken();
      lastName = tokenizer.nextToken();
      userName = tokenizer.nextToken();
      System.out.println(lastName+", "+firstName+". And his/her username is "+userName);
    }
  } 
}