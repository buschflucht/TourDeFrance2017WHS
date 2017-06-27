package tourDeFrance;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tourDeFrance.model.Etappen;

public class Main extends Application {
	private Stage primaryStage;
	
	 private ObservableList<Etappen> EtappenData = FXCollections.observableArrayList();

	 
	 public Main(){
		 
	 }
	 
	 
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Tour de France Main Menu");
		showMainView();
	}

	private void showMainView() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainMenu.fxml"));
			AnchorPane pane = loader.load();

			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
