public class EnterCorrectInput2 
{ 
  public static void main(String[] args) 
  { 
    int num;     
    boolean cont = true;
    System.out.println("We want a number between 1 and 100 inclusive.");  
    while (cont) 
    { 
      num = SimpleInput.getIntNumber("Enter number: "); 
      if (num>0&&num<101) 
      { 
        cont = false; 
        System.out.println("The number you entered is " + num); 
      } 
    } 
  } 
} 