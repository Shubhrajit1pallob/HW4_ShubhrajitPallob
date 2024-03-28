package hw4_application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.control.Alert;


public class FileHandeling {
	
	//Local variable to store the data of the patient temporarily.
	private static String[] patientInfo = new String[7];
	private static String[] patientDataInfo = new String[7];

	//This function processes the data from the CT Scan form and put it in the array to store the data locally
	//After that the data is written to a file.
	public static void processpatientInfo(String patientId) {
		patientInfo[0] = patientId;
		patientInfo[1] = UIMain.firstNameField.getText();
		patientInfo[2] = UIMain.lastNamefield.getText();
		patientInfo[3] = UIMain.emailField.getText();
		patientInfo[4] = UIMain.phoneNumbeField.getText();
		patientInfo[5] = UIMain.healthHistoryField.getText();
		patientInfo[6] = UIMain.insuranceIdField.getText();
	}
	
	//This function clears the patient's info textFields
	public static void clearPatientInfoTextField() {
		UIMain.firstNameField.clear();
		UIMain.lastNamefield.clear();
		UIMain.emailField.clear();
		UIMain.phoneNumbeField.clear();
		UIMain.healthHistoryField.clear();
		UIMain.insuranceIdField.clear();
	}
	
	//This function Clears the patientView TextFields. 
	public static void clearPatientViewTextField() {
		UIMain.patientIDField2.clear();
		UIMain.totalCACScoreField2.clear();
		UIMain.lmField2.clear();
		UIMain.ladField2.clear();
		UIMain.lcxField2.clear();
		UIMain.racField2.clear();
		UIMain.pdaField2.clear();
	}
	
	//Process the CT Scan data and stores it locally
	//This locally stored data will be later stored in the file.
	public static void processingCTScanData() {
		patientDataInfo[0] = UIMain.patientIDField1.getText();
		patientDataInfo[1] = UIMain.totlaCACScoreField1.getText();
		patientDataInfo[2] = UIMain.lmField1.getText();
		patientDataInfo[3] = UIMain.ladField1.getText();
		patientDataInfo[4] = UIMain.lcxField1.getText();
		patientDataInfo[5] = UIMain.racField1.getText();
		patientDataInfo[6] = UIMain.pdaField1.getText();
	}
	
	//This method clears the CT Scan view TextFields.
	public static void clearCTTestField1() {
		UIMain.patientIDField1.clear();
		UIMain.totlaCACScoreField1.clear();
		UIMain.lmField1.clear();
		UIMain.ladField1.clear();
		UIMain.lcxField1.clear();
		UIMain.racField1.clear();
		UIMain.pdaField1.clear();
	}
	
