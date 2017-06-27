package tourDeFrance.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.FileChooser;

public class EtappenController implements Initializable{

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

	final FileChooser fileChooser = new FileChooser();
	private Desktop desktop = Desktop.getDesktop();

	@FXML
	public void closeEtappenTable() {
		MainMenuController.getInstance().closeTab();
	}
	
	@FXML
	public void refreshEtappenTable(){

	}
	
	@FXML
	public void importEtappenTable(){
		configureFileChooser(fileChooser);
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			try {
				desktop.open(file);
			} catch (IOException e) {e.printStackTrace();}
		}
	}

	/**
	 * Filechooser wird configuriert die directory vom filepath wird gesetzt
	 * 
	 * @param fileChooser 
	 */
	private static void configureFileChooser(final FileChooser fileChooser){                           
		fileChooser.setTitle("View Pictures");
		File recordsDir = new File(System.getProperty("user.home"), 
				"git/TourDeFrance2017WHS/TourDeFrance2017/resources");
		
//		if (! recordsDir.exists()) {
//		    recordsDir.mkdirs();
//		}
		
		fileChooser.setInitialDirectory(recordsDir); 
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}

}
