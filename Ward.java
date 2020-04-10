// Name: Christian Santiago, Hiroki Sato, Zachary Gould
// Assignment: Group Project
// Course: CMS 170 Problem Solving II with Java
// Due Date: 12/2/19
// ----------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------
import java.util.*;
public class Ward {
	//DATA MEMBERS
	private ArrayList<Patient> patientList;   
	private String 			   departmentName;
	private int 			   capacity;
	
	// ----------------------------------------
	// ----------------------------------------
	//CONSTRUCTORS
	public Ward() {
		patientList    = new ArrayList<>();		
		departmentName = "";					
		capacity       = 0;						
	}
	
	// ----------------------------------------
	public Ward(String newName, int cap) {
		patientList    = new ArrayList<>();		
		departmentName = newName;				
		capacity       = cap;
	}
	
	// ----------------------------------------
	// ----------------------------------------
	//ACCESSORS AND MUTATORS
	public ArrayList<Patient> getPatientList(){
		return this.patientList;						
	}
	
	// ----------------------------------------
	public void setPatientList(ArrayList<Patient> newPs) {
		patientList = newPs;										
	}
	
	// ----------------------------------------
	public String getDepartmentName() {
		return this.departmentName;
	}
	
	// ----------------------------------------
	public void setDepartmentName(String newName) {
		departmentName = newName;								
	}
	
	// ----------------------------------------
	public int getCapacity() {
		return this.capacity;
	}
	
	// ----------------------------------------
	public void setCapacity(int newCap) {
		capacity = newCap;
	}
	
	// ----------------------------------------
	// BUSINESS METHODS:
	// findPatient method: Linear search of patientList
	public Patient findPatient(int id) {
		boolean found	     = false;
		int	    currentIndex = 0;
		int	    patientId; 					
		
		while(currentIndex < patientList.size() && found == false) {
			patientId = patientList.get(currentIndex).getID();
			
			if(patientId == id) {
				found = true;
			} else {
				currentIndex++;
			}
		}
		
		if(found == true) {
			return patientList.get(currentIndex);
		}
		else {
			return null;
		}
	}
																				
	// ----------------------------------------	
	public void addToWard(Patient newPatient) {
		if(patientList.size() < capacity) {										
			patientList.add(newPatient);										
		}
		else {
			System.out.println("Sorry, the " + getDepartmentName() + " ward is currently full.");
		}
	}
	
	// ----------------------------------------	
	//removefromWard method: Linear search of patientList to find patient with matching ID number and remove them from the ward
	public void removeFromWard(int patientID) {
		int index = 0;
		boolean patientFound = false;
		
		while(index < patientList.size() && patientFound == false) {
			if(patientList.get(index).getID() == patientID) {
				patientList.remove(index);
				patientFound = true;
			} else {
				index ++;
			}
		}
	}
	
	// ----------------------------------------
	// toString uses StringBuilder object to allow all patients' data to be returned at once
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(departmentName + "\n" + "------------------------------" + "\n");
		
		int currentIndex = 0;
		while(currentIndex < patientList.size()) {
			Patient temp = patientList.get(currentIndex);
			sb.append(temp.toString());
			sb.append("\n");
			
			currentIndex = currentIndex +1;
		}
		
		return sb.toString();
	}

	// ----------------------------------------
}