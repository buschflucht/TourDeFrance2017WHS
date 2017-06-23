package tourDeFrance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tourDeFrance.DBFunctions;

public class CreateTablesController {
	
	@FXML
	private Button btnCloseCreateTables;
	@FXML
	private Button btnCreaeTables;

	@FXML
	public void closeCreateTables() {
		Stage stage = (Stage) btnCloseCreateTables.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void createTables(){
		DBFunctions.tabellenAnlegen();
	}
	
	
	
}
