package ca.uwo.csd.cs2212.zalbiraw;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class App implements Serializable
{
	private static void storePerson(String[] args) throws Exception
	{
		Person person = new Person(args[2], new Address(args[3], args[4], args[5]));
		for (int i=6; i<args.length; i++)person.addPhoneNumber(args[i]);
	
		ObjectOutputStream OUS = new ObjectOutputStream(new FileOutputStream(args[1]));
		OUS.writeObject(person);OUS.close();
	}

	private static void loadPerson(String[] args) throws Exception
	{
		Person person = new Person();
		ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(args[1]));
		person=(Person)OIS.readObject();
		System.out.println(person.toString());
	}

	private static void showUsageAndExit() 
	{
		System.err.println("Usage:");
		System.err.println("\tstore filename name street city country phone1 phone2 phone3 ...");
		System.err.println("\tload filename");
		System.exit(-1);
	}

	/**
	 * We expect that the args array will contain either:
	 * 
	 * store filename name street city country phone1 phone2 phone3 ... load
	 * filename
	 * 
	 * You can assume that the arguments will be correct (i.e. you don't have to
	 * perform validation).
	 * 
	 * Note that for the 'store' method, you will be passed at least one phone
	 * number, but may be passed multiple phone numbers.
	 */
	public static void main(String[] args) throws Exception 
	{
		if (args.length == 0)showUsageAndExit();

		switch (args[0]) 
		{
		case "store":
			if ((args.length < 7) || (new File(args[1]).exists()))showUsageAndExit();
			storePerson(args);
			break;
		case "load":
			if ((args.length != 2) || (!new File(args[1]).exists())|| (new File(args[1]).isDirectory())) showUsageAndExit();
			loadPerson(args);
			break;
		default:
			showUsageAndExit();
		}
	}
}