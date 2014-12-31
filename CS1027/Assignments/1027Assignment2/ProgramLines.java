import java.util.StringTokenizer;
import static java.lang.Character.*;
/**
 * This class reads a file and stores its lines in an array
 * it also sorts these lines to either comment lines variable lines
 * print lines end line or postfix lines and evaluates them
 * @author Zaid Albirawi (zalbiraw)
 */
public class ProgramLines 
{
	/**
	 * final characters for keyChar
	 */
	private final char COMMENT = '#';
	private final char VARIABLE = 'v';
	private final char PRINT = 'p';
	private final char END = 'e';
	
	/**
	 * final operation characters for postfix lines evaluation
	 */
	private final char ADD = '+';
	private final char SUBTRACT = '-';
	private final char MULTIPLY = '*';
	private final char DIVIDE = '/';
	
	/**
	 * a LinkedStack stack that will help evaluate the postfix expression
	 */
	private LinkedStack<Integer> stack;
	
	/**
	 * an array that will save the document lines one by one
	 */
	private String[] listProgramLines;
	
	/**
	 * default max value for the array
	 */
	private final int MAX_PROGRAMLINES=30;
	
	/**
	 * integer that keeps track of the number of lines read.
	 */
	private int lineCount;
	
	/**
	 * VariableList that saves all the variables
	 */
	private VariableList vList;
	
	/**
	 * Constructor that creates a list for programLines, a list for Variables, and a stack
	 * that will contain the postfix expressions.
	 */
	public ProgramLines ()
	{
		this.lineCount =0;
		this.listProgramLines = new String [this.MAX_PROGRAMLINES];
		this.vList = new VariableList();
		this.stack = new LinkedStack<Integer>();
	}
	
	/**
	 * A method to determine if the character is a keyChar.
	 * @param character that is checked to be a keyChar.
	 * @return a true if the character is equal to one of these characters #, v, p or e.
	 */
	private boolean isKeyChar (char keyChar)
	{
	    return ( keyChar=='#' || keyChar=='v' ||keyChar=='p' || keyChar=='e' );
	}
	
	/**
	 * isOpKeyChar method to determine if the character is an operation keyChar.
	 * @param character that is checked to be an operation keyChar.
	 * @return a true if the character is equal to one of these characters +, -, *, or /.
	 */
	private boolean isOpKeyChar (char keyChar)
	{
		return ( keyChar=='+' || keyChar=='-' ||keyChar=='*' || keyChar=='/' );
	}
	
	/**
	 * var method to check if the line starts with a key Variable.
	 * @param string that contains a line from the document.
	 */
	private void var (String line)
	{
		StringTokenizer tokenizer;
		tokenizer = new StringTokenizer(line);
	    char charT;
	    /**
		 * checks if the line has more token to to make sure it adds all the variables and to avoid a run time exception error 
		 */
		while (tokenizer.hasMoreTokens())
		{
			charT=tokenizer.nextToken().charAt(0);
			/**
			 *checks if the variable is a key character
			 */
			if (charT==COMMENT||charT==VARIABLE||charT==PRINT||charT==END)
        	{
        		System.out.println("Warning: The variable '"+charT+"' can't be used as a variable because it is a key character.");
        	}
			/**
			 *checks if the variable is a digit or a letter
			 */
			else if (!isDigit(charT)&&!isLetter(charT))
			{
				System.out.println("Warning: this character, '"+charT+"', can't be used as a variable.");
			}
			/**
			 *checks if the variable has already been used
			 */
			else if (this.vList.check(charT+"")==true)
			{
				System.out.println("Warning: attempt to redeclare the variable '"+charT+".'");
			}
			/**
			 *adds the variable if it meets the requirements.
			 */
			else this.vList.add(charT+"");
		}
	}
	
	/**
	 * print method that prints the statement required.
	 * @param string that contains a line from the document that starts with p.
	 * @return String that contains a statement.
	 */
	private String print (String line)
	{
		String s="";
		int index=0, count=0;
		/**
		 *runs the program until the the line runs out of characters
		 */
		while (count<line.length())
		{
			/**
			 *checks if the variable is '"'
			 */
			if (line.charAt(count)=='"')
			{
				count++;
			}
			else if (line.charAt(count)=='=')
			{
				s+=line.charAt(count);
				index=this.vList.find(String.valueOf(line.charAt(count+3)));
				/**
				 *checks the value of the variable
				 */
				if (this.vList.listVariables[index].getValue()==-1)
				{
					s+=" ? ";
				}
				else 
				{
					s+= " "+this.vList.listVariables[index].getValue()+" ";
				}	
				count+=4;
			}
			else 
			{
				s+=line.charAt(count);
				count++;
			}
		}
		return s;
	}
	
