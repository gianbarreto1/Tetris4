package tetris;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import tetris.view.GameScreenController;
import tetris.view.MainMenuController;

public class Main extends Application {

	private Stage primaryStage;
	private AnchorPane mainPane;
	private MediaPlayer mp;
	
	public Main()
	{
		
	}
	
	@Override
	public void start(Stage primaryStage) 
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Tetris");
		
		Media media = new Media(Main.class.getResource("tetris_music.mp3").toString());
		mp = new MediaPlayer(media);  // Creates a new thread that plays the media
		
		mp.setCycleCount(MediaPlayer.INDEFINITE);  // Set the media player to infinite loop
		mp.play();  // need to tell the media player to start playing (similar to starting a thread)
		//stops the media player when program closes
        //existing bug may cause media player to stop prematurely without this
		this.primaryStage.setOnCloseRequest(windowEvent -> {
			mp.stop();
		});
		
		showMainMenu();
	}
	
	private void showMainMenu()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainMenu.fxml"));
			mainPane = (AnchorPane) loader.load();
			
			MainMenuController controller = loader.getController();
			controller.setMain(this);
			
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
	
	public MediaPlayer getMediaPlayer()
	{
		return this.mp;
	}
	
	public Main getMain()
	{
		return this;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

