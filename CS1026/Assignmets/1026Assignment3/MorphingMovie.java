public class MorphingMovie
{
  public static void main (String[] args)
  {
    //initializing the strings and giving them the picture directories as values, also initializes the dir to a string
    String startPic = FileChooser.pickAFile(), endPic = FileChooser.pickAFile(), dir;
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
      //gives a vlue to the dir string
      dir = SimpleInput.getString("Please enter the directory in which you want the moive to be stored in (Note: the directory name must end with a slash, for example Z:/Movie1/)");
      //initializes numStages and gives it a value
      int numStages =0;
      //while loop to make sure that the user asks for more than 2 intermediate phases
      while(numStages<2)
      {
        //gives numStages a vlues 2=<x
        numStages = SimpleInput.getIntNumber("Enter the number of Intermediate phases: ");
      }
      
      //initializes and gives a value to the picture area 
      Picture [] interPicture = new Picture [numStages];
      
      //forloop to give create the pictues and call the mothod morphStage to give them a value
      for (int i =0; i<numStages; i++)
      {
        interPicture [i] = new Picture (startPicture.getWidth(), startPicture.getHeight());
        interPicture [i].morphStage(startPicture, endPicture, numStages, i+1);
      }
      
      //initializes the frameSequencer
      FrameSequencer frameSequencer = new FrameSequencer(dir);
      //forloop to add frames to the frameSequencer
      for (int j = 0; j<interPicture.length;j++)
      {
        frameSequencer.addFrame(interPicture[j]);
      }
      //forloop to add frames backwords to the frameSequencer
      for (int k = 0; k<interPicture.length;k++)
      {
        frameSequencer.addFrame(interPicture[interPicture.length-k-1]);
      }
      //initializes the movie obj and playes the movie
      MoviePlayer movieObj = new MoviePlayer (dir);
      movieObj.playMovie();
    }
  }
}