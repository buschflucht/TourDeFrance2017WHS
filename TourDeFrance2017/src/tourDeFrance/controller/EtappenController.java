package tourDeFrance.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.ColumnConstraints;

public class EtappenController{

	//	@FXML
	//	private TableColumn<etappen, String> colEtappe;
	//	@FXML
	//	private TableColumn<etappen, String> colDatum;
	//	@FXML
	//	private TableColumn<etappen, String> colUhrzeit;
	//	@FXML
	//	private TableColumn<etappen, String> colStartort;
	//	@FXML
	//	private TableColumn<etappen, String> colZielort;
	//	@FXML
	//	private TableColumn<etappen, String> colLaenge;
	//	@FXML
	//	private TableColumn<etappen, String> colEtappenart;


	@FXML
	private Button btnRefreshEtappenTable;	
	@FXML
	private Button btnCloseEtappenTable;
	@FXML
	private Button btnImportEtappenTable;

	@FXML
	public void closeEtappenTable() {
		MainMenuController.getInstance().closeTab();
	}
	
	@FXML
	public void refreshEtappenTable(){

	}
	
	@FXML
	public void importEtappenTable(){
		
	}

}
