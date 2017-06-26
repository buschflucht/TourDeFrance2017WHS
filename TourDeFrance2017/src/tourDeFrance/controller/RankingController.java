package tourDeFrance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RankingController {

	@FXML
	private Button btnCloseRanking;

	@FXML
	public void closeShowRanking() {
		MainMenuController.getInstance().closeTab();
	}

}
