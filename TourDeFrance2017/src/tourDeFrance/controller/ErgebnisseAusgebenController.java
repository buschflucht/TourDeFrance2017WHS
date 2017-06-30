package tourDeFrance.controller;

import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tourDeFrance.model.Etappe;
import tourDeFrance.model.EtappenDAO;

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

	@FXML
	public void initialize() {

		colEtappenNummer.setCellValueFactory(cellData -> cellData.getValue().etappenNummerProperty().asString());
		colDatum.setCellValueFactory(cellData -> cellData.getValue().datumProperty());
		try {
			populateErgebnisse(EtappenDAO.selectErgebnisse());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	private void populateErgebnisse(List<Etappe> etappen) {
		ObservableList<Etappe> etappenData = FXCollections.observableArrayList();
		etappenData.addAll(etappen);
		tbview.setItems(etappenData);
	}

}
