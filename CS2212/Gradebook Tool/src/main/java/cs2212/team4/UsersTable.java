package cs2212.team4;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * UsersTable class is a type of TableModel that will contain the names of students in a given Course Object.
 * This class allows for the viewing and editing of Student Information from students in a course.
 * It also contains a reference to a GradesTable object, which contains the Grades of the students in another
 * table format.
 *
 * Jenna Le
 * */

 public class UsersTable extends DefaultTableModel{
    //Data Serialization
	private static final long serialVersionUID = 1L;
    //Refers to GradesTable object that contains the Students' grades
	private GradesTable grades;
    //Keeps track of students inside this UsersTable
    private final List<Student> studentNames = new ArrayList<>();
    //Current/Active course that this table represents
    private Course currCourse;
    //Column Names
    private final String[] COLUMN_NAMES = {"First Name", "Last Name", "Email", "Student #"};

    /**
     * Constructor, takes no parameters to create a default empty UserTable
     */

    public UsersTable() {
        for (int i = 0; i < COLUMN_NAMES.length; i++){
            addColumn(COLUMN_NAMES[i]);
        }
        grades = new GradesTable();
    }

    /**
     * Overloaded constructor, allows for the deactivation of some columns in a UsersTable. User
     * will input '0' or '1', whether they want to hide or show the column, respectively.
     *
     * @param first     0 - hide, 1 - show first name column
     * @param last      0 - hide, 1 - show last name column
     * @param email     0 - hide, 1 - show email column
     * @param num       0 - hide, 1 - show stud. no column
     */

    public UsersTable(Course currCourse, int first,int last, int email, int num, int asnAvg, int exmAvg) {
        this.currCourse = currCourse;
        int numStud=currCourse.getStudentListSize();
        int[] columns = {first,last,email,num};

        for (int i = 0; i < COLUMN_NAMES.length; i++){
            if (columns[i]==1)
                addColumn(COLUMN_NAMES[i]);
        }
        grades = new GradesTable(currCourse,asnAvg,exmAvg);

        for (int i = 0; i < numStud; i++) {
            Student stud = currCourse.getStudent(i);
            studentNames.add(stud);
            addStudent(stud, first, last, email, num);
            grades.addGrades(stud);
        }
        fillScreen();
    }

    /**
     * Creates a new GradesTable based on the AsnAvg and ExmAvg parameters.
     *
     * @param asn   1 or 0, to show or hide asnAvg column
     * @param exm   1 or 0, to show or hide exmAvg column
     *
     */
    public void updateGradesTable(int asn, int exm){
        grades = new GradesTable(currCourse,asn,exm);
        fireTableDataChanged();
    }


    /**
     * Returns the GradesTable
     * @return grades The GradesTable object for this UsersTable
     */
    public GradesTable getGradesTable(){
        return grades;
    }


    /**
     * Adds a new student ROW to the usersTable
     * Assumes the student already exists in the course.
     *
     * @param c Course
     * @param studNum Student's ID number
     */

    public void addStudent (Course c, String studNum){
        Student s = c.getStudent(c.findStudent(studNum));
        addRow(new Object [] {s.getNameFirst(),s.getNameLast(),s.getEmail(),s.getNumber()});
        fireTableDataChanged();
    }


    /**
     * Adds a new student ROW of information to the UsersTable, overloaded method.
     * Takes parameters that determine whether certain Student Info columns are appearing.
     *
     * @param s             Student object whose information is to be added
     * @param firstName     0 - hide, 1 - show first name column
     * @param lastName      0 - hide, 1 - show last name column
     * @param number        0 - hide, 1 - show student no. column
     * @param email         0 - hide, 1 - show email column
     */

    public void addStudent(Student s, int firstName, int lastName, int number, int email){
    	Object[] array = new Object [4];
        int ctr=0;
        if (firstName==1)
            array[ctr++]=s.getNameFirst();
        if (lastName==1)
            array[ctr++]=s.getNameLast();
        if (number==1)
            array[ctr++]=s.getEmail();
        if (email==1)
            array[ctr++]=s.getNumber();
        addRow(array);
    }

    /**
    * Returns the list of students objects currently in the table.
    * @return List of students currently in UsersTable
    */

    public List<Student> getStudentNames() {
        return studentNames;
    }

    /**
     * Removes student from table.
     * @param rowIndex row of table where student is to be removed.
     */
    public void removeStudent(int rowIndex){
        removeRow(rowIndex);
        fireTableDataChanged();
    }

    /**
     * Overrides the superclass' setValueat method, to edit the student object's information in the Course
     * @param aValue        String to change the attribute of the student
     * @param rowIndex      Row where attribute is selected
     * @param columnIndex   Column where attribute is selected
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    	String clmn = getColumnName(columnIndex);
        if ((rowIndex < 0) || (rowIndex >= studentNames.size()))
            return;
        else {
            //Checks whether changed value is an empty string -- does not allow the user to change any attribute to blank.
            if(aValue.equals(""))
                return;
            switch (clmn) {
                case "First Name":
                    currCourse.getStudent(currCourse.findStudent(studentNames.get(rowIndex).getNumber())).setNameFirst((String) aValue);
                    fireTableCellUpdated(rowIndex,columnIndex);
                    return;
                case "Last Name":
                    currCourse.getStudent(currCourse.findStudent(studentNames.get(rowIndex).getNumber())).setNameLast((String) aValue);
                    fireTableCellUpdated(rowIndex,columnIndex);
                    return;
                case "Email":
                    currCourse.editStudentEmail(currCourse.getStudent(currCourse.findStudent(studentNames.get(rowIndex).getNumber())), (String) aValue);
                    fireTableCellUpdated(rowIndex,columnIndex);
                    return;
                case "Student #":
                    //Checks whether the student number inputted are only numbers, and does not belong to an already existing student.
                    if((((String)aValue).matches("^[0-9]+$"))){
                        currCourse.editStudentNumber(currCourse.getStudent(currCourse.findStudent(studentNames.get(rowIndex).getNumber())),(String)aValue);
                        fireTableCellUpdated(rowIndex,columnIndex);
                        return;
                    }
                    else return;
            }
        }
    }


    /**
     * Disables editing for cells that do not contain student information (empty rows)
     *
     * @param rowIndex
     * @param columnIndex
     * @return True if selected cell can be edited, false otherwise.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (rowIndex > studentNames.size()-1)
            return false;
        else
            return true;
    }

    /**
     * For visual purposes - fills the table with blank columns to get rid of empty space onscreen.
     */
    public void fillScreen(){
        //Fill with columns/rows if there is empty space in the screen
        int width = this.getColumnCount() + grades.getColumnCount();
        int height = this.getRowCount();

        if (width<11){
            for (int i=0; i < 11-width; i++){
                grades.addColumn("");
            }
        }
    }
}