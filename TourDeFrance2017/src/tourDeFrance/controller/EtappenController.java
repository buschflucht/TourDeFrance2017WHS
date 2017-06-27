package tourDeFrance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tourDeFrance.Main;
import tourDeFrance.model.Etappen;

public class EtappenController{

		@FXML
		private TableColumn<Etappen, String> colEtappe;
		@FXML
		private TableColumn<Etappen, String> colDatum;
		@FXML
		private TableColumn<Etappen, String> colUhrzeit;
		@FXML
		private TableColumn<Etappen, String> colStartort;
		@FXML
		private TableColumn<Etappen, String> colZielort;
		@FXML
		private TableColumn<Etappen, String> colLaenge;
		@FXML
		private TableColumn<Etappen, String> colEtappenart;
		@FXML
		private TableView<Etappen> tbview;
		
		private MainMenuController main;


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
	@FXML
	public void initialize() {
			
	colEtappe.setCellValueFactory(cellData -> cellData.getValue().getEtappenID().asString());
	colStartort.setCellValueFactory(cellData -> cellData.getValue().getStartort());
	colZielort.setCellValueFactory(cellData -> cellData.getValue().getZielort());
	colLaenge.setCellValueFactory(cellData -> cellData.getValue().getLaenge().asString());
	   
	}
	
	 /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainMenuController main) {
        this.main = main;
	
        // Add observable list data to the table
        tbview.setItems(main.getEtappenData());
    }
    
    
   
}
