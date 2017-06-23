package tourDeFrance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EtappenController {

	@FXML
	private Button btnCloseEtappenPlan;

	@FXML
	public void closeEtappenTable() {
		Stage stage = (Stage) btnCloseEtappenPlan.getScene().getWindow();
		stage.close();
	}
	
	
	
	
	
}
