package hw4_application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	//The attributes that will be accessed throughout the package.
	public UIMain theMainUI; 
	public static Stage myStage;
	public static Scene mainScene;
	public static Scene patientIntakeScene;
	public static Scene ctScanScene;
	public static Scene patientIdScene;
	public static Scene patientDetailsScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			myStage = primaryStage;
			BorderPane root = new BorderPane();
			
			mainScene = UIMain.createMainScene(root);
			patientIntakeScene = UIMain.createPatientIntakeScene();
			ctScanScene = UIMain.createCTtScanScene();
			patientIdScene = UIMain.createPatientIDScene();
			patientDetailsScene = UIMain.createPatientDetailsScene();
			
			mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			myStage.setScene(mainScene);
			myStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void switchScene(Scene s) {
		
		myStage.setScene(s);
		
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
