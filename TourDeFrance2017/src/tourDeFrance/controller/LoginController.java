package tourDeFrance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tourDeFrance.DBFunctions;

public class LoginController {

	@FXML
	private Button btnLocal;
	@FXML
	private Button btnManual;
	@FXML
	private Button btnLive;
	@FXML
	private Button btnLocalDB;
	@FXML
	private Button btnManualDB;
	@FXML
	private Button btnLiveDB;
	@FXML
	private Button btnClear;
	@FXML
	private Button btnClearDB;
	@FXML
	private TextField txtIp;
	@FXML
	private TextField txtPort;
	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPW;
	@FXML
	private TextField txtIpDB;
	@FXML
	private TextField txtPortDB;
	@FXML
	private TextField txtUserDB;
	@FXML
	private PasswordField txtPWDB;
	@FXML
	private TextField txtDatabase;
	@FXML
	private CheckBox chboxManualDB;
	@FXML
	private CheckBox chboxManual;
	@FXML
	private Button btnCloseLogin;

	@FXML
	public void connectLocal() {

		String rt = "";
		rt = DBFunctions.getInstance().connectLocal();

		Alert alert = new Alert(AlertType.INFORMATION);

		if (rt == "Connection succeed") {
			alert.setTitle("Success");
			alert.setHeaderText("Successful Connection");
			alert.setContentText("Connected to Local-Server!");
			alert.showAndWait();
			Stage stage = (Stage) btnLocal.getScene().getWindow();
			String login = DBFunctions.getInstance().getAktuelleConnection();
			MainMenuController control = MainMenuController.getInstance();
			control.updateMenu(login);
			stage.close();
		} else {
			alert.setTitle("Hoppala");
			alert.setHeaderText("Hoppala da laeuft was falsch");
			alert.setContentText("Connection to Local-Server failed!");
			alert.showAndWait();
		}
	}

	@FXML
	public void connectLocalDB() {
		String rt = "";
		rt = DBFunctions.getInstance().connectLocalDB();

		Alert alert = new Alert(AlertType.INFORMATION);

		if (rt == "Connection succeed") {
			alert.setTitle("Success");
			alert.setHeaderText("Succesful Connection");
			alert.setContentText("Connected with Local DataBase!");
			alert.showAndWait();
			Stage stage = (Stage) btnLocalDB.getScene().getWindow();
			String login = DBFunctions.getInstance().getAktuelleConnection();
			MainMenuController control = MainMenuController.getInstance();
			control.updateMenuDB(login);
			stage.close();
		} else {
			alert.setTitle("FAIL");
			alert.setHeaderText("Hoppala da laeuft was falsch");
			alert.setContentText("Connection with Local DB 'tourdefrance2017' failed - "
					+ "Database tourdefrance2017 doesn't exist");
			alert.showAndWait();
		}

	}

	@FXML
	public void connectLive() {

		String rt = "";
		rt = DBFunctions.getInstance().connectLIVE();

		Alert alert = new Alert(AlertType.INFORMATION);

		if (rt == "Connection succeed") {
			alert.setTitle("Success");
			alert.setHeaderText("Succesful Connection");
			alert.setContentText("Connected to Live-Server!");
			alert.showAndWait();
			Stage stage = (Stage) btnLive.getScene().getWindow();
			String login = DBFunctions.getInstance().getAktuelleConnection();
			MainMenuController control = MainMenuController.getInstance();
			control.updateMenu(login);
			stage.close();
		} else {
			alert.setTitle("FAIL");
			alert.setHeaderText("Hoppala da laeuft was falsch");
			alert.setContentText("Connection to Live-Server failed");
			alert.showAndWait();
		}
	}

	@FXML
	public void connectLiveDB() {

		String rt = "";
		rt = DBFunctions.getInstance().connectLIVEDB();

		Alert alert = new Alert(AlertType.INFORMATION);

		if (rt == "Connection succeed") {
			alert.setTitle("Success");
			alert.setHeaderText("Succesful Connection");
			alert.setContentText("Connected with Live-Database!");
			alert.showAndWait();
			Stage stage = (Stage) btnLiveDB.getScene().getWindow();
			String login = DBFunctions.getInstance().getAktuelleConnection();
			MainMenuController control = MainMenuController.getInstance();
			control.updateMenuDB(login);
			stage.close();
		} else {
			alert.setTitle("FAIL");
			alert.setHeaderText("Hoppala da laeuft was falsch");
			alert.setContentText("Connection with Live DB 'tourdefrance2017' failed");
			alert.showAndWait();
		}
	}

