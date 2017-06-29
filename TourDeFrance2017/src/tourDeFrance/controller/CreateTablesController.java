package tourDeFrance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import tourDeFrance.DBFunctions;

public class CreateTablesController {

	@FXML
	private Button btnCloseCreateTables;
	@FXML
	private Button btnCreaeTables;

	@FXML
	public void closeCreateTables() {
		MainMenuController.getInstance().closeTab();
	}

	@FXML
	public void createTables() {
		String dummy = "";
		dummy = DBFunctions.getInstance().tabellenAnlegen();

		Alert alert = new Alert(AlertType.INFORMATION);

		if (dummy == "succeed") {
			alert.setTitle("Erfolgreiche Erstellung");
			alert.setHeaderText(null);
			alert.setContentText("Erfolgreich alle Tabellen in der Datenbank 'tourdefrance2017' erstellt!");
			alert.showAndWait();
			MainMenuController.getInstance().closeTab();
		} else {
			alert.setTitle("Fehlgeschlagen");
			alert.setHeaderText(null);
			alert.setContentText("Tabellen konnten nicht angelegt werden!");

			alert.showAndWait();
		}
	}

}
