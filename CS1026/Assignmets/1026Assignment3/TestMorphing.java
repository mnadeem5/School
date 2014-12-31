public class TestMorphing
{
  public static void main (String[] args)
  {
    //initializing the strings and giving them the picture directories as values
    String startPic = FileChooser.pickAFile(), endPic = FileChooser.pickAFile();
    //initializing the pictures and giving them values
    Picture startPicture = new Picture (startPic), endPicture = new Picture(endPic);
    //checks if the heghts or widths match to keep going with the program or not 
    if (startPicture.getHeight() != endPicture.getHeight() || startPicture.getWidth() != endPicture.getWidth())
    {
      //a msg that shows that either the heights or widths didnt match 
      SimpleOutput.showInformation("ERROR! The pictures' sizes do not match");
    }
    else
    {  
      //shows the startPicture in a popup
      startPicture.repaint();
      //asks for the number of phases in popup box
      int numStages = SimpleInput.getIntNumber("Enter the number of Intermediate phases: ");
      //initializes the array of pictures 
      Picture [] interPicture = new Picture [numStages];
      
      // a for loop to sets values to the pictures in the array call the morphStage methode and shows the edited pictures 
      for (int i =0; i<numStages; i++)
      {
        interPicture [i] = new Picture (startPicture.getWidth(), startPicture.getHeight());
        interPicture [i].morphStage(startPicture, endPicture, numStages, i+1);
        interPicture [i].repaint();
      }
      //shows the endPicture in a popup window
      endPicture.repaint();
    }
  }
}