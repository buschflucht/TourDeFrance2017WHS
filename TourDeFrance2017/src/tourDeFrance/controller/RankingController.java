package tourDeFrance.controller;

import java.sql.SQLException;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tourDeFrance.model.User;
import tourDeFrance.model.UserDAO;

public class RankingController {

	@FXML
	private Button btnCloseRanking;
	@FXML
	private TableView<User> tbViewR;
	@FXML
	private TableColumn<User, String> colPlatz;
	@FXML
	private TableColumn<User, String> colTipper;
	@FXML
	private TableColumn<User, String> colPunkte;

	@FXML
	public void closeShowRanking() {
		MainMenuController.getInstance().closeTab();
	}

	@FXML
	public void initialize() {

		// colPlatz.setCellValueFactory(cellData ->
		// cellData.getValue().etappenIDProperty().asString());
		colTipper.setCellValueFactory(cellData -> Bindings.concat(cellData.getValue().vorNameProperty(), "            ",
				cellData.getValue().nachNameProperty()));
		// colPunkte.setCellValueFactory(cellData ->
		// cellData.getValue().punkteIDProperty().asString());
		try {
			populateRanking(UserDAO.selectUser());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	//Befuellt Ranking TableView
	private void populateRanking(List<User> user) {
		ObservableList<User> userData = FXCollections.observableArrayList();
		userData.addAll(user);
		tbViewR.setItems(userData);
	}

}
