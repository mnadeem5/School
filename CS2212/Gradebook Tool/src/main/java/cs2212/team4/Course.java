package cs2212.team4;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Stack;
import au.com.bytecode.opencsv.CSVReader;

/**
 *
 * Course is the class that will be used to store all course students, deliverables, grades, and course information.
 *
 * team4-gradebook application
 *
 * @author Zaid Albirawi
 * @version 1.6 3/25/2014
 */

public class Course implements CourseADT, Serializable
{
	/* ************************************************************
	 * Instance Variables
	 ************************************************************ */

	// The Course Class version
	private static final long serialVersionUID = 1L;
	// The Course object title, term, and code.
	private String title = "", term = "", code = "", description="";
	// The Course object student list.
	private ArrayList<Student> studentList = new ArrayList<Student>();
	// The Course object deliverable list.
	private ArrayList<Deliverable> deliverableList = new ArrayList<Deliverable>();
	// The Course object empty deliverable slots
	private Stack<Integer> stkDeliver = new Stack<Integer>();
	//A collection of reports containing various information on a student
	//public static Collection<Report> student_info = new ArrayList<Report>();
    //Variable used to keep track of deliverable weights -- Optional if we want to keep
    //deliverable weights <= 100
    private double weightTotal=0;
    
    private Color color = new Color(20, 150, 250);

	/**
	 * Constructor that creates a course with a given title, term, and code
	 * 
	 * @param title The title of the course
	 * @param term The term of the course
	 * @param code The course code
	 * 
	 */
	public Course(String title, String term, String code) {
		this.title = title;
		this.term  = term;
		this.code  = code;
	}

	/* ************************************************************
	 * Accessor Methods
	 ************************************************************ */

	/**
	 * Gets the course title
	 * 
	 * @return The title of the course
	 * 
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the course term
	 * 
	 * @return The term of the course
	 * 
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * Gets the course code
	 * 
	 * @return The course code
	 * 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the course color
	 * 
	 * @return The course color
	 * 
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * Gets the course description
	 * 
	 * @return The course description
	 * 
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * Gets a specified student of this course
	 * 
	 * @param stud An index number pertaining to a student in a list
	 * @return The student that is indexed at the passed number. If the student index is out of bounds from the list, return null
	 * 
	 */
	public Student getStudent(int stud) {
		if (stud > studentList.size() - 1 || stud < 0)
			return null;
		return studentList.get(stud);
	}

	/**
	 * Gets a specified Deliverable of this course
	 * 
	 * @param deliver An index number pertaining to a deliverable in this course's list of deliverables
	 * @return The Deliverable that is indexed at the passed number. If the deliverable index is out of bounds from the list, return null
	 * 
	 */
	public Deliverable getDeliverable(int deliver) {
		if (deliver < deliverableList.size() && deliver>=0)
			return deliverableList.get(deliver);
		return null;
	}

	/**
	 * Gets a specific student grade
	 * 
	 * @param stud	A student of this course
	 * @param grade An index number pertaining to a grade in this student's list of grades
	 * @return The specidied student's specified grade. If the grade index is out of bounds from the list, return -1
	 * 
	 */
	public double getGrade(Student stud, int grade) {
		if (grade > studentList.size() - 1 || grade < 0)
			return -1;
		return stud.getGrade(grade);
	}

	/**
	 * Gets the average for a course
	 *
	 * @return The average between all the students' grades in the course. If there are no students or no deliverables in the course, return -1. If there are no assigned grades, return 0
	 *
	 */
	public double getClassAvg(){
		if (studentList.size()>0&&deliverableList.size()>0){
			double avg=0, tempAvg;
			int ctr=0;
			for (int i=0; i<studentList.size();i++){
				tempAvg = studentList.get(i).getAvg();
	            if (tempAvg > -1){
				    avg += tempAvg;
	                ctr++;
	            }
			}
			if (ctr>0)
				return avg/ctr;
		}
		return -1;
	}


	/**
	 * Gets the average for a course assignments 
	 *
	 * @return The average between all the students' assignment grades in the course. If there are no students or no deliverables in the course, return -1. If there are no assigned grades, return 0
	 *
	 */
	public double getClassAsnAvg(){
		if (!(studentList.size()>0&&deliverableList.size()>0))
			return -1;
		
		double avg=0, tempAvg;
		int ctr=0;
		for (int i=0; i<studentList.size();i++){
			tempAvg = studentList.get(i).getAsnAvg();
            if (tempAvg > -1){
			    avg += tempAvg;
                ctr++;
            }
		}
		if (ctr>0)
			return avg/ctr;
		return -1;
	}
	
