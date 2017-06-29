package tourDeFrance.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import tourDeFrance.model.Etappe;

public class MainMenuController implements Initializable {

	public Main main;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnLogOut;
	@FXML
	private Button btnExit;
	@FXML
	private TabPane tbPane;
	@FXML
	private Label lblConnection;
	@FXML
	private Button btnCreateDB;
	@FXML
	private Button btnCreateTables;
	@FXML
	private Button btnShowEtappen;
	@FXML
	private Button btnErgebnisseEingeben;
	@FXML
	private Button btnErgebnisseAusgeben;
	@FXML
	private Button btnCreateRanking;
	@FXML
	private Button btnShowRanking;
	
	private static MainMenuController instance;

	
	public static MainMenuController getInstance() {
		return instance;
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
			if (tbPane.getTabs().isEmpty()) {
				tbPane.getTabs().add(tb);

			} else {
				closeTab();
			}

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
			if (tbPane.getTabs().isEmpty()) {
				tbPane.getTabs().add(tb);

			} else {
				closeTab();
			}

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
			if (tbPane.getTabs().isEmpty()) {
				tbPane.getTabs().add(tb);
			} else {
				closeTab();
			}

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
			if (tbPane.getTabs().isEmpty()) {
				tbPane.getTabs().add(tb);

			} else {
				closeTab();
			}

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
			if (tbPane.getTabs().isEmpty()) {
				tbPane.getTabs().add(tb);

			} else {
				closeTab();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateMenu(String string) {
		boolean ok = true;
		lblConnection.setText(string);
		if (DBFunctions.getInstance().getAktuelleConnection().isEmpty()) {
			ok = false;
		}
		setButtonFields(ok);
	}

	public void updateMenuDB(String string) {
		boolean ok = true;
		lblConnection.setText(string);
		if (DBFunctions.getInstance().getAktuelleConnection().isEmpty()) {
			ok = false;
		}
		setButtonFieldsDB(ok);
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

	public void closeTab() {
		tbPane.getTabs().remove(0);
	}

	@FXML
	public void LogOut() {
		DBFunctions.getInstance().closeConnection();
		btnCreateDB.setVisible(false);
		btnCreateTables.setVisible(false);
		btnErgebnisseAusgeben.setVisible(false);
		btnErgebnisseEingeben.setVisible(false);
		btnShowEtappen.setVisible(false);
		btnShowRanking.setVisible(false);
		btnCreateRanking.setVisible(false);
		lblConnection.setText("");
		if (tbPane.getTabs().isEmpty()) {
		} else {
			closeTab();
		}
	}

	private void setButtonFields(boolean ok) {
		btnCreateDB.setVisible(ok);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		instance = this;

	}

	private void setButtonFieldsDB(boolean ok) {
		btnCreateTables.setVisible(ok);
		btnErgebnisseAusgeben.setVisible(ok);
		btnErgebnisseEingeben.setVisible(ok);
		btnShowEtappen.setVisible(ok);
		btnShowRanking.setVisible(ok);
		btnCreateRanking.setVisible(ok);
	}

}
