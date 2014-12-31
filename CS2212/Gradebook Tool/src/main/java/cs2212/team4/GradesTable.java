package cs2212.team4;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;

/**
 * GradesTable class is a type of TableModel that will contain the grades of students in a given Course Object.
 * This class allows for the viewing, editing, and removal of Grades from students in a course.
 *
 * @author Jenna Le
 */

public class GradesTable extends DefaultTableModel{

	//Data serialization
	private static final long serialVersionUID = 1L;
	//Column titles
	private final String[] COLUMN_NAMES = {"Asn Avg", "Exam Avg"};
	//Counter for number of columns, counter for total deliverables in a course
	int numColumns, delivSize;
	//Variables (will be either 1 or 0) determining whether Asn/Exm avg columns will appear
	int asnAvg, exmAvg;
	//Keeps track of students and their grades in the table
	private final List<Student> studentGrades = new ArrayList<Student>();
	//Keeps track of deliverables in the table
	private final List<Deliverable> deliverableGrades = new ArrayList<Deliverable>();
	//Current course
	Course currCourse;

	/**
	 * Default constructor creating an empty GradesTable.
	 */
	public GradesTable() {
		addColumn("Average");
		delivSize = 0;
		asnAvg = 0;
		exmAvg = 0;
		numColumns = 1;
	}

	/**
	 * Overloaded constructor, creates empty GradesTable and allows for the deactivation of some columns.
	 * User will input '0' or '1', whether they want to hide or show the column, respectively.
	 *
	 * @param currCourse The current/active course object that this GradesTable belongs to
	 * @param asnAvg     0 - hide, 1 - show assignment average column
	 * @param exmAvg     0 - hide, 1 - show exam average column
	 */
	public GradesTable(Course currCourse, int asnAvg, int exmAvg) {
		this.asnAvg = asnAvg;
		this.exmAvg = exmAvg;
		addColumn("Average");
		this.currCourse = currCourse;
		int infoColumns=0;

		int[] columns = {asnAvg,exmAvg};
		for (int i = 0; i < 2; i++){
			if (columns[i]==1){
				addColumn(COLUMN_NAMES[i]);
				infoColumns++;
			}
		}
		for (int i = 0; i < currCourse.getDeliverableListSize(); i++) {
			//Creates a column header read as: DeliverableName [Weight%]
			if (currCourse.getDeliverable(i) != null) {
				deliverableGrades.add(currCourse.getDeliverable(i));
				addColumn(currCourse.getDeliverable(i).getName() + " [" + (int) currCourse.getDeliverable(i).getWeight() + "%]");
			}
		}
		numColumns = currCourse.getDeliverableListSize()+infoColumns+1;
	}

	/**
	 * Adds a row of grades to the table for a student. It takes into account whether asnAvg or exmAvg
	 * columns are visible, and adjusts accordingly.
	 *
	 * @param s student containing grades to be added to the table
	 */
	public void addGrades(Student s) {
		Object[] grades = new Object [numColumns];
		studentGrades.add(s);

		int ctr = 0;
		if(s.getNumGrades()>0||s.getAvg()>-1||s.getExmAvg()>-1||s.getAsnAvg()>-1) {
			if (s.getAvg()>=0)
				//                grades[ctr++] = s.getAvg(); //Insert into table as a Double
				grades[ctr++] = String.format("%.2f", s.getAvg());
			else
				grades[ctr++] = "";
			if (asnAvg==1 && s.getAsnAvg()>=0)
				//                grades[ctr++] = s.getAsnAvg(); //Insert into table as a Double
				grades[ctr++]= String.format("%.2f", s.getAsnAvg());
			else if(asnAvg==1)
				grades[ctr++] = "";
			if (exmAvg==1 && s.getExmAvg()>=0)
				//                grades[ctr++] = s.getExmAvg(); //Insert into table as a Double
				grades[ctr++]=String.format("%.2f", s.getExmAvg());
			else if(exmAvg==1)
				grades[ctr++]= "";
			for (int i = 0; i < deliverableGrades.size(); i++) {
				if (deliverableGrades.get(i) != null) {
					int id = deliverableGrades.get(i).getObjId();
					if (s.getGrade(id) >= 0) {
						//                        grades[ctr++] = s.getGrade(id);
						grades[ctr++] = String.format("%.2f", s.getGrade(id));
					} else
						grades[ctr++] = "";
				}
			}
			addRow(grades);
		} else addRow(new Object[]{null});
	}

