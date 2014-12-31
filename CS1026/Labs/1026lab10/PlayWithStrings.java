public class PlayWithStrings 
{ 
     public static void main (String[] args) 
    { 
       String test = "This is a test.";
       for (int i =0; i<test.length(); i++) System.out.print(test.charAt(i));
       System.out.println("");
       for (int j =0; j<test.length(); j++) System.out.print(test.charAt(test.length()-j-1));
       
     }
}
  
   