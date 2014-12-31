import java.util.*;

public class bigInt {

	ArrayList<Integer> bigNum = new ArrayList <Integer>();

	//Adds the numbers to the list.
	public bigInt(String num)
	{
		for(int i=0; i<num.length(); i++)
			bigNum.add(Integer.parseInt(num.charAt(i)+""));
	}

	//Creates a bigInt from an existing list!
	private bigInt(ArrayList<Integer> lst){bigNum=lst;}

	//Addition method.
	public bigInt add (bigInt num)
	{
		//creates a temporary list to store the result
		ArrayList<Integer> lst = new ArrayList <Integer>();
		int i=0,carry=0, a, b, tmp;

		//adds the two bigInts
		for ( ; i<bigNum.size()&&i<num.bigNum.size(); i++)
		{
			a=bigNum.get(bigNum.size()-i-1);
			b=num.bigNum.get(num.bigNum.size()-i-1);
			if ((tmp=a+b+carry)>9)
			{
				lst.add(0, (tmp%10));carry=1;
			}
			else
			{
				lst.add(0, tmp);carry=0;
			}
		}
		//flushes the rest of the integers in the first bigInt
		for ( ; i<bigNum.size(); i++)
		{
			a=bigNum.get(bigNum.size()-i-1);
			if((tmp=a+carry)>9)
			{
				lst.add(0, (tmp%10));carry=1;
			}
			else
			{
				lst.add(0, tmp);carry=0;
			}
		}

		//flushes the rest of the integers in the second bigInt
		for ( ; i<num.bigNum.size(); i++)
		{
			b=num.bigNum.get(num.bigNum.size()-i-1);
			if((tmp=b+carry)>9)
			{
				lst.add(0, (tmp%10));carry=1;
			}
			else
			{
				lst.add(0, tmp);carry=0;
			}
		}

		//flushes the carry
		if (carry==1)lst.add(0, 1);

		//returns a bigInt
		return (new bigInt(lst));
	}

	//Subtraction method.
	public bigInt sub (bigInt num) throws Exception
	{
		//creates a temporary list to store the result
		ArrayList<Integer> lst = new ArrayList <Integer>();
		int i=0,carry=0, a, b, tmp;

		//Subtracts the second bigInt from the first one.
		for ( ; i<bigNum.size()&&i<num.bigNum.size(); i++)
		{
			a=bigNum.get(bigNum.size()-i-1);
			b=num.bigNum.get(num.bigNum.size()-i-1);

			if ((tmp=a-b-carry)<0)
			{
				lst.add(0, (tmp+10));carry=1;
			}
			else
			{
				lst.add(0, tmp);carry=0;
			}
		}

		//flushes the rest of the first bigInt
		for ( ; i<bigNum.size(); i++)
		{
			a=bigNum.get(bigNum.size()-i-1);
			if((tmp=a-carry)<0)
			{
				lst.add(0, (tmp+10));carry=1;
			}
			else
			{
				lst.add(0, tmp);carry=0;
			}
		}

		//throws an exception if the second bigInt is bigger than the first.
		if (i<num.bigNum.size()||carry==1)throw new Exception("Negative!");
		return (new bigInt(lst));
	}

	//Multiply method.
	public bigInt multi (bigInt num)
	{
		int a, b;
		//creates temporary bigInt objects to use in the calculation
		bigInt multiply;bigInt tmp=new bigInt("");bigInt result=new bigInt("");

		//Iterates through the list of numbers in the second bigInt
		for (int i=0; i<num.bigNum.size(); i++)
		{
			b=num.bigNum.get(num.bigNum.size()-i-1);

			//Iterates thought the numbers of the first bigInt
			tmp=new bigInt("");
			for (int j=0 ; j<bigNum.size(); j++)
			{
				a=bigNum.get(bigNum.size()-j-1);

				//multiplies the two numbers from the first and second bigInt objects
				multiply=new bigInt((a*b)+"");

				//pads the numbers for an appropriate long addition method
				for (int k=0; k<j; k++)multiply.bigNum.add(0);

				//Performs long addition
				tmp=tmp.add(multiply);
			}
			//pads the numbers for an appropriate long addition method
			for (int k=0; k<i; k++)tmp.bigNum.add(0);

			//Performs long addition
			result=result.add(tmp);
		}

		//return the result.
		return result;
	}

	//A square method.
	public bigInt square()
	{
		bigInt a=new bigInt(bigNum);
		bigInt b=new bigInt(bigNum);
		return a.multi(b);
	}

	//toString method.
	public String toString()
	{
		String str="";
		//prints the number in the right order.
		for(int i=bigNum.size()-1; i>-1; i--)str=(bigNum.get(i))+str;
		//removes all the extra zeros left on the left of the number during the calculations.
		while(str.charAt(0)=='0'&&str.length()>1)str=str.substring(1, str.length());
		return str;
	}
}