	/**
	 * Allows for the editing of the Student object in 
	 * the course by editing cells in the GradesTable.
	 *
	 * @param aValue      value to be edited
	 * @param rowIndex    row location to be edited
	 * @param columnIndex column location to be edited
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if ((rowIndex < 0) || (rowIndex >= currCourse.getStudentListSize()))
			return;
		else if (columnIndex < 1+asnAvg+exmAvg) {
			String clmn = getColumnName(columnIndex);
			switch (clmn) {
			case "Average":
				if(aValue.equals(""))
					currCourse.getStudent(currCourse.findStudent(studentGrades.get(rowIndex).getNumber())).setAvg(-1);
				else if(!(((String)aValue).matches("\\d+(\\.\\d+)?")))
					return;
				else if (Double.parseDouble((String) aValue)>100||Double.parseDouble((String) aValue)<0)
					return;
				else
					currCourse.getStudent(currCourse.findStudent(studentGrades.get(rowIndex).getNumber())).setAvg(Double.parseDouble((String) aValue));

				fireTableCellUpdated(rowIndex,columnIndex);
				return;
			case "Asn Avg":
				if(aValue.equals(""))
					currCourse.getStudent(currCourse.findStudent(studentGrades.get(rowIndex).getNumber())).setAsnAvg(-1);
				else if(!(((String)aValue).matches("\\d+(\\.\\d+)?")))
					return;
				else if (Double.parseDouble((String) aValue)>100||Double.parseDouble((String) aValue)<0)
					return;
				else
					currCourse.getStudent(currCourse.findStudent(studentGrades.get(rowIndex).getNumber())).setAsnAvg(Double.parseDouble((String) aValue));
				fireTableCellUpdated(rowIndex,columnIndex);
				return;
			case "Exam Avg":
				if(aValue.equals(""))
					currCourse.getStudent(currCourse.findStudent(studentGrades.get(rowIndex).getNumber())).setExmAvg(-1);
				else if(!(((String)aValue).matches("\\d+(\\.\\d+)?")))
					return;
				else if (Double.parseDouble((String) aValue)>100||Double.parseDouble((String) aValue)<0)
					return;
				else
					currCourse.getStudent(currCourse.findStudent(studentGrades.get(rowIndex).getNumber())).setExmAvg(Double.parseDouble((String) aValue));
				fireTableCellUpdated(rowIndex,columnIndex);
				return;
			}
		}
		else {
			Deliverable d = deliverableGrades.get(columnIndex - (1+asnAvg+exmAvg));
			if (aValue.equals("")){
				currCourse.getStudent(rowIndex).removeGrade(d.getObjId(),
						d.getType());
			}
			else if(!(((String)aValue).matches("\\d+(\\.\\d+)?")))
				return;
			else {
				if (Double.parseDouble((String) aValue)>100||Double.parseDouble((String) aValue)<0)
					return;
				currCourse.getStudent(rowIndex).addGrade(d.getObjId(),
						Double.parseDouble((String) aValue), d.getType(),
						d.getWeight());
			}
		}

		fireTableCellUpdated(rowIndex, columnIndex);
	}

	/**
	 * Method to restrict the user from manually editing a student's calculated course grade.
	 * This grade is calculated automatically by the program, and so does not need to be edited.
	 *
	 * @param rowIndex    row location to be checked
	 * @param columnIndex column location to be checked
	 * @return boolean       true if cell is editable, false otherwise
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex < 0 || columnIndex > numColumns || rowIndex > studentGrades.size()-1)
			return false;
		else if (getColumnName(columnIndex).equals(""))
			return false;
		else
			return true;
	}
}