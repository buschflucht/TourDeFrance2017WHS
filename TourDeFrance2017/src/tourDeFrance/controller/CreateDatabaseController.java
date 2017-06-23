package tourDeFrance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
		DBFunctions.createDb();
	}
	
	
	
	
	
	
}
