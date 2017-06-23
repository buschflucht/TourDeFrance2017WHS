package tourDeFrance.controller;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tourDeFrance.DBFunctions;
import tourDeFrance.Main;

public class MainMenuController {

	public Main main;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnExit;
	@FXML
	private TabPane tbPane;
	@FXML
	private Label lblConnection;

	public void setMain(Main main) {
		this.main = main;

	}

	@FXML
	public void openLogin() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("view/Login.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.setTitle("Establish a connection");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void openCreateDB() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("view/CreateDatabase.fxml"));
			AnchorPane anchor = (AnchorPane) fxmlLoader.load();
			Tab tb = new Tab("Create Database", anchor);
			tbPane.getTabs().add(tb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void openCreateTables() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("view/CreateTables.fxml"));
			AnchorPane anchor = (AnchorPane) fxmlLoader.load();
			Tab tb = new Tab("Create Tables", anchor);
			tbPane.getTabs().add(tb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void openEtappenPlan() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("view/DataEtappen.fxml"));
			AnchorPane anchor = (AnchorPane) fxmlLoader.load();
			Tab tb = new Tab("Etappenplan", anchor);
			tbPane.getTabs().add(tb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void openErgebnisseAusgeben() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("view/ErgebnisseAusgeben.fxml"));
			AnchorPane anchor = (AnchorPane) fxmlLoader.load();
			Tab tb = new Tab("beendete Ergebnisse", anchor);
			tbPane.getTabs().add(tb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void openShowRanking() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("view/ShowRanking.fxml"));
			AnchorPane anchor = (AnchorPane) fxmlLoader.load();
			Tab tb = new Tab("show Ranking", anchor);
			tbPane.getTabs().add(tb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void updateLabel() {
		lblConnection.setText(DBFunctions.getAktuelleConnection());
	}

	@FXML
	public void closeApp() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Programm beenden");
		alert.setHeaderText("Sie sind dabei das Programm zu schlieﬂen");
		alert.setContentText("Bist du dir sicher?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Platform.exit();
		} else {

		}

	}

}
