package tourDeFrance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import tourDeFrance.DBFunctions;

public class CreateDatabaseController {

	@FXML
	private Button btnCloseCreateDB;
	@FXML
	private Button btnCreateDB;

	@FXML
	public void closeCreateDB() {
		MainMenuController.getInstance().closeTab();
	}

	@FXML
	public void createDB() {
		String dummy = "";
		dummy = DBFunctions.getInstance().createDb();

		Alert alert = new Alert(AlertType.INFORMATION);

		if (dummy == "succeed") {
			alert.setTitle("Erfolgreiche Verbindung");
			alert.setHeaderText(null);
			alert.setContentText("Datenbank tourdefrance2017 erstellt!");
			alert.showAndWait();
			MainMenuController.getInstance().openLogin();
			closeCreateDB();
		} else {
			alert.setTitle("Fehlgeschlagen");
			alert.setHeaderText(null);
			alert.setContentText("Erstellung Datenbank tourdefrance2017 fehlgeschlagen");

			alert.showAndWait();
		}
	}

}