	/**
	 * Gets the average for a course exams 
	 *
	 * @return The average between all the students' exams grades in the course. If there are no students or no deliverables in the course, return -1. If there are no assigned grades, return 0
	 *
	 */
	public double getClassExamAvg() {
		if (!(studentList.size()>0&&deliverableList.size()>0))
			return -1;

		double avg=0, tempAvg;
		int ctr=0;
		for (int i=0; i<studentList.size();i++){
			tempAvg = studentList.get(i).getExmAvg();
            if (tempAvg > -1){
			    avg += tempAvg;
                ctr++;
            }
		}
		if (ctr>0)
			return avg/ctr;
		return -1;
	}
	
	/**
	 * Gets the average for a course deliverable
	 *
	 * @param deliver the index of the deliverable in the course we are looking for
	 *
	 * @return The class average for a specific deliverable. If no students or deliverables, return -1. If fetching a deliverable returns null, return -1.
	 *
	 */
	public double getClassDeliverableAvg(int deliver) {
		if (!(studentList.size() > 0 && deliverableList.size() > 0))
			return -1;
		
		if (deliver < deliverableList.size() && deliver >= 0) {
			if (deliverableList.get(deliver) == null)
				return -1;
			
			double avg = 0;
			int ctr = 0;
			for (int i = 0; i < studentList.size(); i++) {
				if (studentList.get(i).getGrade(deliver) != -1) {
					avg += studentList.get(i).getGrade(deliver);
					ctr++;
				}
			}
			if (ctr > 0)
				return avg / ctr;
		}
		return -1;
	}

	/**
	 * Gets the size of a list of deliverables pertaining to the course
	 * 
	 * @return The size of the list of the deliverables in this course
	 * 
	 */
	public int getDeliverableListSize() {
		return deliverableList.size();
	}

	/**
	 * Gets the size of a list of students pertaining to the course
	 * 
	 * @return The size of the list of the students in this course
	 * 
	 */
	public int getStudentListSize() {
		return studentList.size();
	}

	/**
	 * Gets the list of students pertaining to the course
	 * 
	 * @return The list of students in this course
	 * 
	 */
	public ArrayList<Student> getStudents() {
		return studentList;
	}

	/**
	 * Gets the list of deliverables pertaining to the course
	 * 
	 * @return The list of deliverables in this course
	 * 
	 */
	public ArrayList<Deliverable> getDeliverables() {
		return deliverableList;
	}
	
	/**
	 * Gets the current weight total
	 * 
	 * @return The the current weight total
	 * 
	 */
	public double getTotalWeight() {
		return weightTotal;
	}

	/* ************************************************************
	 * Mutator Methods
	 ************************************************************ */

	/**
	 * Sets the course title
	 * 
	 * @param title The desired title for the course
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the course term
	 * 
	 * @param term The desired term for the course
	 * 
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * Sets the course code
	 * 
	 * @param code The desired course code
	 * 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Sets the course color
	 * 
	 * @param color The desired course color
	 * 
	 */
	public void setColor(Color color){
		this.color=color;
	}
	
	/**
	 * Sets the course description
	 * 
	 * @param description The desired course description
	 * 
	 */
	public void setDescription(String description){
		this.description=description;
	}
        
        /**
	 * Sets the total weight
	 * 
	 * @param weightTotal The desired total weight
	 * 
	 */
	public void setTotalWeight(double weightTotal){
		this.weightTotal=weightTotal;
	}

	/* ************************************************************
	 * Helper Methods
	 ************************************************************ */

	/**
	 * Checks to make sure that the student ID passed doesn't currently belong to another student in the course
	 * 
	 * @param number The student's ID number that we want to check for authenticity
	 * @return false if the ID number is unique, true if a student in the course already has this ID number
	 * 
	 */
	private boolean checkNumber(String number) {
		if (findStudent(number) == -1)
			return false;
		return true;
	}

	/**
	 * Checks to make sure that the email address passed doesn't currently belong to another student in the course
	 * 
	 * @param email The email of the student that we want to check for authenticity
	 * @return false if the email is unique, true if a student in the course already has this email
	 * 
	 */
	private boolean checkEmail(String email) {
		for (int i = 0; i < studentList.size(); i++)
			if (studentList.get(i).getEmail().equals(email))
				return true;
		return false;
	}

