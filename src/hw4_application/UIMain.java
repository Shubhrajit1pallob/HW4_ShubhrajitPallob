package hw4_application;

import java.util.ArrayList;

import hw4_application.FileHandeling;
import javafx.scene.control.TextField;
import hw4_application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class UIMain {
	
	/**********************************************************************************************

		Attributes
	
	**********************************************************************************************/
	
	
//	public static String patientID;
//	static String [] patientInfo;
	static ArrayList<Integer> uniqueIdlist = new ArrayList<>();
	
	//The Main page UI.
	static Label mainPageLabel, patientIntakePageLabel, ctScanPageLabel, patientViewPageLabel;
	static Button patientIntakeBtn, ctScanTechViewBtn, patientViewBtn;
	
	//Patient Intakes Form UI details.
	static Label firstName, lastName, email, phoneNumber, healthHistory, insuranceId;
	static TextField firstNameField, lastNamefield, emailField, phoneNumbeField, healthHistoryField, insuranceIdField;
	static Button intakeFormsaveBtn;
	
	//CT Scan Form UI.
	static Label patientID1, totalCACScore1, vLACACScore, lm1, lad1, lcx1, rac1, pda1;
	static TextField patientIDField1, totlaCACScoreField1, lmField1, ladField1, lcxField1, racField1, pdaField1;
	static Button ctScansaveBtn;
	
	//Patient View UI
	static Label pageLabel, patientID2, totalCACScore2, lm2, lad2, lcx2, rac2, pda2;
	static TextField patientIDField2, totalCACScoreField2, lmField2, ladField2, lcxField2, racField2, pdaField2;
	static Button closeBtn, nextPage;
	
	
	
	
	
	
	
	/**********************************************************************************************

		This function creates the main page of the program.
	
	**********************************************************************************************/
	
	public static Scene createMainScene(BorderPane root) {
		BorderPane rootPane = root;
		mainPageLabel = createPageLabel("Welcome to Heart Health Imaging and Recording System", root);
		patientIntakeBtn =createButton("Patient Intake");
		
		patientIntakeBtn.setOnAction(e -> {
			Main.switchScene(Main.patientIntakeScene);
		});
		
		ctScanTechViewBtn = createButton("CT Scan Tech View");
		
		ctScanTechViewBtn.setOnAction(e -> {
			Main.switchScene(Main.ctScanScene);
		});
		
		patientViewBtn = createButton("Patient View");
		
		patientViewBtn.setOnAction(e -> {
			Main.switchScene(Main.patientIdScene);
		});
		
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(patientIntakeBtn, ctScanTechViewBtn, patientViewBtn);
		rootPane.setCenter(vbox);
		Scene mainScene = new Scene(rootPane, 700, 500);
		return mainScene;
	}
	
	
	/**********************************************************************************************

	This function creates Patient Intakes Form Page.
	
	**********************************************************************************************/
	
	public static Scene createPatientIntakeScene() {
		
		BorderPane rootPane = new BorderPane();
		patientIntakePageLabel = createPageLabel("Patient Intake Form", rootPane);
		
		firstName = bodyLabel("First Name:");
		lastName = bodyLabel("Last Name:");
		email = bodyLabel("EMail:");
		phoneNumber = bodyLabel("Phone Number:");
		healthHistory = bodyLabel("Health History:");
		insuranceId = bodyLabel("Insurance ID");

		firstNameField = createTextField();
		lastNamefield = createTextField();
		emailField = createTextField();
		phoneNumbeField = createTextField();
		healthHistoryField = createTextField();
		insuranceIdField = createTextField();
		
		
		//setting up a id ArrayList to keep track of patient's records.
		
		GridPane grid = new GridPane();
		grid.add(firstName, 0, 0, 1, 1);
		grid.add(lastName, 0, 1, 1, 1);
		grid.add(email, 0, 2, 1, 1);
		grid.add(phoneNumber, 0, 3, 1, 1);
		grid.add(healthHistory, 0, 4, 1, 1);
		grid.add(insuranceId, 0, 5, 1, 1);
		grid.add(firstNameField, 1, 0, 1, 1);
		grid.add(lastNamefield, 1, 1, 1, 1);
		grid.add(emailField, 1, 2, 1, 1);
		grid.add(phoneNumbeField, 1, 3, 1, 1);
		grid.add(healthHistoryField, 1, 4, 1, 1);
		grid.add(insuranceIdField, 1, 5, 1, 1);
		GridPane.setMargin(grid, new Insets(20));
		
		
		VBox rightBox = new VBox();
		Button saveBtn = createButton("Save");
		rightBox.setAlignment(Pos.BOTTOM_RIGHT);
		rightBox.getChildren().addAll(saveBtn);
		
		rootPane.setLeft(grid);
		rootPane.setBottom(rightBox);
		
		saveBtn.setOnAction(e -> {
			
			//Check if all fields are filled.
			if (firstNameField.getText().isEmpty() || lastNamefield.getText().isEmpty() || emailField.getText().isEmpty() || phoneNumbeField.getText().isEmpty() || healthHistoryField.getText().isEmpty() || insuranceIdField.getText().isEmpty()) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Sorry!");
		        alert.setHeaderText(null);
		        alert.setContentText("All fields must be filled out");

		        alert.showAndWait();
		    } else {
		    	
		    	int id = FileHandeling.generateNumber();
				
				String patientId = String.valueOf(id);
				
				
		    	//Process the data
				FileHandeling.processpatientInfo(patientId);
				
				//Save the file of new user.
				FileHandeling.createNewPatientFile();
				
				//Clear the previous textField
				FileHandeling.clearPatientInfoTextField();

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Patient ID");
				alert.setHeaderText(null);
				alert.setContentText("The Patient Id is: " + patientId);

				alert.showAndWait();
				
				//Switch scene to the main screen and save the info of the Patient.
				Main.switchScene(Main.mainScene);
				
		    }
			
			
			
		});
		
		Scene patientIntakeScene = new Scene(rootPane, 700, 500);
		
		return patientIntakeScene;
		
	}
	
	
	
	/**********************************************************************************************

	This function creates CT Scan Form Page.
	
	**********************************************************************************************/
	
	
	public static Scene createCTtScanScene () {
		
		BorderPane rootPane = new BorderPane();
		GridPane topGrid = new GridPane();
		GridPane centerGrid = new GridPane();
		patientID1 = new Label("Patient ID");
		totalCACScore1 = new Label("The Total Agatston CAC Score");
		vLACACScore = new Label("Vessel Level Agatston CAC Score");
		lm1 = new Label("LM:");
		lad1 = new Label("LAD:");
		lcx1 = new Label("LCX:");
		rac1 = new Label("RAC:");
		pda1 = new Label("PDA:");
		
		patientIDField1 = createTextField();
		totlaCACScoreField1 = createTextField();
		lmField1 = createTextField();
		ladField1 = createTextField();
		lcxField1 = createTextField();
		racField1 = createTextField();
		pdaField1 = createTextField();
		
		ctScansaveBtn = createButton("Save");
		VBox saveBox = new VBox();
		saveBox.getChildren().addAll(ctScansaveBtn);
		saveBox.setAlignment(Pos.BOTTOM_RIGHT);
		
		topGrid.add(patientID1, 0, 0, 1, 1);
		topGrid.add(patientIDField1, 1, 0, 1, 1);
		topGrid.add(totalCACScore1, 0, 1, 1, 1);
		topGrid.add(totlaCACScoreField1, 1, 1, 1, 1);
		topGrid.add(vLACACScore, 0, 2, 1, 1);
		topGrid.setPadding(new Insets(15));
		
		centerGrid.add(lm1, 0, 1, 1, 1);
		centerGrid.add(lmField1, 1, 1, 1, 1);
		centerGrid.add(lad1, 0, 2, 1, 1);
		centerGrid.add(ladField1, 1, 2, 1, 1);
		centerGrid.add(lcx1, 0, 3, 1, 1);
		centerGrid.add(lcxField1, 1, 3, 1, 1);
		centerGrid.add(rac1, 0, 4, 1, 1);
		centerGrid.add(racField1, 1, 4, 1, 1);
		centerGrid.add(pda1, 0, 5, 1, 1);
		centerGrid.add(pdaField1, 1, 5, 1, 1);
		centerGrid.setPadding(new Insets(25));
		
		ctScansaveBtn.setOnAction(e -> {
			
			//Check if all fields are filled.
			if (patientIDField1.getText().isEmpty() || totlaCACScoreField1.getText().isEmpty() || lmField1.getText().isEmpty() || ladField1.getText().isEmpty() || lcxField1.getText().isEmpty() || racField1.getText().isEmpty() || pdaField1.getText().isEmpty()) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Sorry!");
		        alert.setHeaderText(null);
		        alert.setContentText("All fields must be filled out");

		        alert.showAndWait();
		    } else {
		    	
		    	//Process the data
				FileHandeling.processingCTScanData();
				
				//Save the file of new user.
				FileHandeling.createNewPatientpatientDataFile();
				
				//Clears the Previous data in the text field
		    	FileHandeling.clearCTTestField1();
				
				//Switch scene to the main screen and save the info of the Patient.
				Main.switchScene(Main.mainScene);
				
		    }
			
		});
		
		rootPane.setTop(topGrid);
		rootPane.setCenter(centerGrid);
		rootPane.setBottom(saveBox);
		
		return new Scene(rootPane, 700, 500);
		
	}
	
	
	/**********************************************************************************************

		This is a intermediate part where the program asks for the patient ID.
	
	**********************************************************************************************/
	
	public static Scene createPatientIDScene () {
		
		BorderPane rootPane = new BorderPane();
		patientID2 = new Label("Enter Your Patient ID: ");
		patientIDField2 = createTextField();
		nextPage = createButton("Next");
		
		patientID2.setPadding(new Insets(5, 10, 0, 0));
		
		VBox nextBtnBox = new VBox();
		nextBtnBox.getChildren().addAll(nextPage);
		nextBtnBox.setAlignment(Pos.BOTTOM_RIGHT);
		
		HBox stackBox = new HBox();
		stackBox.getChildren().addAll(patientID2, patientIDField2);
		stackBox.setPadding(new Insets(10));
		rootPane.setTop(stackBox);
		rootPane.setBottom(nextBtnBox);
		
		nextPage.setOnAction(e -> {

			String patientId = patientIDField2.getText();
			
			//Check if all fields are filled.
			if (patientIDField2.getText().isEmpty()) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Sorry!");
		        alert.setHeaderText(null);
		        alert.setContentText("All fields must be filled out");

		        alert.showAndWait();
		    } else {
		    	// Disables the TextFields.
		    	totalCACScoreField2.setEditable(false);
		    	lmField2.setEditable(false);
		    	ladField2.setEditable(false);
		    	lcxField2.setEditable(false); 
		    	racField2.setEditable(false);
		    	pdaField2.setEditable(false);
		    	
		    	//Call the show data method.
		    	if (FileHandeling.checkIdInFile(patientId)) {
		    		if (FileHandeling.showPatientData(patientId)) {
		    			
		    			//Switch scene
		    			Main.switchScene(Main.patientDetailsScene);
		    			
		    		} else {
		    			Main.switchScene(Main.mainScene);
		    		}
		    	} else {
		    		Alert alert = new Alert(Alert.AlertType.ERROR);
			        alert.setTitle("Sorry!");
			        alert.setHeaderText(null);
			        alert.setContentText("There is no user with this patient ID");
		
			        alert.showAndWait();
			        
			        patientIDField2.clear();
			        
		    		Main.switchScene(Main.mainScene);
		    	}
		    }
			
		});
		
		return new Scene(rootPane, 700, 500);
		
	}
	
	/**********************************************************************************************

	This function creates Patient View Page.
	
	**********************************************************************************************/
	
	
	public static Scene createPatientDetailsScene () {
		
		BorderPane rootPane = new BorderPane();
		String labelText = "Hello " + patientIDField2.getText();

		pageLabel = createPageLabel(labelText, rootPane);
		pageLabel.setAlignment(Pos.CENTER);
		totalCACScore2 = new Label("The Total Agatston CAC Score");
		lm2 = new Label("LM:");
		lad2 = new Label("LAD:");
		lcx2 = new Label("LCX:");
		rac2 = new Label("RAC:");
		pda2 = new Label("PDA:");
		
		totalCACScoreField2 = createTextField();
		lmField2 = createTextField();
		ladField2 = createTextField();
		lcxField2 = createTextField();
		racField2 = createTextField();
		pdaField2 = createTextField();
		
//		totalCACScoreField2.setPadding(new Insets(0, 0, 0, 10));
		
		closeBtn = createButton("Close");
		VBox closeBtnBox = new VBox();
		closeBtnBox.getChildren().addAll(closeBtn);
		closeBtnBox.setAlignment(Pos.BOTTOM_RIGHT);
		
		GridPane centerGrid = new GridPane();
		
		HBox box = new HBox(15);
		box.getChildren().addAll(totalCACScore2, totalCACScoreField2);
		box.setAlignment(Pos.BASELINE_LEFT);
		box.setPadding(new Insets(10));
		VBox stack = new VBox();
		stack.getChildren().addAll(pageLabel, box);
		stack.setAlignment(Pos.CENTER);
		
		centerGrid.add(lm2, 0, 1, 1, 1);
		centerGrid.add(lmField2, 1, 1, 1, 1);
		centerGrid.add(lad2, 0, 2, 1, 1);
		centerGrid.add(ladField2, 1, 2, 1, 1);
		centerGrid.add(lcx2, 0, 3, 1, 1);
		centerGrid.add(lcxField2, 1, 3, 1, 1);
		centerGrid.add(rac2, 0, 4, 1, 1);
		centerGrid.add(racField2, 1, 4, 1, 1);
		centerGrid.add(pda2, 0, 5, 1, 1);
		centerGrid.add(pdaField2, 1, 5, 1, 1);
		centerGrid.setPadding(new Insets(25));
		
		closeBtn.setOnAction(e -> {
			FileHandeling.clearPatientViewTextField();
			Main.switchScene(Main.mainScene);
		});
		
		rootPane.setTop(stack);
		rootPane.setCenter(centerGrid);
		rootPane.setBottom(closeBtnBox);
		
		return new Scene(rootPane, 700, 500);
		
		
	}
	
	
	
	/**********************************************************************************************

		This function creates a new Button.
	
	**********************************************************************************************/
	
	private static Button createButton(String btnText) {
		
		String blueColor = "-fx-background-color: #4472c5;";
		Button button = new Button(btnText);
		button.setMaxWidth(200);
		button.setMinWidth(150);
		button.setMinHeight(60);
		button.setStyle(blueColor);
		VBox.setMargin(button, new Insets(10));
		
		return button;
		
	}
	
	/**********************************************************************************************

		This function creates a label for the different pages.
	
	**********************************************************************************************/
	
	public static Label createPageLabel (String labelText, BorderPane rootPane) {
		Label pageLabel = new Label(labelText);
		pageLabel.setAlignment(Pos.CENTER);
		pageLabel.setStyle("-fx-font-size: 18pt;");
	    StackPane centerPane = new StackPane(); // StackPane to center the label
	    centerPane.getChildren().add(pageLabel);
	    rootPane.setTop(centerPane);
	    return pageLabel;
	}
	


	
	/**********************************************************************************************

		This function creates a label for the different pages.
	
	 **********************************************************************************************/
	
	public static Label bodyLabel (String labelText) {
		Label bodyLabel = new Label(labelText);
		bodyLabel.setAlignment(Pos.BASELINE_LEFT);
	    return bodyLabel;
	}
	

	/**********************************************************************************************

		This function creates a TextFiel.

	 **********************************************************************************************/
	
	public static TextField createTextField() {
		
		TextField field = new TextField();
		field.setMinWidth(250);
		field.setMaxWidth(300);
//		VBox.setMargin(field, new Insets(10));
		GridPane.setMargin(field, new Insets(10));
		return field;
		
	}
	
	

}
