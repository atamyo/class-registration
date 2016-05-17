package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class EnrolledStudents implements java.io.Serializable {
	
	private static final long serialVersionUID = 845084005691355866L;
	private ArrayList<Student> enrolledStudents;
	
	public EnrolledStudents() {
		this.enrolledStudents = new ArrayList<Student>();
	}
	
	public static EnrolledStudents initializeStudents() {
		EnrolledStudents es = new EnrolledStudents();
		es.addStudent(new Student(777777777, "Admin"));
		return es;
	}
	
	public void addStudent(Student student) {
		enrolledStudents.add(student);
	}
	
	public void removeStudent(Student student) {
		enrolledStudents.remove(student);
	}
	
	public void addStudentPrompt() throws NumberFormatException, IOException {
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		int ruid;
		String name;
		
		System.out.println("Adding a student.\nEnter RUID: ");
		ruid = Integer.parseInt(b.readLine());

		System.out.println("\nEnter name: ");
		name = b.readLine();
		
		addStudent(new Student(ruid, name));
	}
	
	public void displayStudents() {
		for (Student s: enrolledStudents) {
			System.out.println(s.getName() + " (RUID: " + s.getSID() + ")");
		}
	}
	
	public Student findStudentByRUID(int ruid) {
		for (Student s: enrolledStudents) {
			if (s.getSID() == ruid) {
				return s;
			}
		}
		return null;
	}
}
