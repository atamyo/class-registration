package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Catalog;
import model.EnrolledStudents;
import model.Student;

public class WebRegApp {

	public static void main(String[] args) {
		
		String enrolledPath = "src/data/Enrollment.ser";
		String catalogPath = "src/data/Catalog.ser";
		
		EnrolledStudents enrolled = loadStudents(enrolledPath);
		Catalog catalogue = loadCatalog(catalogPath);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int ruid = 0, input = 0;
		String sname = null, cname = null;
		Student loggedIn = null;
		boolean Running = true;
		
		// Login
		System.out.println("Please enter your RUID: ");
		try {
			ruid = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		loggedIn = enrolled.findStudentByRUID(ruid);
		if (loggedIn == null) {
			System.out.println("Welcome, new student.\nPlease enter your name:");
			try {
				sname = br.readLine();
				enrolled.addStudent(new Student(ruid, sname));
				saveStudents(enrolled, enrolledPath);
				loggedIn = enrolled.findStudentByRUID(ruid);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Welcome back, " + loggedIn.getName() + ".");
		}
		
		while(Running) {
			System.out.println("\nMenu:"
					+ "\n1. Look up courses by name"
					+ "\n2. Look up courses by department"
					+ "\n3. View catalog, sorted by department/course number"
					+ "\n4. View catalog, sorted by scheduled day/period"
					+ "\n5. View my course schedule"
					+ "\n6. View my current credit load"
					+ "\n7. Register for a course"
					+ "\n8. Withdraw from a course"
					+ "\n9. View a courses' roster"
					+ "\n10. Quit"
					+ "\n\nChoice (1-10)?\n");
			
			try {
				input = Integer.parseInt(br.readLine());
			} 
			catch (NumberFormatException e) {
				// TODO Auto-generated catch block
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			switch(input) {
			case 1:
				// Look up courses by name
				System.out.println("\nEnter course name: ");
				try {
					cname = br.readLine();
					catalogue.displayResults(catalogue.findCourseByName(cname));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 2:
				// Look up courses by department
				System.out.println("\nEnter department number: ");
				try {
					cname = br.readLine();
					catalogue.displayResults(catalogue.findCourseBySubj(cname));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 3:
				// View catalog, sorted by department/course number
				catalogue.displayCatalog();
				break;
			case 4:
				// View catalog, sorted by scheduled day/period
				catalogue.displayCatalog();
				break;
			case 5:
				// View my course schedule
				loggedIn.displayCourses();
				break;
			case 6:
				// View my current credit load
				System.out.println("Current credit load: " + loggedIn.sumCredits());
				break;
			case 7:
				// Register for a course
				System.out.println("\nEnter course name: ");
				try {
					cname = br.readLine();
					loggedIn.addCoursePrompt(catalogue, cname);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 8:
				// Withdraw from a course
				System.out.println("\nEnter course name: ");
				try {
					cname = br.readLine();
					loggedIn.dropCoursePrompt(catalogue, cname);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 9:
				// View a courses' roster
				System.out.println("\nEnter course name: ");
				try {
					cname = br.readLine();
					catalogue.displayRoster(cname);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 10:
				// Quit
				System.out.println("Logged out of WebReg.");
				Running = false;
				break;
			/*case 11:
				try {
					catalogue.addCoursePrompt();
					saveCatalog(catalogue, catalogPath);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 12:
				enrolled.displayStudents();
				break;*/
			default:
				System.out.println("Invalid input. Please enter a number from 1 to 10.");
				break;
			}			
		}
		
		saveStudents(enrolled, enrolledPath);
		saveCatalog(catalogue, catalogPath);

	}
	
	public static EnrolledStudents loadStudents(String filePath) {
		EnrolledStudents es = null;
		try {
			FileInputStream file = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(file);
			es = (EnrolledStudents) in.readObject();
			in.close();
			file.close();
		}
		catch(FileNotFoundException i)
	    {
			System.out.println("Enrolled file not found");
			return EnrolledStudents.initializeStudents();
	    }
		
		catch(ClassNotFoundException c)
	    {
			System.out.println("EnrolledStudents class not found");
			return EnrolledStudents.initializeStudents();
	    } 
		catch (IOException e) {
			System.out.println("Something went wrong");
			return EnrolledStudents.initializeStudents();
		}
		
		return es;
	}
	
	public static Catalog loadCatalog(String filePath) {
		Catalog ca = null;
		try {
			FileInputStream file = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(file);
			ca = (Catalog) in.readObject();
			in.close();
			file.close();
		}
		catch(FileNotFoundException i)
	    {
			System.out.println("Catalog file not found");
			return Catalog.initializeCourses();
	    }
		
		catch(ClassNotFoundException c)
	    {
			System.out.println("Catalog class not found");
			return Catalog.initializeCourses();
	    } 
		catch (IOException e) {
			System.out.println("Something went wrong");
			return Catalog.initializeCourses();
		}
		
		return ca;
		
	}
	
	public static boolean saveStudents(EnrolledStudents es, String filePath) {
		try {
	         FileOutputStream fileOut = new FileOutputStream(filePath);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(es);
	         out.close();
	         fileOut.close();
		}
		catch(IOException i) {
			i.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static boolean saveCatalog(Catalog c, String filePath) {
		try {
	         FileOutputStream fileOut = new FileOutputStream(filePath);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(c);
	         out.close();
	         fileOut.close();
		}
		catch(IOException i) {
			i.printStackTrace();
			return false;
		}
		
		return true;
	}

}
