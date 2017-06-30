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
		String tableCreate = "";
		tableCreate = DBFunctions.getInstance().tabellenAnlegen();
		String dataBaseName = DBFunctions.getInstance().getDatabaseName();
		Alert alert = new Alert(AlertType.INFORMATION);

		if (tableCreate == "succeed") {
			alert.setTitle("Success");
			alert.setHeaderText(null);
			alert.setContentText("Created all Tables in " + dataBaseName + "!");
			alert.showAndWait();
			MainMenuController.getInstance().closeTab();
		} else {
			alert.setTitle("Failed");
			alert.setHeaderText(null);
			alert.setContentText("Tables could not be created!");

			alert.showAndWait();
		}
	}

}
