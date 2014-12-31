package cs2212.team4;

public class StudentReportData {
	private Student student;
	private Course course;
	private int i;

	public StudentReportData(Student student, Course course, int i) {
		this.student = student;
		this.course = course;
		this.i = i;
	}

	public String getCourseTitle() {
		if (i > -1)
			return "";
		return course.getTitle();
	}

	public String getCourseTerm() {
		if (i > -1)
			return "";
		return course.getTerm();
	}

	public String getCourseCode() {
		if (i > -1)
			return "";
		return course.getCode();
	}

	public double getClassAvg() {
		if (i > -1)
			return -1;
		return course.getClassAvg();
	}

	public double getClassAsnAvg() {
		if (i > -1)
			return -1;
		return course.getClassAsnAvg();
	}

	public double getClassExamAvg() {
		if (i > -1)
			return -1;
		return course.getClassExamAvg();
	}

	public String getStudentFirstName() {
		if (i > -1)
			return "";
		return student.getNameFirst();
	}

	public String getStudentLastName() {
		if (i > -1)
			return "";
		return student.getNameLast();
	}

	public String getStudentNumber() {
		if (i > -1)
			return "";
		return student.getNumber();
	}

	public String getStudentEmail() {
		if (i > -1)
			return "";
		return student.getEmail();
	}

	public double getStudentAvg() {
		if (i > -1)
			return -1;
		return student.getAvg();
	}

	public double getStudentAsnAvg() {
		if (i > -1)
			return -1;
		return student.getAsnAvg();
	}

	public double getStudentExamAvg() {
		if (i > -1)
			return -1;
		return student.getExmAvg();
	}

	public double getGrade() {
		if (i == -1)
			return -1;
		return student.getGrade(i);
	}

	public double getWeight() {
		if (i == -1)
			return -1;
		return course.getDeliverable(i).getWeight();
	}
	
	public double getDeliverAvg(){
		if (i == -1)
			return -1;
		return course.getClassDeliverableAvg(i);
	}

	public String getDeliverName() {
		if (i == -1)
			return "";
		return course.getDeliverable(i).getName();
	}
	public String getDeliverType() {
		if (i == -1)
			return "";
		return course.getDeliverable(i).getType();
	}
}
