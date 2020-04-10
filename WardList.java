// Name: Christian Santiago, Hiroki Sato, Zachary Gould
// Assignment: Group Project
// Course: CMS 170 Problem Solving II with Java
// Due Date: 12/2/19
// ----------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------
public class WardList {
	// DATA MEMBERS
	private WardNode head;
	
	// ----------------------------------------
	// CONSTRUCTOR
	public WardList () {
		head = null;
	}
	
	// ----------------------------------------
	// ACCESSORS AND MUTATORS
	public WardNode getHead () {
		return head;
	}
	
	// ----------------------------------------
	public void setHead (WardNode insertHead) {
		head = insertHead;
	}
	
	// ----------------------------------------
	// BUSINESS METHODS
	public boolean isEmpty () {
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}
	
	// ----------------------------------------
	public void addToFront (WardNode newWardNode) {
		if (isEmpty()) {
			newWardNode.setNext(null);
		} else {
			newWardNode.setNext(head);
		}
		
		newWardNode.setPrevious(null);
		head = newWardNode;
	}
	
	// ----------------------------------------
	//deletewardNode method: Traverses list to find a specified node by the department's name and deletes the node from the list 
	public void deleteWardNode (String targetDepartment) {
		WardNode temptr = head;
		boolean  found  = false;
		
		while (head != null && found != true) {
			if (temptr.getDepartment().getDepartmentName().equals(targetDepartment)) {
				found  = true;
			} else {
				temptr = temptr.getNext();
			}
		}
		
		if (found == true) {
			if (head == temptr) {
				if (head.getNext() == null) {			// in case list is only 1 node long
					head = null;
				} else {
					head = temptr.getNext();
					temptr.getNext().setPrevious(null);
				}
				temptr.setNext(null);
				
			} else if (temptr.getNext() == null) {		// if node is at end of list
				temptr.getPrevious().setNext(null);		
				temptr.setPrevious(null);
				
			} else {									// if node is in middle
				temptr.getPrevious().setNext(temptr.getNext());
				temptr.getNext().setPrevious(temptr.getPrevious());
				temptr.setNext(null);
				temptr.setPrevious(null);
			}
		} else {
			System.out.println(targetDepartment + " ward could not be found");
		}
	}

	// ----------------------------------------
	// globalPatientSearch method: Traverses entire list to find a patient based on the ID
	public Patient globalPatientSearch (int id) {
		
		WardNode temptr = getHead();
		boolean patientFound = false;
		Patient tempPatient = null;
		
		while (temptr != null && patientFound == false) {
			tempPatient = temptr.getDepartment().findPatient(id);
			
			if (tempPatient != null) {
				patientFound = true;
			} else {
				if (temptr.getNext() == null) {
					temptr = null;
				} else {
					temptr = temptr.getNext();
				}
			}
		}
		
		if (patientFound == true) {
			return tempPatient;
		} else {
			return null;
		}
	}

	//----------------------------------------
	// locatePatientDepartment method: Traverses all departments to find which department a patient belongs to using the patient's 
	// wardAssign and each Ward's departmentName  
	public Ward locatePatientDepartment (Patient insertPatient) {
		
		WardNode temptr = getHead();
		boolean wardFound = false;
		
		while(temptr != null && wardFound == false) {
			if(temptr.getDepartment().getDepartmentName().equals(insertPatient.getWardAssign())) {
				wardFound = true;
			}
			else {
				temptr = temptr.getNext();
			}
		}
		
		if (wardFound == true) {
			return temptr.getDepartment();
		} else {
			return null;
		}
	}
	
	// ----------------------------------------
}