	/**
	 * Edit a student's ID number
	 * 
	 * @param stud The student whose ID number we want to change
	 * @param number The new ID number we want to change to
	 * @return empty string if ID number was changed or if requested change was already the current number. Or, if the number already belongs to another student, return string message with this problem
	 * 
	 */
	public String editStudentNumber(Student stud, String number) {
		if (stud.getNumber().equals(number))
			return "";
		else if (checkNumber(number))
			return "The Student Number already exists";
		else
			stud.setNumber(number);
		return "";
	}

	/**
	 * Edit a student's Email
	 * 
	 * @param stud The student whose email we want to change
	 * @param email The new email we want to change to
	 * @return empty string if the email was changed or if requested change was already the current email. Or, if the email already belongs to another student, return string message with this problem
	 * 
	 */
	public String editStudentEmail(Student stud, String email) {
		if (stud.getEmail().equals(email))
			return "";
		else if (checkEmail(email))
			return "The Student Email already exists";
		else
			stud.setEmail(email);
		return "";
	}

	/**
	 * Finds the index number of the requested student within the course's list of students
	 * 
	 * @param number The student's ID number
	 * @return The index at which the requested student sits in the list of students in this course. Or, if the student doesn't exist, return -1
	 * 
	 */
	public int findStudent(String number) {
		for (int i = 0; i < studentList.size(); i++)
			if (studentList.get(i).getNumber().equals(number))
				return i;
		return -1;
	}

	/**
	 * Adds a student to the course
	 * 
	 * @param nameFirst The student's first name
	 * @param nameLast The student's last name
	 * @param number The student's unique ID number
	 * @param email The student's email
	 * @return true if the student was added to the course. Or, if a student in the course already shares the same email or ID number, return false
	 * 
	 */
	public boolean addStudent(String nameFirst, String nameLast, String number,
			String email) {
		if (!(checkNumber(number) || checkEmail(email))) {
			studentList.add(new Student(nameFirst, nameLast, number, email));
			return true;
		}
		return false;
	}

	/**
	 * Remove a student from the course
	 * 
	 * @param i The student's index number in the list of students in this course
	 * @return true if the student was removed. Or, if the index given falls out of bounds of the list of students, return false
	 * 
	 */
	public boolean removeStudent(int i) {
		if (i >= studentList.size())
			return false;
		studentList.remove(i);
		return true;
	}

	/**
	 * Finds the index number of the requested deliverable within the course's list of deliverables
	 * 
	 * @param deliver The deliverable we are searching for
	 * @return The index at which the requested deliverable sits in the list of deliverables in this course. Or, if the deliverable doesn't exist, return -1
	 * 
	 */
	public int findDeliverable(Deliverable deliver) {
		for (int i = 0; i < deliverableList.size(); i++)
			if (deliverableList.get(i)!=null&&deliverableList.get(i).equals(deliver))
				return i;
		return -1;
	}

	/**
	 * Adds a deliverable to the course
	 * 
	 * @param name The name of the dliverable
	 * @param type The type of the deliverable
	 * @param weight The weight of the deliverable
	 * @return empty string if a deliverable was added to the course. If the deliverable already exists, return a message explaining this problem. If course weight exceeded, return a message explaining this problem
	 * 
	 */
	public String addDeliverable(String name, String type, double weight) {
		if (findDeliverable(new Deliverable(name, type, weight, 0)) != -1)
			return "Deliverable already exists";
		if (weightTotal + weight > 100)
			return "You have exceeded the course 100% weight \nceiling, current usable weight "
					+ (100 - weightTotal) + "%";
		if (!stkDeliver.isEmpty())
			deliverableList.set(stkDeliver.peek(), new Deliverable(name, type,
					weight, stkDeliver.pop()));
		else
			deliverableList.add(new Deliverable(name, type, weight,
					deliverableList.size()));
		weightTotal += weight;
		return "";
	}

	/**
	 * Removes a deliverable from the course
	 * 
	 * @param i The index of the deliverable we want to remove in the list of the deliverables for this course
	 * @return true if the deliverable was removed. Or, if the index falls out of bounds from our list of deliverables in this course, return false
	 * 
	 */
	public boolean removeDeliverable(int i) {
		if (i >= deliverableList.size())
			return false;
		//Also Remove this grade item from all the students (to have a correct avg calculation)
		String type = getDeliverable(i).getType();
		for (int j = 0; j<studentList.size(); j++){
			getStudent(j).removeGrade(i,type);
		}
		weightTotal -= getDeliverable(i).getWeight();
        deliverableList.set(i, null);
		stkDeliver.push(i);
		return true;
	}

