import java.io.*;
public class Compress 
{
	public static void main (String args []) throws DictionaryException 
	{
		String file="D:/workspace/2210Assignment2/src/Assignment 3PDF";
		int i=0;
		
		try
		{
			if (file=="")
			{
				System.out.println("File to compress");
				BufferedReader keyboard =  new BufferedReader(new InputStreamReader(System.in));
				file = keyboard.readLine();
			}
			BufferedOutputStream out = new BufferedOutputStream (new FileOutputStream (file+".zzz"));
			BufferedInputStream in = new BufferedInputStream (new FileInputStream (file+".pdf"));
			MyOutput myOut = new MyOutput();
			/**
			  * Stores the input file into an array of integers. Each entry is equivalent to a byte. 
			  */
			int byteAry [] = new int [in.available()];i=0;
			while (in.available()!=0)
			{
				byteAry[i]=in.read();i++;
			}
			in.close();
			System.out.println("Compressing...");
			
			/**
			  * Creates a new Dictionary of size 4093 (a prime.)
			  */
			Dictionary dic=new Dictionary (4093);
			
			/**
			  * try and catch for insert
			  */
			try 
			{
				/**
				  * Inserts all 256 ASCII characters into the Dictionary.
				  */
				for (i=0; i<256; i++)
					dic.insert(new DictEntry((char)i+"", i));
			}
			catch (DictionaryException e)
			{
				System.out.print("This key, \""+(char)i+""+"\",has already been inserted into the Dictionary.");
			}
			
			/**
			  * An algorithm that checks if a String is in the Dictionary, if its then it keeps appending string together until
			  * it reaches a string that is not in the dictionary. When that point is reached, the algorithm will output the 
			  * predecessor of that string to the file "out" and add the string to the Dictionary.
			  */
			i=0;
			String s="";
			String [] str=new String [2];
			//int count=0;
			while (i<byteAry.length)
			{
				str=append(dic, byteAry, i, s, str);
				myOut.output(dic.find(str[0]).getCode(), out);
				//System.out.println(dic.find(str[0]).getCode());
				
				if (dic.numElements()<4096)
				{
					if (!str[1].equals("Don't insert"))
					{
						//count+=
						/**
						  * try and catch for insert
						  */
						try 
						{
							dic.insert(new DictEntry(str[1], dic.numElements()));
						}
						catch (DictionaryException e)
						{
							System.out.print("This key, \""+(char)i+""+"\",has already been inserted into the Dictionary.");
						}
					}	
				}
				i=i+str[0].length();
			}
			//System.out.println("Number of collision "+count);
			myOut.flush(out);
			out.close();
			System.out.println("The compression operation is done.");
		}
		catch (IOException e)
		{
			System.out.println("Cannot open input or output files: "+file);
		}
	}
	
	/**
	  * The method append is a helper method that helps execute the compression algorithm by appending strings together.
	  * 
	  * @param		d			a Dictionary that contains a list of all the DictEntries.
	  * @param		byteAry		an integer array that contains the bytes of the input file.
	  * @param		pos			an integer that keeps track of the position of the pointer.
	  * @param		s			a String that contains the appended strings.
	  * 
	  * @return		str			a String array that contains both the strings' predecessor and the string.
	  */
	
	public static String [] append (Dictionary d, int [] byteAry, int i, String s, String [] str)
	{	
		/**
		  * Checks if the string is in the Dictionary. If it isn't then it returns the str.
		  */
		if ((d.find(s+(char)byteAry[i])==null))
		{
			str[1]=s+(char)byteAry[i];
			return str;
		}
		/**
		  * Checks if the string is in the Dictionary. If it is then it recursively calls itself and appends the next character
		  * to s.
		  */
		else
		{
			s+=(char)byteAry[i];
			str[0]=s;
			/**
			  * Prevents the program from running out of bounds by stoping it from appending the next character to s if the 
			  * pos + 1 is larger than the file length
			  */
			if (i+1<byteAry.length)
				return (append (d, byteAry,i+1, s, str));
			else 
			{
				str [1]="Don't insert";
				return str;
			}
		}
	}
}
