package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Catalog implements java.io.Serializable {
	
	private static final long serialVersionUID = -8431238817888231144L;
	private ArrayList<Course> courseCatalog;
	
	public Catalog () {
		this.courseCatalog = new ArrayList<Course>(); 
	}
	
	public static Catalog initializeCourses() {
		Catalog ca = new Catalog();
		ca.addCourse(new Course("198", "112", "Data Structures", 4));
		ca.addCourse(new Course("560", "101", "Elementary Italian", 4));
		ca.addCourse(new Course("400", "105", "Facets of Food Science", 1));
		ca.addCourse(new Course("198", "111", "Introduction to Computer Science", 4));
		ca.addCourse(new Course("560", "232", "Italian Culture", 3));
		ca.addCourse(new Course("220", "301", "Money and Banking", 3));
		ca.addCourse(new Course("965", "300", "New York Theater Experience", 3));
		ca.addCourse(new Course("105", "343", "Observational Radio Astronomy", 3));
		ca.addCourse(new Course("776", "310", "Plant Propogation", 3));
		ca.addCourse(new Course("965", "315", "Playwriting", 3));
		ca.addCourse(new Course("105", "342", "Principles of Astrophysics", 3));
		ca.addCourse(new Course("080", "201", "Seminar in Contemporary Art", 3));
		return ca;
	}
	
	public ArrayList<Course> getCatalog() {
		return courseCatalog;
	}
	
	public void setCatalog(ArrayList<Course> catalog) {
		courseCatalog = catalog;
	}
	
	public void displayCatalog() {
		for (Course c: courseCatalog) {
			System.out.println(c.getCID() + " [" + c.getName() + "] (period) Credits: " + c.getCredits());
		}
	}
	
	public void addCoursePrompt() throws NumberFormatException, IOException {
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		String deptCode, courseCode, name;
		int credits;
		
		System.out.println("Adding a course.\nEnter department code: ");
		deptCode = b.readLine();
		
		System.out.println("\nEnter course code: ");
		courseCode = b.readLine();
		
		System.out.println("\nEnter course name: ");
		name = b.readLine();
		
		System.out.println("\nEnter number of credits: ");
		credits = Integer.parseInt(b.readLine());
		
		addCourse(new Course(deptCode, courseCode, name, credits));
		
	}
	
	public void addCourse(Course course) {
		courseCatalog.add(course);
	}
	
	public void deleteCourse(Course course) {
		courseCatalog.remove(course);
	}
	
	public ArrayList<Course> findCourseByName(String name) {
		ArrayList<Course> results = new ArrayList<Course>();
		
		for (Course c: courseCatalog) {
			if (c.getName().toLowerCase().contains(name.toLowerCase())) {
				results.add(c);
			}
		}
		
		if (results.size() == 0) {
			System.out.println("No courses found containing \"" + name + "\".");
		}
		
		return results;
	}
	
	public ArrayList<Course> findCourseBySubj(String subjCode) {
		ArrayList<Course> results = new ArrayList<Course>();
		
		for (Course c: courseCatalog) {
			if (c.getSubjectCode().equals(subjCode)) {
				results.add(c);
			}
		}
		
		if (results.size() == 0) {
			System.out.println("No courses found in Department # " + subjCode + ".");
		}
		
		return results;
	}
	
	public void displayResults(ArrayList<Course> results) {
		for (Course c: results) {
			System.out.println(c.getCID() + " [" + c.getName() + "] (period) Credits: " + c.getCredits());
		}
	}
	
	public void displayRoster(String name) {
		ArrayList<Course> courses = new ArrayList<Course>();
		
		courses = findCourseByName(name);
		for (Course c: courses) {
			System.out.println("Roster for course " + c.getName() + ":");
			c.displayRoster(c.getRoster());
		}
	}
	

}