	/**
	 * Imports students into the course
	 * @param file The path where the file containing the students to be imported is located
	 * @return empty string if the students were imported successfully. If there is no first name, last name, student number, or email, then return a message pertaining to the particular missing value. If reader fails, return message saying the file is corrupted.
	 * @throws IOException If an output exception occurred
	 * @throws FileNotFoundException If a FileNotFound exception occurred
	 * 
	 */
	public String importStudents(File file)
	{
		String[] sAry;
		ArrayList<String> tempAry = new ArrayList<String>();
		int [] vars = new int[4];
		int ctr = 0;
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			sAry=reader.readNext();
			for (int i=0; i<sAry.length; i++)
				tempAry.add(sAry[i]);
			
			if (tempAry.contains("First Name"))
				vars[0]=tempAry.indexOf("First Name");
			else{
				reader.close();
				return "Error: First Name column was not found";
			}
				
			if (tempAry.contains("Last Name"))
				vars[1]=tempAry.indexOf("Last Name");
			else{
				reader.close();
				return "Error: Last Name column was not found";
			}

			if (tempAry.contains("Student Number"))
				vars[2]=tempAry.indexOf("Student Number");
			else{
				reader.close();
				return "Error: Student Number column was not found";
			}
			
			if (tempAry.contains("Email"))
				vars[3]=tempAry.indexOf("Email");
			else{
				reader.close();
				return "Error: Email column was not found";
			}
			
			while ((sAry = reader.readNext()) != null) {
				if (sAry.length != 4) {
					reader.close();
					if (ctr>0)
						return "Error: the file is corrupted, some students have been added";
					return "Error: the file is corrupted.";
				}
				addStudent(sAry[vars[0]], sAry[vars[1]], sAry[vars[2]], sAry[vars[3]]);ctr++;
			}
			reader.close();
			return "";
		} catch (FileNotFoundException e) {
			return "Error: File not found";
		} catch (IOException e) {
			return "Error: the program failed to import the file";
		}
	}

	/**
	 * Exports the Students of a course into a .csv file
	 * 
	 * @param file The path where we want to export the students
	 * @return empty string if the students were exported
	 * @throws IOException If an output exception occurred
	 * 
	 */
	public String exportStudents(File file) {
		try {
			if (file.exists())
				file.delete();
			Writer bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file)));
			bw.write("\"First Name\", \"Last Name\", \"Student Number\", \"Email\"\n");
			for (int i = 0; i < studentList.size(); i++)
				bw.write(studentList.get(i).toString());
			bw.close();
			return "";
		} catch (IOException e) {
			return "Error: program failed to export the file";
		}
	}

	/**
	 * Imports deliverables into the course
	 * 
	 * @param file The path where the file containing the deliverables to be imported is located
	 * @return empty string if the deliverables were imported successfully. If there is no name, type, or weight, then return a message pertaining to the particular missing value. If reader fails, return message saying the file is corrupted.
	 * @throws IOException If an input exception occurred
	 * @throws FileNotFoundException If a FileNotFound exception occurred
	 * @throws NumberFormatException If a NumberFormat exception occurred
	 * 
	 */
	public String importDeliverables(File file) {
		String[] dAry;
		ArrayList<String> tempAry = new ArrayList<String>();
		int [] vars = new int[3];
		int ctr = 0;
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			dAry=reader.readNext();
			for (int i=0; i<dAry.length; i++)
				tempAry.add(dAry[i]);
			
			if (tempAry.contains("Name"))
				vars[0]=tempAry.indexOf("Name");
			else{
				reader.close();
				return "Error: Name column was not found";
			}
				
			if (tempAry.contains("Type"))
				vars[1]=tempAry.indexOf("Type");
			else{
				reader.close();
				return "Error: Type column was not found";
			}
				
			if (tempAry.contains("Weight"))
				vars[2]=tempAry.indexOf("Weight");
			else{
				reader.close();
				return "Error: Weight column was not found";
			}
			
			while ((dAry = reader.readNext()) != null) {
				if (dAry.length != 3) {
					reader.close();
					if (ctr>0)
						return "Error: the file is corrupted, some deliverables have been added";
					return "Error: the file is corrupted.";
				}
				addDeliverable(dAry[vars[0]], dAry[vars[1]], Double.parseDouble(dAry[vars[2]]));ctr++;
			}			
			reader.close();
			return "";
		}catch (NumberFormatException e) {
			return "Error: Some grade values are not numbers";
		} catch (FileNotFoundException e) {
			return "Error: File not found";
		} catch (IOException e) {
			return "Error: the program failed to import the file";
		}
	}

	/**
	 * Exports the deliverables of a course into a .csv file
	 * 
	 * @param file The path where we want to export the deliverables
	 * @return empty string if the deliverables were exported.
	 * @throws IOException If an output exception occurred
	 * 
	 */
	public String exportDeliverables(File file) {
		try {
			Writer bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file)));
			bw.write("\"Name\", \"Type\", \"Weight\"\n");
			for (int i = 0; i < deliverableList.size(); i++)
				if (deliverableList.get(i)!=null)
					bw.write(deliverableList.get(i).toString());
			bw.close();
			return "";
		} catch (IOException e) {
			return "Error: program failed to export the file";
		}
	}

	/**
	 * Imports students' grades into the course
	 * 
	 * @param file The path where the file containing the students' grades to be imported is located
	 * @return empty string if the grades were imported successfully. If there is no student number, then return a message pertaining to the missing value. If reader fails, return message saying the file is corrupted.
	 * @throws IOException If an input exception occurred
	 * @throws FileNotFoundException If a FileNotFound exception occurred
	 * @throws NumberFormatException If a NumberFormat exception occurred
	 * 
	 */
	public String importGrades(File file) {
		String[] gAry;
		ArrayList<String> tempAry = new ArrayList<String>();
		ArrayList<Integer> vars = new ArrayList<Integer>();
		ArrayList<Integer> fileLoc = new ArrayList<Integer>();
		int studNum, ctr = 0,init;
		Student student;
		Deliverable deliver;
		String dne="";
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			gAry=reader.readNext();
			for (int i=0; i<gAry.length; i++)
				tempAry.add(gAry[i]);
			
			init=gAry.length;
			
			if (tempAry.contains("Student Number"))
				studNum=tempAry.indexOf("Student Number");
			else{
				reader.close();
				return "Error: Student Number column was not found";
			}
				
			for (int i=0; i<gAry.length; i++){
				for (int j=0; j<deliverableList.size(); j++){
					if ((deliver=deliverableList.get(j))!=null){
						if (deliver.getName().equals(gAry[i])){
							vars.add(j);
							fileLoc.add(i);
							break;
						}
					}
				}
			}
			
			while ((gAry = reader.readNext()) != null) {
				if (gAry.length != init) {
					reader.close();
					if (ctr>0)
						return "Error: the file is corrupted, some grades have been added";
					return "Error: the file is corrupted";
				}
				int find=findStudent(gAry[studNum]);
				if (find != -1) {
					student = studentList.get(find);
					for (int i = 0; i < vars.size(); i++) {
						deliver = deliverableList.get(vars.get(i));
						student.addGrade(vars.get(i),
								Double.parseDouble(gAry[fileLoc.get(i)]),
								deliver.getType(), deliver.getWeight());
					}
					ctr++;
				} else 
					dne+=gAry[studNum]+"\n";
			}
			reader.close();
			if (dne.equals(""))
				return dne;
			return "0"+dne;
		}catch (NumberFormatException e) {
			return "Error: Some grade values are not numbers";
		} catch (FileNotFoundException e) {
			return "Error: File not found";
		} catch (IOException e) {
			return "Error: the program failed to import the file";
		}
	}

	/**
	 * Exports students' grades into a .csv file
	 * 
	 * @param file The path where we want to export the grades
	 * @return empty string if the grades were exported.
	 * @throws IOException If an input exception occurred
	 * 
	 */
	public String exportGrades(File file) {
		ArrayList<Integer> dilvers = new ArrayList<Integer>();
		try {
			Writer bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file)));
			String str = "\"First Name\", \"Last Name\", \"Student Number\", \"Email\"";
			for (int i = 0; i < deliverableList.size(); i++)
				if (deliverableList.get(i) != null) {
					str = str + ", \"" + deliverableList.get(i).getName()
							+ "\"";
					dilvers.add(i);
				}
			bw.write(str + "\n");
			for (int i = 0; i < studentList.size(); i++)
				bw.write(studentList.get(i).toStringGrade(dilvers.toArray()));
			bw.close();
			return "";
		} catch (IOException e) {
			return "Error: program failed to export the file";
		}
	}

	/**
	 * A method that test for course equality
	 * 
	 * @param crs The course we are comparing to	
	 * @return true if the course is equal to this course, false otherwise
	 * 
	 */
	public boolean equals(Course crs) {
		if (this.toString().equalsIgnoreCase(crs.toString()))
			return true;
		return false;
	}

	/**
	 * A toString method
	 * 
	 * @return The course information. Including title, term, and code
	 * 
	 */
	public String toString() {
		return ("\"" + title + "\", \"" + term + "\", \"" + code + "\"\n");
	}
}
