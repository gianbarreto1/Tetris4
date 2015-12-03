package tetris;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private AnchorPane mainPane;
	
	public Main()
	{
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Tetris");
		
		showMainMenu();
	}
	
	private void showMainMenu()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainMenu.fxml"));
			mainPane = (AnchorPane) loader.load();
			
			Scene scene = new Scene(mainPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage()
	{
		return this.primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

