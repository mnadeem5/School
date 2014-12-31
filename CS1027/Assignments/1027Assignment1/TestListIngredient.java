/**
 * This java program will test the class ListIngredient
 * Zaid Albiawi
 * zalbiraw@uwo.ca
 *
 */
public class TestListIngredient{
  public static void main(String[] args) throws Exception{
	//gives the string variables file paths values
    String database = "D:\\Workspace\\1027 Assignment1\\src\\table.dat";
    String recipe = "D:\\Workspace\\1027 Assignment1\\src\\pasta.txt";
    
    //creates a new ingredient list
    ListIngredient ingredient = new ListIngredient();
    //reads the ingredient list and specifies that this is the main ingredient list
    ingredient.readList(database, "Ingredient");
    //finds how many calories "butter" has and prints it
    System.out.println(ingredient.find("butter"));
    
    //creates a recipe ingredient list
    ListIngredient ingredient2 = new ListIngredient();
    //reads the ingredient list and specifies that this is the recipe ingredient list
    ingredient2.readList(recipe, "Recipe");
    //prints out the total calorie count from all the ingredients inside the recipe list
    System.out.println(ingredient2.count(recipe,ingredient));
  }
}
