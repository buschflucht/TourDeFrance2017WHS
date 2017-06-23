package tourDeFrance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RankingController {
	
	
	@FXML
	private Button btnCloseRanking;

	@FXML
	public void closeShowRanking() {
		Stage stage = (Stage) btnCloseRanking.getScene().getWindow();
		stage.close();
	}

}
