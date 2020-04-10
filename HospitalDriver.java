// Name: Christian Santiago, Hiroki Sato, Zachary Gould
// Assignment: Group Project
// Course: CMS 170 Problem Solving II with Java
// Due Date: 12/2/19
// ----------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------
import java.io.*;
import java.util.*;
public class HospitalDriver {
	
	// DATA MEMBERS
	private static Scanner 	   inputScan;
	private static PrintWriter outputWriter;
	private static WardList    hospital = new WardList();
	
	// ------------------------------------------------
	// ------------------------------------------------ 
	// MAIN
	public static void main(String[] args) {
		buildHospital();
		processDataTransactions();
		printSummarySheet();	
	}

	// ------------------------------------------------
	// ------------------------------------------------ 
	// BUSINESS METHODS
	//buildHospital method: collects the data from the raw file and uses it to create the initial hospital (Wards and Patients)
	public static void buildHospital () {
		try {
			
			inputScan = new Scanner (new File("hospitaldata.txt"));
			buildDepartments();
			populateDepartments();
			
		} catch (FileNotFoundException e) {
			
			System.out.println("hospitaldata.txt not found");
			
		}
	}
	
	//------------------------------------------------
	//processDataTransactions: uses data from transactions file to alter patient data
	public static void processDataTransactions () {
		try {
			inputScan = new Scanner (new File ("datatransactions.txt"));
			String [] tokens;			
			
			while (inputScan.hasNextLine()) {
				tokens = inputScan.nextLine().split(",");
				performTransaction(tokens);
			} 	
		} catch (FileNotFoundException e) {
			System.out.println("datatransactions.txt could not be found");
		}
	}
	
	// ------------------------------------------------
	// printSummarySheet: traverses the hospital list and prints out a formatted summary of important patient data by each department
	public static void printSummarySheet() {
		
		try {
			WardNode temp = hospital.getHead();
			
			String summaryFile = "summarysheet.txt";
			File f = new File(summaryFile);
			outputWriter = new PrintWriter(f);
			
			while (temp != null) {
				
				outputWriter.println("[" + temp.getDepartment().getDepartmentName() + " Ward" + "]");
				outputWriter.println();
				outputWriter.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				outputWriter.println();
				
				for (int i = 0; i < temp.getDepartment().getPatientList().size(); i++) {
					outputWriter.println(temp.getDepartment().getPatientList().get(i).toString());
				}
				
				outputWriter.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				outputWriter.println();
				
				temp = temp.getNext();
			}
			
			outputWriter.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File was not located.");
		}
	}
	
	// ------------------------------------------------
	// ------------------------------------------------
	// HELPER METHODS
	//buildDepartment method: uses data at the beginning of the raw input file to create and set the data of the hospital departments
	public static void buildDepartments () {
		
		String [] tokens;
		int numDepartments = Integer.parseInt(inputScan.nextLine());
		
		for(int i = 0; i < numDepartments; i++) {
			tokens = inputScan.nextLine().split(",");
			
			WardNode department = new WardNode();
			Ward newWard = new Ward();
			
			newWard.setDepartmentName(tokens[0]);
			newWard.setCapacity(Integer.parseInt(tokens[1]));
			
			department.setDepartment(newWard);
			hospital.addToFront(department);
		}
	}
	
	// ------------------------------------------------
	//populateDepartment method: adds a patient object to the correct department(Ward) by using locatePatientDepartment method 
	public static void populateDepartments () {
		
		String [] tokens;
		Patient patient = null;
		Ward patientDepartment = null;

		while (inputScan.hasNextLine()) {
			tokens = inputScan.nextLine().split(",");
			patient = new Patient();
			fillPatientData(tokens, patient, 0);
			
			patientDepartment = hospital.locatePatientDepartment(patient);
			
			if (patientDepartment != null) {
				
				patientDepartment.addToWard(patient);
				
			} else {
				
				System.out.println(patient.getWardAssign() + " ward was not found in this hospital");
				
			}
		}		
	}
	
	// ------------------------------------------------
	// fillPatientData method: uses the data from the raw file to set the data of a patient.
	public static void fillPatientData(String [] patientDataTokens, Patient newPatient, int inputType) {
		
		newPatient.setWardAssign(patientDataTokens[inputType]);
		newPatient.setFirstName(patientDataTokens[inputType+1]);
		newPatient.setLastName(patientDataTokens[inputType+2]);
		newPatient.setAge(Integer.parseInt(patientDataTokens[inputType+3]));
		newPatient.setIllness(patientDataTokens[inputType+4]);
		newPatient.setStatus(patientDataTokens[inputType+5]);
		newPatient.setArrivalTime(patientDataTokens[inputType+6]);
		newPatient.setDoctor(patientDataTokens[inputType+7]);
		newPatient.setHealthcare(patientDataTokens[inputType+8]);
		
		ArrayList <String> tempTreatments = new ArrayList <String> ();
		
		for (int i = inputType+9; i < patientDataTokens.length; i++) {
			
			tempTreatments.add(patientDataTokens[i]);
		}
		newPatient.setTreatments(tempTreatments);		
	}
	