	@FXML
	public void connectManual() {

		if (txtIp.getText().isEmpty() || txtPort.getText().isEmpty() || txtUser.getText().isEmpty()
				|| txtPW.getText().isEmpty()) {

			Alert alert2 = new Alert(AlertType.WARNING);
			alert2.setTitle("Achtung");
			alert2.setHeaderText(null);
			alert2.setContentText("Bitte alle Felder ausfuellen!");
			alert2.showAndWait();

		} else {

			String rt = "";
			rt = DBFunctions.getInstance().connect(txtIp.getText(), txtPort.getText(), txtUser.getText(),
					txtPW.getText());
			Alert alert = new Alert(AlertType.INFORMATION);

			if (rt == "Connection succeed") {
				alert.setTitle("Success");
				alert.setHeaderText("Successful Connection");
				alert.setContentText("Connected to Local-Server!");
				alert.showAndWait();
				Stage stage = (Stage) btnManual.getScene().getWindow();
				String login = DBFunctions.getInstance().getAktuelleConnection();
				MainMenuController control = MainMenuController.getInstance();
				control.updateMenu(login);
				stage.close();

			} else {
				alert.setTitle("Fehlgeschlagen");
				alert.setHeaderText("Oops");
				alert.setContentText("Connection failed. Check UserInput!");
				alert.showAndWait();
			}
		}

	}

	@FXML
	public void connectManualDB() {

		if (txtIpDB.getText().isEmpty() || txtPortDB.getText().isEmpty() || txtUserDB.getText().isEmpty()
				|| txtPWDB.getText().isEmpty() || txtDatabase.getText().isEmpty()) {

			Alert alert2 = new Alert(AlertType.WARNING);
			alert2.setTitle("Achtung");
			alert2.setHeaderText(null);
			alert2.setContentText("Bitte alle Felder ausfuellen!");
			alert2.showAndWait();

		} else {

			String rt = "";
			rt = DBFunctions.getInstance().connectDB(txtIpDB.getText(), txtPortDB.getText(), txtUserDB.getText(),
					txtPWDB.getText(), txtDatabase.getText());
			Alert alert = new Alert(AlertType.INFORMATION);

			if (rt == "Connection succeed") {
				alert.setTitle("Success");
				alert.setHeaderText("Succesful Connection");
				alert.setContentText(
						"Connected to " + txtDatabase.getText() + " !");
				alert.showAndWait();
				Stage stage = (Stage) btnManualDB.getScene().getWindow();
				String login = DBFunctions.getInstance().getAktuelleConnection();
				MainMenuController control = MainMenuController.getInstance();
				control.updateMenuDB(login);
				stage.close();

			} else {
				alert.setTitle("Failed");
				alert.setHeaderText("Oops");
				alert.setContentText("Connection to " + txtDatabase.getText()
						+ " failed. Check UserInput");
				alert.showAndWait();
			}
		}

	}

	@FXML
	public void clearTxt() {
		txtIp.clear();
		txtPort.clear();
		txtUser.clear();
		txtPW.clear();
	}

	@FXML
	public void clearTxtDB() {
		txtIpDB.clear();
		txtPortDB.clear();
		txtUserDB.clear();
		txtPWDB.clear();
	}

	@FXML
	public void select() {
		if (chboxManual.isSelected()) {
			txtIpDB.setDisable(true);
			txtPortDB.setDisable(true);
			txtUserDB.setDisable(true);
			txtPWDB.setDisable(true);
			btnManualDB.setDisable(true);
			btnClearDB.setDisable(true);
			txtDatabase.setDisable(true);
			txtIp.setDisable(false);
			txtPort.setDisable(false);
			txtUser.setDisable(false);
			txtPW.setDisable(false);
			btnManual.setDisable(false);
			btnClear.setDisable(false);
			chboxManual.setSelected(true);
			chboxManualDB.setSelected(false);
		}
	}

	@FXML
	public void selectDB() {
		if (chboxManualDB.isSelected()) {
			txtIp.setDisable(true);
			txtPort.setDisable(true);
			txtUser.setDisable(true);
			txtPW.setDisable(true);
			btnManual.setDisable(true);
			btnClear.setDisable(true);
			txtIpDB.setDisable(false);
			txtPortDB.setDisable(false);
			txtUserDB.setDisable(false);
			txtPWDB.setDisable(false);
			btnManualDB.setDisable(false);
			btnClearDB.setDisable(false);
			txtDatabase.setDisable(true);
			chboxManual.setSelected(false);
			chboxManualDB.setSelected(true);

		}

	}

	@FXML
	public void closeLogin() {
		Stage stage = (Stage) btnCloseLogin.getScene().getWindow();
		stage.close();
	}

}
