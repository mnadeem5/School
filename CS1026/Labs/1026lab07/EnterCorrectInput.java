public class EnterCorrectInput 
{ 
  public static void main(String[] args) 
  { 
     System.out.println("We want a number between 1 and 100 inclusive."); 
     int num=0; 
     while (num<1 || num>100)
     {
       num = SimpleInput.getIntNumber("Enter number: "); 
     }
     System.out.println("The number you entered is " + num); 
  } 
}