// Name: Christian Santiago, Hiroki Sato, Zachary Gould
// Assignment: Group Project
// Course: CMS 170 Problem Solving II with Java
// Due Date: 12/2/19
// ----------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------
import java.util.*;
public class Patient {
	// DATA MEMBERS
	private 	   String 			 firstName 	  ,
									 lastName  	  ,
				   			  		 illness   	  ,
				   			  		 wardAssign	  ,
				   			  		 healthcare	  ,
				   			  		 status	   	  ,
				   			  		 doctor		  ,
				   			  		 arrivalTime  ;
	
	private 	   ArrayList<String> treatments;
	private 	   int 			 	 age; 
	
	private static int 		  		 IDGenerator = 0; 
	private		   int 				 ID; 
	
	// ----------------------------------------
	// ----------------------------------------
	// CONSTRUCTOR	
	public Patient() {
		firstName     = "";
		lastName   	  = "";
		
		ID 		   	  = IDGenerator;
		IDGenerator   = IDGenerator + 1;
		
		illness	      = "";
		wardAssign    = "";
		age 	      = -1;
		treatments    = new ArrayList<String>();
		healthcare    = "";
		status 	      = "";
		doctor 	      = "";
		arrivalTime	  = "";
	}
	
	// ----------------------------------------
	// ----------------------------------------
	// ACCESSORS AND MUTATORS
	public String getFirstName() {
		return firstName;
	}
	
	// ----------------------------------------
	public void setFirstName(String fname) {
		firstName = fname;
	}
	
	// ----------------------------------------
	public String getLastName() {
		return lastName;
	}
	
	// ----------------------------------------
	public void setLastName(String lname) {
		lastName = lname;
	}
	
	// ----------------------------------------
	public int getID() {
		return ID;
	}
	
	// ----------------------------------------
	public void setID(int id) {
		ID = id;
	}
	
	// ----------------------------------------
	public String getIllness() {
		return illness;
	}
	
	// ----------------------------------------
	public void setIllness(String sickness) {
		illness = sickness;
	}
	
	// ----------------------------------------
	public int getAge() {
		return age;
	}
	
	// ----------------------------------------
	public void setAge(int patientAge) {
		age = patientAge;
	}
	
	// ----------------------------------------
	public String getWardAssign() {
		return wardAssign;
	}

	// ----------------------------------------
	public void setWardAssign(String wardName) {
		wardAssign = wardName;
	}
	
	// ----------------------------------------
	public ArrayList<String> getTreatments() {
		return treatments;
	}
	
	// ----------------------------------------
	public void setTreatments(ArrayList<String> patientTreatments) {
		treatments = patientTreatments;
	}
	
	// ----------------------------------------
	public String getHealthcare() {
		return healthcare;
	}
	
	// ----------------------------------------
	public void setHealthcare(String insurance) {
		healthcare = insurance;
	}
	
	// ----------------------------------------
	public String getStatus() {
		return status;
	}
	
	// ----------------------------------------
	public void setStatus(String currentStatus) {
		status = currentStatus;
	}
	
	// ----------------------------------------
	public String getDoctor() {
		return doctor;
	}
	
	// ----------------------------------------
	public void setDoctor(String doctorName) {
		doctor = doctorName;
	}
	
	// ----------------------------------------
	public String getArrivalTime() {
		return arrivalTime;
	}
	
	// ----------------------------------------
	public void setArrivalTime(String ti) {
		arrivalTime = ti;
	}
	
	// ----------------------------------------
	// ----------------------------------------
	// BUSINESS METHODS: toString method
	public String toString() {
		return "First name: " + firstName + "\n" + 
				"Last name: " + lastName  + "\n" + 
				"ID: "        + ID        + "\n" + 
				"Age: "       + age       + "\n" + 
				"Doctor: "    + doctor    + "\n" +
				"Healthcare: "+ healthcare+ "\n" +
				"Illness: "   + illness   + "\n" +
				"Status: "    + status    + "\n";
				
	}
	
	// ----------------------------------------
}