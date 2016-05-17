package model;

import java.util.ArrayList;

public class Course implements java.io.Serializable {
	
	private static final long serialVersionUID = -8690646271614304086L;
	private String courseSubjectCode;
	private String courseCourseCode;
	private String courseID;
	private String courseName;
	private ArrayList<Period> coursePeriods;
	private int courseCredits;
	private ArrayList<Student> courseRoster;
	
	public Course(String courseSubjectCode, String courseCourseCode, String courseName, int courseCredits) {
		this.courseSubjectCode = courseSubjectCode;
		this.courseCourseCode = courseCourseCode;
		this.courseID = courseSubjectCode + ":" + courseCourseCode;
		this.courseName = courseName;
		this.coursePeriods = new ArrayList<Period>();
		this.courseCredits = courseCredits;
		this.courseRoster = new ArrayList<Student>();
	}
	
	public Course(String courseID, String courseName, int courseCredits) {
		this.courseID = courseID;
		this.courseSubjectCode = courseID.substring(0,2);
		this.courseCourseCode = courseID.substring(4,6);
		this.courseName = courseName;
		this.coursePeriods = new ArrayList<Period>();
		this.courseCredits = courseCredits;
		this.courseRoster = new ArrayList<Student>();
	}
	
	public String getSubjectCode() {
		return courseSubjectCode;
	}
	
	public void setSubjectCode(String subjectCode) {
		courseSubjectCode = subjectCode;
	}
	
	public String getCourseCode() {
		return courseCourseCode;
	}
	
	public void setCourseCode(String courseCode) {
		courseSubjectCode = courseCode;
	}
	
	public String getCID() {
		return courseID;
	}
	
	public void setCID(String cid) {
		courseID = cid;
	}
	
	public void refreshCID() {
		courseID = courseSubjectCode + ":" + courseCourseCode;
	}
	
	public String getName() {
		return courseName;
	}
	
	public void setName(String cname) {
		courseName = cname;
	}
	
	public ArrayList<Period> getPeriods() {
		return coursePeriods;
	}
	
	public void setPeriods(ArrayList<Period> cperiods) {
		coursePeriods = cperiods;
	}
	
	public void addPeriod(Period period) {
		coursePeriods.add(period);
	}
	
	public void removePeriod(Period period) {
		coursePeriods.remove(period);
	}
	
	public int getCredits() {
		return courseCredits;
	}
	
	public void setCredits(int credits) {
		courseCredits = credits;
	}
	
	public ArrayList<Student> getRoster() {
		return courseRoster;
	}
	
	public void setRoster(ArrayList<Student> roster) {
		courseRoster = roster;
	}
	
	public void displayRoster(ArrayList<Student> roster) {
		for (Student s: roster) {
			System.out.println(s.getName());
		}
	}
	
	public void addStudent(Student student) {
		courseRoster.add(student);
	}
	
	public void removeStudent(Student student) {
		courseRoster.remove(student);
	}
	
	// Overriding equals
	public boolean equals(Object o) {
		  if (!(o instanceof Course)) {
		    return false;
		  }
		  Course other = (Course) o;
		  return (courseID.equals(other.courseID)) && (courseName.equals(other.courseName));
	}

	public int hashCode() {
		  return courseID.hashCode();
	}
	
}