	/**
	 * end method that terminates the program.
	 */
	private void end()
	{
		System.exit(0);
	}
	
	/**
	 * evaluate method to evaluate the postfix expressions.
	 * @param a string that contains the postfix expression.
	 * @return a value to the postfix expression.
	 */
	private Integer evaluate (String postfix)
	{
		int result=0;
		int index=0;
		char charAtIndex;
		int op1, op2;
		
		while (index<postfix.length())
		{
			charAtIndex=postfix.charAt(index);
			if (charAtIndex!=' ')
			{
				if (isOpKeyChar(charAtIndex))
				{
					/**
					 *makes sure that the postfix expression is correct
					 */
					if (stack.size()<2)
					{
						System.out.println("Warning: The postfix expression "+postfix+" is invalid.");
						return -1;
					}
					else 
					{
						op2=stack.pop();
						op1=stack.pop();
						switch (charAtIndex)
						{
							case ADD:
								result=op1+op2;
							break;
							case SUBTRACT:
								result=op1-op2;
							break;
							case MULTIPLY:
								result=op1*op2;
							break;
							case DIVIDE:
								/**
								 * prevents the program from dividing by zero
								 */
								if (op2==0)
								{
									System.out.println("Warning: Cannot devid by zero, the variable value is set to '?.'");
									result=-1;
								}
								else result=op1/op2;
						}
						this.stack.push(result);
					}
				}
				/**
				 *if the next variable is not an operator then add the next number to the stack
				 */
				else 
				{
					/**
					 * checks if the variable exists in the variable list
					 */
					if (vList.check(charAtIndex+""))
					{
						/**
						 * pushes the variables value into the stack
						 */
						this.stack.push(this.vList.listVariables[this.vList.find(charAtIndex+"")].getValue());
					}
					/**
					 * pushes the number into the stack
					 */
					else this.stack.push(Integer.parseInt(charAtIndex+""));
				}
				index++;
			}
			else index++;
		}
		result=this.stack.pop();
		/**
		 * checks if the result is less than -1 and sets it to zero, less than -1 because -1 is ?
		 */
		if (result<-1)
		{
			System.out.println("Warning: The result is set to 0 because the variable is equal to a negative number.");
			return 0;
		}
		/**
		 * returns the most right number of the result
		 */
		else return result%10;
	}
	
	/**
	 * readList method that will read file and output the result of each line
	 * @param fileName	filename of file that contains the I program code
	 */
	public void readList (String fileName) throws Exception 
	{		  
			
	  InStringFile reader = new InStringFile(fileName);
	  System.out.println("\nReading from file " + fileName + "\n");
	  char keyCharCheck;
	  
	  do
	    {
		  /**
		   * adds the line to the array of lines.
		   */
		  this.listProgramLines[this.lineCount] = (reader.read());
		  keyCharCheck=this.listProgramLines[this.lineCount].charAt(0);
		  
		  /**
		   * checks if the character is a key variable 
		   */
		  if (isKeyChar(keyCharCheck))
		  {
			  switch (keyCharCheck)
		      {
		        case COMMENT:
		        	System.out.println(this.listProgramLines[this.lineCount]);
		        	break;
		        case VARIABLE:
		        	//System.out.println(this.listProgramLines[this.lineCount]);
		        	/**
		        	 * calls the variable method.
		        	 */
		        	var(this.listProgramLines[this.lineCount].substring(2));
		        	break;
		        case PRINT:
		        	//System.out.println(this.listProgramLines[this.lineCount]);
		        	/**
		        	 * prints the result string from the print method.
		        	 */
		        	System.out.println(print(this.listProgramLines[this.lineCount].substring(2)));
		        	break;
		        case END:
		        	//System.out.println(this.listProgramLines[this.lineCount]);
		        	System.out.println("Program has been terminated");
		        	end();
		      }
		  }
		  /**
		   * if the line doesn't start with a key variable then it calls the evaluate method
		   */
		  else 
		  {
			  /**
			   * System.out.println(this.listProgramLines[this.lineCount]);
			   */
			  int index;
			  index=this.vList.find(this.listProgramLines[this.lineCount].substring(0, 1));
			  /**
			   * sets the value of the variable to the evaluate method result
			   */
			  this.vList.listVariables[index].setValue(evaluate(this.listProgramLines[this.lineCount].substring(2)));
		  }
	    }
	  while (!reader.endOfFile()); 	   
	  reader.close();
	}
}
