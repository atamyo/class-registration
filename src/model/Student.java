package model;

import java.util.ArrayList;

public class Student implements java.io.Serializable {
	
	private static final long serialVersionUID = 7636523413156858982L;
	private int studentID;
	private String studentName;
	private ArrayList<Course> studentCourses;
	
	public Student(int studentID, String studentName) {
		this.studentID = studentID;
		this.studentName = studentName;
		this.studentCourses = new ArrayList<Course>();
	}
	
	public int getSID() {
		return studentID;
	}
	
	public void setSID(int sid) {
		studentID = sid;
	}
	
	public String getName() {
		return studentName;
	}
	
	public void setName(String sname) {
		studentName = sname;
	}
	
	public ArrayList<Course> getCourses() {
		return studentCourses;
	}
	
	public void setCourses(ArrayList<Course> scourses) {
		studentCourses = scourses;
	}
	
	public void addCourse(Course course) {
		studentCourses.add(course);
	}
	
	public void dropCourse(Course course) {
		studentCourses.remove(course);
	}
	
	public void displayCourses() {
		System.out.println("Current schedule:");
		for (Course c: studentCourses) {
			System.out.println(c.getCID() + " [" + c.getName() + "] (period) Credits: " + c.getCredits());
		}
	}
	
	public int sumCredits() {
		int sum = 0;
		
		for (Course c: studentCourses) {
			sum = sum + c.getCredits();
		}
		
		return sum;
	}
	
	public void addCoursePrompt(Catalog catalog, String name) {
		ArrayList<Course> courses = new ArrayList<Course>();
		
		courses = catalog.findCourseByName(name);
		for (Course c: courses) {
			if (isLegal(c)) {
				addCourse(c);
				c.addStudent(this);
				System.out.println("Registered for course " + c.getName() + ".");
			}
		}
	}
	
	public void dropCoursePrompt(Catalog catalog, String name) {
		ArrayList<Course> courses = new ArrayList<Course>();
		
		courses = catalog.findCourseByName(name);
		for (Course c: courses) {
			dropCourse(c);
			c.removeStudent(this);
			System.out.println("Withdrew from course " + c.getName() + ".");
		}
	}
	
	public boolean isLegal(Course course) {
		
		// Checks that students isn't already registered for course
		for (Course c: this.studentCourses) {
			if (course.getCID().equals(c.getCID())) {
				System.out.println("Can't add course -- you're already registered!");
				return false;
			}
		}
		
		// Checks that student will not go over 21 credits
		if ((this.sumCredits() + course.getCredits()) > 21) {
			System.out.println("Can't add course -- your schedule will exceed the maximum load of 21 credits."
					+ "\nYou will need to withdraw from a class in order to register for " + course.getName() + ".");
			return false;
		}
		
		return true;
	}
	
	// Overriding equals
	public boolean equals(Object o) {
		  if (!(o instanceof Student)) {
		    return false;
		  }
		  Student other = (Student) o;
		  return (studentID == other.studentID) && (studentName.equals(other.studentName));
	}

	public int hashCode() {
		  return studentName.hashCode();
	}
	
}
