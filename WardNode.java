// Name: Christian Santiago, Hiroki Sato, Zachary Gould
// Assignment: Group Project
// Course: CMS 170 Problem Solving II with Java
// Due Date: 12/2/19
// ----------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------
public class WardNode {
	// DATA MEMBERS
	private Ward     department;
	private WardNode previous;
	private WardNode next;
	
	// ----------------------------------------
	// CONSTRUCTOR 
	public WardNode () {
		department = null;
		previous   = null;
		next       = null;
	}
	
	// ----------------------------------------
	public WardNode (Ward insertDepartment, WardNode insertPrevious, WardNode insertNext) {
		department = insertDepartment;
		previous   = insertPrevious;
		next       = insertNext;
	}
	
	// ----------------------------------------
	// ACCESSORS AND MUTATORS
	public Ward getDepartment () {
		return department;
	}
	
	// ----------------------------------------
	public void setDepartment (Ward insertDepartment) {
		department = insertDepartment;
	}
	
	// ----------------------------------------
	public WardNode getPrevious () {
		return previous;
	}
	
	// ----------------------------------------
	public void setPrevious (WardNode insertPrevious) {
		previous = insertPrevious;
	}

	// ----------------------------------------
	public WardNode getNext () {
		return next;
	}
	
	// ----------------------------------------
	public void setNext (WardNode insertNext) {
		next = insertNext;
	}
	
	// ----------------------------------------
	// BUSINESS METHOD
	public String toString () {
		return String.format("Ward: " + "%-20s" + "Capacity: " + "%-6d", department.getDepartmentName(), department.getCapacity());
	}
}