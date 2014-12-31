/**
 * This class creates Ingredient lists using the Ingredient class and
 * will allow the user to read lists from text files, find specific 
 * calorie count for an item in those files and is also able to give the
 * final count of the total number of calories in a recipe.
 * 
 * Zaid Albiawi
 * zalbiraw@uwo.ca
 *
 */
//import java.util so that program is able to import information
import java.util.*;
public class ListIngredient 
{
	//creates instant variables 
	private final int INGREDIENT_MAX=30;
	private Ingredient [] listIngredient;
	private Ingredient [] listRecipe;
	private int numIngredient, numRecipe;
	
	public ListIngredient()
	{
		//gives values to the ingredient lists; the recipe and the main ingredient list
		this.listIngredient=new Ingredient[INGREDIENT_MAX];
		this.listRecipe=new Ingredient[INGREDIENT_MAX];
		this.numIngredient=0;
		this.numRecipe=0;
	}
	
	public void add(String name, Double calorieCount, String choice)
	{
		//creates new ingredients using the information obtained from the readList
		Ingredient ingredient = new Ingredient(name, calorieCount);
		
		//checks if its the ingredient recipe or the main ingredient list
		if (choice.equals("Ingredient"))
		{
			//makes sure that the array does not out of bounds
			if (numIngredient<INGREDIENT_MAX)
			{
				//adds the ingredient to the ingredient list
				this.listIngredient[this.numIngredient]=ingredient;
				this.numIngredient++;
			}
			//returns an error massage if the number of ingredients exceeds the limit
			else System.out.println("The max number of Ingredients has been exceeded");
		}
		
		//checks if its the ingredient recipe or the main ingredient list
		else if (choice.equals("Recipe"))
		{
			//makes sure that the array does not out of bounds
			if (numRecipe<INGREDIENT_MAX)
			{
				//adds the ingredient to the ingredient recipe list
				this.listRecipe[this.numRecipe]=ingredient;
				this.numRecipe++;
			}
			//returns an error massage if the number of ingredients exceeds the limit
			else System.out.println("The max number of Ingredients has been exceeded");
		}
	}
	
	public Double find (String ingredient){
		//Declares he variable count and gives it a value of 0
		int count=0;
		//makes sure that count does not exceed the number of ingredients and prevents a null pointer error
		while (count<this.numIngredient)
		{
			//searches the list array for the ingredients name and returns its index number in the array
			if (this.listIngredient[count].getName().equals(ingredient))
			{
				return this.listIngredient[count].getCalorieCount();
			}
			//keeps counting while the name is not found
			else
			{
				count++;
			}
		}
		//throws an error if the name was not found
		throw new Error("Ingredient not found");
	}
	
	public Double count(String fileName, ListIngredient list) throws Exception
	{
		//defines the variable
		Double count =0.0;
		//uses the readList of recipe ingredients
		readList(fileName, "Recipe");
		for (int i=0; i<7; i++)
		{
			//adds all the ingredients' calorie counts * the number of grams 
			count+=list.find(this.listRecipe[i].getName())*this.listRecipe[i].getCalorieCount();
		}
		//returns the value of count 100 calories per gram
		return count/100;
	}
	
	/**
	 * readList method adds into the university courses list from a file
	 * @param fileName	filename of file that contains course information
	 * @return 
	 */
	public void readList (String fileName, String choice) throws Exception {		  
			
	  // create object that controls file reading and opens the file
	  InStringFile reader = new InStringFile(fileName);
	  System.out.println("\nReading from file " + fileName + "\n");

	  // read data from file one line at a time
			  
	  String line;
	  StringTokenizer tokenizer;
	  String ingredientName;
	  double calorieCount;

	  do
	    {
	      line = (reader.read());
	      
	      // get ingredient and quantity information
	      // Note: it is assumed that each line of the disk file has
	            
	      	tokenizer = new StringTokenizer(line);
	      	ingredientName = tokenizer.nextToken();
	      	calorieCount = Double.parseDouble(tokenizer.nextToken());
			
	      	//checks if the user is adding an ingredient or a recipe ingredient
	    	if (choice.equals("Ingredient"))
			{
	    		//uses the add method to add ingredients to the list
	    		add(ingredientName, calorieCount, "Ingredient");
			}
			else if (choice.equals("Recipe"))
			{
				//uses the add method to add ingredients to the list
				add(ingredientName, calorieCount,"Recipe");
			}

	    }
	  while (!reader.endOfFile()); 
			   
	  reader.close(); 
	}
}
