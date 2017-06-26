package tourDeFrance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EtappenController {

	@FXML
	private Button btnCloseEtappenPlan;

	@FXML
	public void closeEtappenTable() {
		MainMenuController.getInstance().closeTab();
	}

}
