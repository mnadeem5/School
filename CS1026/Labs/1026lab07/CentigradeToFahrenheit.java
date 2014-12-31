public class CentigradeToFahrenheit 
{ 
  public static void main (String[] args)  
  { 
    double temperature = SimpleInput.getNumber("Enter Double Number: ");
    double fdegrees = (temperature/5.0)* 9.0 + 32; 
    SimpleOutput.showInformation(temperature + " degrees C is "+fdegrees + " degrees F");
  } 
} 