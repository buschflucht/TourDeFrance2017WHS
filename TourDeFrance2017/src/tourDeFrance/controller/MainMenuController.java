package tourDeFrance.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tourDeFrance.DBFunctions;
import tourDeFrance.Main;

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
	@FXML
	private Button btnImportCsv;

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

	@FXML
	public void pressErgebnisseEinlesen() {

	}

	@FXML
	public void importCsv() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("");
		alert.setHeaderText("Choose how to import your .csv Data");
		alert.setContentText("TestData or RealData");

		ButtonType buttonTestData = new ButtonType("TestData");
		ButtonType buttonRealData = new ButtonType("RealData");
		ButtonType buttonCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTestData, buttonRealData, buttonCancel);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTestData) {
			String data1 = "";
			String data2 = "";
			String data3 = "";
			String data4 = "";
			String data5 = "";

			DBFunctions.getInstance().ergebnisseEingeben();
			data1 = DBFunctions.getInstance()
					.datenEinlesen("./resources/Testdaten_User_Etappen_Teams_Fahrer_Tipps2016/user2016.csv", "user");
			data2 = DBFunctions.getInstance()
					.datenEinlesen("./resources/Testdaten_User_Etappen_Teams_Fahrer_Tipps2016/teams2016.csv", "teams");
			data3 = DBFunctions.getInstance().datenEinlesen(
					"./resources/Testdaten_User_Etappen_Teams_Fahrer_Tipps2016/fahrer2016.csv", "fahrer");
			data4 = DBFunctions.getInstance().datenEinlesen(
					"./resources/Testdaten_User_Etappen_Teams_Fahrer_Tipps2016/etappen2016.csv", "etappen");
			data5 = DBFunctions.getInstance()
					.datenEinlesen("./resources/Testdaten_User_Etappen_Teams_Fahrer_Tipps2016/tipps2016.csv", "tipps");
			messageDialogImport(data1, data2, data3, data4, data5);
		} else if (result.get() == buttonRealData) {
			DBFunctions.getInstance()
					.datenEinlesen("./resources/Testdaten_User_Etappen_Teams_Fahrer_Tipps2016/user2016.csv", "user");
			DBFunctions.getInstance()
					.datenEinlesen("./resources/Testdaten_User_Etappen_Teams_Fahrer_Tipps2016/user2016.csv", "teams");
			DBFunctions.getInstance()
					.datenEinlesen("./resources/Testdaten_User_Etappen_Teams_Fahrer_Tipps2016/user2016.csv", "fahrer");
			DBFunctions.getInstance()
					.datenEinlesen("./resources/Testdaten_User_Etappen_Teams_Fahrer_Tipps2016/user2016.csv", "etappen");
			DBFunctions.getInstance()
					.datenEinlesen("./resources/Testdaten_User_Etappen_Teams_Fahrer_Tipps2016/user2016.csv", "user");
			DBFunctions.getInstance()
					.datenEinlesen("./resources/Testdaten_User_Etappen_Teams_Fahrer_Tipps2016/user2016.csv", "tipps");
		} else {

		}
	}

	public void messageDialogImport(String a, String b, String c, String d, String e) {
		Alert alert = new Alert(AlertType.INFORMATION);
		if (a == "succeed" && b == "succeed" && c == "succeed" && d == "succeed" && e == "succeed") {
			alert.setTitle("Import successful");
			alert.setHeaderText(null);
			alert.setContentText("Data was successfully imported");
			alert.showAndWait();
		} else {
			alert.setTitle("Import failed");
			alert.setHeaderText(null);
			alert.setContentText("Data could not be imported");

			alert.showAndWait();
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
		try {
			DBFunctions.getInstance().closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		btnCreateDB.setVisible(false);
		btnCreateTables.setVisible(false);
		btnErgebnisseAusgeben.setVisible(false);
		btnErgebnisseEingeben.setVisible(false);
		btnShowEtappen.setVisible(false);
		btnShowRanking.setVisible(false);
		btnCreateRanking.setVisible(false);
		btnImportCsv.setVisible(false);
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
		btnImportCsv.setVisible(ok);
	}

}
