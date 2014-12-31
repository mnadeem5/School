/**
 * This java class is a class that will create an ingredient
 * that will have a name and a calorie value
 * Zaid Albiawi
 * zalbiraw@uwo.ca
 *
 */
public class Ingredient 
{
	//instant variable declaration
	private String name; 
	private Double calorieCount;
	
	//Ingredient class that takes two parameters
	public Ingredient(String name, Double calorieCount)
	{
		this.name=name;
		this.calorieCount=calorieCount;
	}
	
	//Name getter method
	public String getName()
	{
		return this.name;
	}
	
	//Calorie count getter method
	public Double getCalorieCount()
	{
		return this.calorieCount;
	}
}
