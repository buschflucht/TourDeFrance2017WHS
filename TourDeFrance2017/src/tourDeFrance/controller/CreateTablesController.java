package tourDeFrance.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
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
		String dummy = "";
		dummy = DBFunctions.tabellenAnlegen();

		Alert alert = new Alert(AlertType.INFORMATION);

		if(dummy == "succeed"){
			alert.setTitle("Erfolgreiche Erstellung");
			alert.setHeaderText(null);
			alert.setContentText("Erfolgreich alle Tabellen in der Datenbank 'tourdefrance2017' erstellt!");

			alert.showAndWait();
		}
		else{
			alert.setTitle("Fehlgeschlagen");
			alert.setHeaderText(null);
			alert.setContentText("Tabellen konnten nicht angelegt werden!");

			alert.showAndWait();
		}
	}
	
	
	
}
