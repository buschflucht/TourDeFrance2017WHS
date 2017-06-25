package tourDeFrance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import tourDeFrance.DBFunctions;

public class CreateDatabaseController {

	@FXML
	private Button btnCloseCreateDB;
	@FXML
	private Button btnCreateDB;
	
	@FXML
	public void closeCreateDB() {
		Stage stage = (Stage) btnCloseCreateDB.getScene().getWindow();
		stage.close();
	}
	@FXML
	public void createDB(){
		String dummy = "";
		dummy = DBFunctions.createDb();

		Alert alert = new Alert(AlertType.INFORMATION);

		if(dummy == "succeed"){
			alert.setTitle("Erfolgreiche Verbindung");
			alert.setHeaderText(null);
			alert.setContentText("Erfolgreich die Datenbank tourdefrance2017 erstellt!");

			alert.showAndWait();
		}
		else{
			alert.setTitle("Fehlgeschlagen");
			alert.setHeaderText(null);
			alert.setContentText("Erstellung der Datenbank tourdefrance2017 fehlgeschlagen");

			alert.showAndWait();
		}
	}
	
	
	
	
	
	
}
