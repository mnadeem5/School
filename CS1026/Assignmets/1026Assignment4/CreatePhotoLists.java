/**
 * CreatePhotoLists.java
 *
 *
 * Zaid Albirawi 
 * 2012/12/6
 */
public class CreatePhotoLists 
{  
  public static void main (String[] args) 
  {
    //gets the text file name from the user
    String fileName = SimpleInput.getString("Please enter the text file name followed with .txt: ");
    //creats a PhotoList object
    PhotoList createPhotoLists  = new PhotoList (fileName);
    //gets the directory from the user
    String dir = SimpleInput.getString("Please enter the new Photos' directory: ");
    //lists the photos
    createPhotoLists.listPhotos();
    //shows the photos
    createPhotoLists.showPhotos();
    //stores the photos in the directory the user entered
    createPhotoLists.storePhotos(dir);
    //sortes the photos
    createPhotoLists.sortPhotosByDate();
  }
}
