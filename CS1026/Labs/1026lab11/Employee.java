/* Define a class for representing employees 
 * of a University 
 */ 
public class Employee { 
  
  /* attributes (instance variables) */ 
  private String firstName; 
  private String lastName; 
  private String employeeNumber; 
  private String jobTitle; 
  private String department; 
  private double salary; 
  
  /* constructors */ 
  public Employee(String empFirst,String empLast,String empNumber)
  {
    this.firstName=empFirst;
    this.lastName=empLast;
    this.employeeNumber=empNumber;
  }
  public Employee(String empFirst,String empLast,String empNumber,String empJobTitle,String empDepartment,Double empSalary)
  {
    this.firstName=empFirst;
    this.lastName=empLast;
    this.employeeNumber=empNumber;
    this.jobTitle=empJobTitle;
    this.department=empDepartment;
    this.salary=empSalary;
  }
  /* methods */ 
  public String toString ()
  {
    String returnInfo;
    if (this.jobTitle==null) returnInfo = this.firstName+" "+this.lastName+" "+this.employeeNumber;
    else returnInfo = this.firstName+" "+this.lastName+" "+this.employeeNumber+" "+this.jobTitle+" "+this.department+" "+this.salary;
    return returnInfo;
 }
  public String getFirstName()
  {
    return this.firstName;
  }
  public String getLastName()
  {
    return this.lastName;
  }
  public Double getSalary()
  {
    return this.salary;
  }
  public void setSalary(Double employeeSalary)
  {
    salary=employeeSalary;
  }
  public String lastNameFirst()
  {
    return this.lastName+", "+this.firstName; 
  }
  /* for testing: main method for testing the class */ 
  
  public static void main (String[] args) { 
    
    // create 2 sample Employee objects  
    Employee emp1 = new Employee("Albert","Einstein","186000"); 
    Employee emp2 = new Employee("Grace","Hopper","19451945","Faculty","Computer Science",86333.76); 
    
    //show the employee information 
    //try out the accessor and modifier methods 
    System.out.println(emp1.getFirstName() + " " + emp1.getLastName()); 
    emp2.setSalary(emp2.getSalary() + 1000.00); 
    System.out.println(emp2.lastNameFirst());
    System.out.println(emp2.getSalary());
    /* add code to try out other method(s) here */ 
    
  }// end of main method 
  
}// end of Employee class definition