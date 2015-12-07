package tetris.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tetris.Main;

public class MainMenuController 
{
// hello
	private Stage gameStage;
	
	@FXML
	private Label start;
	
	@FXML
	private Label resume;
	
	@FXML
	private Label leader;
	
	@FXML
	private Label exit;
	
	private Main main;
	
	@FXML
	private void initialize()
	{
		
	}
	
	public void setMain(Main main)
	{
		this.main = main;
	}
	
	@FXML
	private void handleStartGame()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/GameScreen.fxml"));
			AnchorPane gamePane = (AnchorPane) loader.load();
			
			Scene scene = new Scene(gamePane);
			main.getPrimaryStage().setScene(scene);
			main.getPrimaryStage().show();
			GameScreenController controller = loader.getController();
			controller.setMain(this.main.getMain());
			controller.Initialize();
			controller.setSceneListener(scene);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@FXML
	private void handleLeaderBoard()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/LeaderBoard.fxml"));
			AnchorPane leaderBoard = (AnchorPane) loader.load();
			
			Stage leaderStage = new Stage();
			leaderStage.setTitle("Leader Board");
			leaderStage.initModality(Modality.WINDOW_MODAL);  // While window is open, nothing can happen to primary stage or its content
			leaderStage.initOwner(main.getPrimaryStage());
			Scene scene = new Scene(leaderBoard);
			leaderStage.setScene(scene);
			
			leaderStage.showAndWait();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@FXML 
	private void handleExit()
	{
		main.getPrimaryStage().close();
		System.exit(1);
	}
}
