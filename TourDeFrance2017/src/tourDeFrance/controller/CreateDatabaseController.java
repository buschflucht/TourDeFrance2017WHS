package tourDeFrance.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tourDeFrance.DBFunctions;

public class CreateDatabaseController implements Initializable {

	@FXML
	private Button btnCloseCreateDB;
	@FXML
	private Button btnCreateDB;
	@FXML
	private TextField txtDataBaseName;

	@FXML
	public void closeCreateDB() {
		MainMenuController.getInstance().closeTab();
	}

	@FXML
	public void createDB() {
		String dbCreate = "";
		try {
			dbCreate = DBFunctions.getInstance().createDb(txtDataBaseName.getText());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Alert alert = new Alert(AlertType.INFORMATION);

		if (dbCreate == "succeed") {
			alert.setTitle("Successful Connection");
			alert.setHeaderText(null);
			alert.setContentText("Database " + txtDataBaseName.getText() + " created!");
			alert.showAndWait();
			MainMenuController.getInstance().openLogin();
			closeCreateDB();
		} else {
			alert.setTitle("Fehlgeschlagen");
			alert.setHeaderText(null);
			alert.setContentText("Creation of " + txtDataBaseName.getText() + " failed");

			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtDataBaseName.setText(DBFunctions.getInstance().getDatabaseName());
	}

}
