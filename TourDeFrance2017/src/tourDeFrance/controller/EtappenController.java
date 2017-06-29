package tourDeFrance.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tourDeFrance.DBFunctions;
import tourDeFrance.model.Etappe;
import tourDeFrance.model.EtappenDAO;

public class EtappenController {

	@FXML
	private TableColumn<Etappe, String> colEtappe;
	@FXML
	private TableColumn<Etappe, String> colDatum;
	@FXML
	private TableColumn<Etappe, String> colUhrzeit;
	@FXML
	private TableColumn<Etappe, String> colStartort;
	@FXML
	private TableColumn<Etappe, String> colZielort;
	@FXML
	private TableColumn<Etappe, String> colLaenge;
	@FXML
	private TableColumn<Etappe, String> colEtappenart;
	@FXML
	private TableView<Etappe> tbview;

	private MainMenuController main;

	@FXML
	private Button btnRefreshEtappenTable;
	@FXML
	private Button btnCloseEtappenTable;


	@FXML
	public void closeEtappenTable() {
		MainMenuController.getInstance().closeTab();
	}

	@FXML
	public void refreshEtappenTable() {

	}

//	@FXML
//	public void importEtappenTable() {
//		Alert alert = new Alert(AlertType.CONFIRMATION);
//		alert.setTitle("");
//		alert.setHeaderText("Choose wisely");
//		alert.setContentText("Choose import");
//
//		ButtonType buttonTestData = new ButtonType("TestData");
//		ButtonType buttonRealData = new ButtonType("RealData");
//		ButtonType buttonCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
//
//		alert.getButtonTypes().setAll(buttonTestData, buttonRealData, buttonCancel);
//		Optional<ButtonType> result = alert.showAndWait();
//		if (result.get() == buttonTestData) {
//			// DBFunctions.getInstance().datenEingeben();
//			// DBFunctions.getInstance().datenEingebenAuswahl("testdaten");
////			try {
//////				populateEtappen(EtappenDAO.selectEtappen());
////			} catch (SQLException e) {
////				e.printStackTrace();
////			}
//		} else if (result.get() == buttonRealData) {
//			// DBFunctions.getInstance().datenEingebenAuswahl("test");
//		} else {
//
//		}
//	}

	@FXML
	public void initialize() {

		colEtappe.setCellValueFactory(cellData -> cellData.getValue().etappenIDProperty().asString());
		colStartort.setCellValueFactory(cellData -> cellData.getValue().startOrtProperty());
		colZielort.setCellValueFactory(cellData -> cellData.getValue().zielOrtProperty());
		colLaenge.setCellValueFactory(cellData -> cellData.getValue().laengeProperty().asString());
		colDatum.setCellValueFactory(cellData -> cellData.getValue().datumProperty());
		colUhrzeit.setCellValueFactory(cellData -> cellData.getValue().zeitProperty());
		colEtappenart.setCellValueFactory(cellData -> cellData.getValue().bezeichnungProperty());
		try {
			populateEtappen(EtappenDAO.selectEtappen());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void populateEtappen(List<Etappe> etappen) {
		ObservableList<Etappe> etappenData = FXCollections.observableArrayList();
		etappenData.addAll(etappen);
		tbview.setItems(etappenData);
	}

}
