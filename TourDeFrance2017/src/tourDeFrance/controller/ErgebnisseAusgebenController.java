package tourDeFrance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tourDeFrance.model.Etappe;

public class ErgebnisseAusgebenController {

	@FXML
	private TableColumn<Etappe, String> colEtappenNummer;
	@FXML
	private TableColumn<Etappe, String> colDatum;
	@FXML
	private TableColumn<Etappe, String> colEtappenSieger;
	@FXML
	private TableColumn<Etappe, String> colSiegerTeam;
	@FXML
	private TableColumn<Etappe, String> colSiegerZeit;
	@FXML
	private TableView<Etappe> tbview;
	@FXML
	private Button btnCloseErgebnisse;

	@FXML
	public void closeErgebnisseAusgeben() {
		MainMenuController.getInstance().closeTab();
	}

	// @FXML
	// public void initialize() {
	//
	// colEtappenNummer.setCellValueFactory(cellData ->
	// cellData.getValue().etappenIDProperty().asString());
	// colEtappenSieger.setCellValueFactory(cellData ->
	// cellData.getValue().startOrtProperty());
	// colSiegerTeam.setCellValueFactory(cellData ->
	// cellData.getValue().zielOrtProperty());
	// colSiegerZeit.setCellValueFactory(cellData ->
	// cellData.getValue().laengeProperty().asString());
	// colDatum.setCellValueFactory(cellData ->
	// cellData.getValue().datumProperty());
	//
	// }
	//
	//
	//
	// private void populateErgebnisse(List<Etappe> etappen){
	// ObservableList<Etappe> ergebnisData = FXCollections.observableArrayList();
	// ergebnisData.addAll(etappen);
	// tbview.setItems(ergebnisData);
	// }
	//

}