	//Creates the File for new Patients.
	public static void createUniqueIdFile(String patientId) {
	    try {
	        File directory = new File("Patient ID Info");
	        if (!directory.exists()) {
	            directory.mkdir();
	        }

	        File newFile = new File(directory.getPath() + "/Patient ID Record.txt");
	        boolean appendMode = newFile.length() > 0;

	        BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(newFile, appendMode));

//	        if (appendMode) {
//	            bufferWriter.write("\n" + patientId);
//	        } else {
//	        }

	        bufferWriter.write(patientId + "\n");
	        bufferWriter.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//Check if folder is empty
	public static boolean isFolderEmpty(String folderPath) {
	    File folder = new File(folderPath);
	    if (folder.exists() && folder.isDirectory()) {
	        return folder.list().length == 0;
	    } else {
	        System.out.println("Folder does not exist or is not a directory");
	        return true; // Consider non-existing folders as empty
	    }
	}
	
	//checks if the patient ID is in the File/Database.
	public static boolean checkIdInFile (String patientId) {
		
		try {
			
			//Check if the id is present in the database
			File directory = new File("Patient ID Info");
			if (!isFolderEmpty(directory.getPath())) {
				
				try (BufferedReader bufferReader = new BufferedReader(new FileReader(directory.getPath() + "/Patient ID Record.txt"))) {
					String line;
					while((line = bufferReader.readLine())!= null) {
						if (patientId.equals(line)) {
							return true;
						}
					}
					
//					bufferReader.close();
				}
				
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return false;
		
	}
	
	// 1. Function to generate a random 5 digit number.
	public static int generateNumber() {
		
		int upperBound = 90000, lowerBound = 10000;
		int rand = (int)(Math.random()*((upperBound - lowerBound))+lowerBound);
		
		return rand;
		
	}
	
	// 2. Create a new file and Folder if not created for new Patient Information.
	
	public static void createNewPatientFile() {
		
		try {
			
			String patientId = patientInfo[0];
			createUniqueIdFile(patientId);
			
			File directory = new File("Patient Information");
			if (! directory.exists()){
		
				directory.mkdir();
			    
			}
			BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(directory.getPath() + "/" + patientId+ "_PatientInfo.txt"));
			
			
			for (String patientInfoItem: patientInfo) {
				
				bufferWriter.write(patientInfoItem + "\n");
				
			}
			
			bufferWriter.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Creates a new file and Folder if not created for storing the patient's data
	public static void createNewPatientpatientDataFile() {
		
		try {
			
					
			String patientId = patientDataInfo[0];
			
			if (!patientId.matches("\\d+")) {
		        throw new IllegalArgumentException("Patient ID must be a valid integer");
		    }

//		    int patientIdInt = Integer.parseInt(patientId);
			
			//Checks if the patientI is present in the database.
			if (!checkIdInFile(patientId)) {
				
				Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Sorry!");
		        alert.setHeaderText(null);
		        alert.setContentText("There is no user with this patient ID");
	
		        alert.showAndWait();
		        
			} else {
				
				File directory = new File("Patient Data");
				if (! directory.exists()){
				    
					directory.mkdir();
				    
				}
				
				BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(directory.getPath() + "/" + patientId+ "CTResults.txt"));
				
				for (String patientData: patientDataInfo) {
					bufferWriter.write(patientData + "\n");
				}
				
				bufferWriter.close();
				
			}
					
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Checks if we have the right file for the patient when the Patient wants to view their data.
	public static boolean rightFile(File file, String patientId) {
		
		String patientFileId;
		String patientFileName = file.getName();
		
		if (patientFileName.length() >= 5) {
			patientFileId = patientFileName.substring(0, 5);
		} else {
			patientFileId = patientFileName;
		}
		
		if (patientFileId.equals(patientId)) {
			return true;
		}
		
		return false;
	}
	
	//Show s the patient's data that was recorded during the CT Scan. 
	public static boolean showPatientData(String patientId) {
		
		//This part is for the getting the Patient Name
		
		File directory = new File("Patient Information");
		// The patient Id is in the database and the data should be there as well.
		File folder = new File(directory.getPath());
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	
		    	if (rightFile(file, patientId)) {
		    		
		    		try {
		    			BufferedReader reader = new BufferedReader(new FileReader(file));
		    			
		    			String line;
		    			int i=0;
		    			
		    			while((line = reader.readLine())!=null) {
		    				if (i >= patientInfo.length) {
		    			        // Array is full, can't add more data
		    			        break;
		    			    }
		    				patientInfo[i] = line;
		    				i++;
		    			}
		    			UIMain.pageLabel.setText("Hello " + patientInfo[1]);
		    			reader.close();
		    			
					} catch (IOException e) {
						e.printStackTrace();
					}
		    		
		    		
		    	}
		    	
		    }
		
		}
		
		//This part is for getting the Patient Data.
		File directory1 = new File("Patient Data");
		// The patient Id is in the database and the data should be there as well.
		File folder1 = new File(directory1.getPath());
		File[] listOfFiles1 = folder1.listFiles();

		for (File file : listOfFiles1) {
		    if (file.isFile()) {
		    	
		    	if (rightFile(file, patientId)) {
		    		
		    		try {
		    			BufferedReader reader = new BufferedReader(new FileReader(file));
		    			
		    			String line;
		    			int i=0;
		    			
		    			while((line = reader.readLine())!=null) {
		    				if (i >= patientDataInfo.length) {
		    			        // Array is full, can't add more data
		    			        break;
		    			    }
		    				patientDataInfo[i] = line;
		    				i++;
		    			}
		    			
		    			UIMain.totalCACScoreField2.setText(patientDataInfo[1]);
		    			UIMain.lmField2.setText(patientDataInfo[2]);
		    			UIMain.ladField2.setText(patientDataInfo[3]);
		    			UIMain.lcxField2.setText(patientDataInfo[4]); 
		    			UIMain.racField2.setText(patientDataInfo[5]);
		    			UIMain.pdaField2.setText(patientDataInfo[6]);
		    			
		    			reader.close();
		    			
		    			
					} catch (IOException e) {
						e.printStackTrace();
					}
		    		
		    		
		    	}
		    	
		    }
		
		}
		return true;
		
	}
}