	// ------------------------------------------------
	//performTransaction method: interprets a transaction command from the transactions file and executes the command on the 
	//   patients of the hospital
	public static void performTransaction(String [] transactionTokens) {
		Patient tempPatient;		
		
		//Cillness: search for a patient who has a matching id which is given from index of 1 and then change illness of the patient.
		if (transactionTokens[0].equals("Cillness")) {
			tempPatient = hospital.globalPatientSearch (Integer.parseInt(transactionTokens[1]));
			if (tempPatient != null) {
				tempPatient.setIllness(transactionTokens[2]);
			} else {
				System.out.println("patient with id of " + transactionTokens[1] + " could not be found");
			}
		
		// ------
		// Cdepartment: search for a patient who has a matching id which is given from index of 1 and change the ward that they are in. 
		} else if (transactionTokens[0].equals("Cdepartment")) {
			tempPatient = hospital.globalPatientSearch (Integer.parseInt(transactionTokens[1]));
			if (tempPatient != null) {
				Patient testPatient = new Patient ();											// testPatient used to ensure department
				testPatient.setWardAssign(transactionTokens[2]);								//    the tempPatient is being transferred to
				Ward postChangeDepartment = hospital.locatePatientDepartment(testPatient);		// 	  exists
				if (postChangeDepartment != null) {
					if (postChangeDepartment.getPatientList().size() < postChangeDepartment.getCapacity()) {
						Ward patientDepartment = hospital.locatePatientDepartment(tempPatient);
						patientDepartment.removeFromWard(tempPatient.getID());
						postChangeDepartment.addToWard(tempPatient);
						tempPatient.setWardAssign(transactionTokens[2]);
					} else {
						System.out.println("Sorry, the " + postChangeDepartment.getDepartmentName() + " ward is currently full");
					}
				} else {
					System.out.println(transactionTokens[2] + " ward was not found in this hospital");
				}
			} else {
				System.out.println("patient with id of " + transactionTokens[1] + " could not be found");
			}
			
		// ------		
		//Cstatus: search for a patient who has matching id which is given from index of 1 and change their status.
		} else if (transactionTokens[0].equals("Cstatus")) {
			tempPatient = hospital.globalPatientSearch (Integer.parseInt(transactionTokens[1]));
			if (tempPatient != null) {
				tempPatient.setStatus(transactionTokens[2]);
			} else {
				System.out.println("patient with id of " + transactionTokens[1] + " could not be found");
			}
		
		// ------
		//Cdoctor: search for a patient who has matching id which is given from index of 1 and change their doctor. 
		} else if (transactionTokens[0].equals("Cdoctor")) {
			tempPatient = hospital.globalPatientSearch (Integer.parseInt(transactionTokens[1]));
			if (tempPatient != null) {
				tempPatient.setDoctor(transactionTokens[2]);
			} else {
				System.out.println("patient with id of " + transactionTokens[1] + " could not be found");
			}
			
		// ------
		//Rtreatment: search for a patient who has matching id which is given from index of 1 and remove treatments
		} else if (transactionTokens[0].equals("Rtreatment")) {
			tempPatient = hospital.globalPatientSearch (Integer.parseInt(transactionTokens[1]));
			if (tempPatient != null) {
				for (int i = 2; i < transactionTokens.length; i++) {
					tempPatient.getTreatments().remove(transactionTokens[i]);
				}
			} else {
				System.out.println("patient with id of " + transactionTokens[1] + " could not be found");
			}
		
		// ------	
		//Atreatment: search for a patient who has matching id which is given from index of 1 and add treatments
		} else if (transactionTokens[0].equals("Atreatment")) {
			
			tempPatient = hospital.globalPatientSearch (Integer.parseInt(transactionTokens[1]));
			
			if (tempPatient != null) {
				for (int i = 2; i < transactionTokens.length; i++) {
					tempPatient.getTreatments().add(transactionTokens[i]);
				}
			} else {
				System.out.println("patient with id of " + transactionTokens[1] + " could not be found");
			}
		
		// ------		
		// Rpatient: search for a patient who has matching id which is given from index of 1 and remove the patient from the Ward/hospital
		} else if (transactionTokens[0].equals("Rpatient")) {
			
			tempPatient = hospital.globalPatientSearch (Integer.parseInt(transactionTokens[1]));
			
			if (tempPatient != null) {
				
				Ward patientDepartment = hospital.locatePatientDepartment(tempPatient);
				
				if (patientDepartment != null) {
					patientDepartment.removeFromWard(tempPatient.getID());
				} else {
					System.out.println(tempPatient.getWardAssign() + " ward was not found in this hospital");
				}
			} else {
				System.out.println("patient with id of " + transactionTokens[1] + " could not be found");
			}
			
		// -------	
		// Apatient: adds a new patient to the hospital using the data provided in the transactions  
		} else if (transactionTokens[0].equals("Apatient")) {
			
			tempPatient = new Patient ();
			fillPatientData(transactionTokens, tempPatient, 1);
			Ward patientDepartment = hospital.locatePatientDepartment(tempPatient);
			
			if (patientDepartment != null) {
				
				patientDepartment.addToWard(tempPatient);
				
			} else {
				
				System.out.println(tempPatient.getWardAssign() + " ward was not found in this hospital");
				
			}
		}
		
		// ------	
	}

	// ------------------------------------------------
}
