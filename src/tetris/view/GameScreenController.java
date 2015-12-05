package tetris.view;

import java.util.concurrent.Callable;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tetris.Main;
import tetris.model.Images;
import tetris.model.TetrisBoard;
import tetris.model.Vector2;

public class GameScreenController {
	
	@FXML
	Pane tetris_pane;

	@FXML
	Pane preview;
	
	private Main main;
	
	ImageView[] Tiles = new ImageView[200];
	TetrisBoard Board = new TetrisBoard();
	Timer timer = new Timer();
	boolean Running = true;
	
	//@FXML
	//private void Initialize()
	//{
	//}
	public void Update()
	{
		Board.Update();
	}
	public void Render()
	{
		for (int x = 0; x < 10; x++)
		{
			for (int y = 0; y < 20; y++)
			{
				int result = Board.getBlock(x, y);
				getBlock(x, y).setImage(Images.getByType(result));
			}
		}
		getBlock(Board.getWorkingTetromino().getPosition().x, Board.getWorkingTetromino().getPosition().y).setImage(Images.getByType(Board.getWorkingTetromino().getType()));
		for (Vector2<Integer> block : Board.getWorkingTetromino().getBlocks())
		{
			getBlock(Board.getWorkingTetromino().getPosition().x + block.x, Board.getWorkingTetromino().getPosition().y + block.y).setImage(Images.getByType(Board.getWorkingTetromino().getType()));
		}
	}
	public void Initialize()
	{
		int nColumns = 10;
		int i = 0;
		double maxWidth = tetris_pane.getWidth() / 10;
		double maxHeight = tetris_pane.getHeight() / 20;
		for (ImageView image : Tiles)
		{
			image = new ImageView();
			image.setImage(Images.EmptyTile);
			image.setX((i % (int) nColumns) * maxWidth);
			image.setY((i / (int) nColumns) * maxHeight);
			
			image.setFitWidth(maxWidth);
			image.setFitHeight(maxHeight);
			
			tetris_pane.getChildren().add(image);

			i++;
		}
		
		timer.schedule(new TimerTask() {
            public void run() {
            	while (Running)
            	{
                	Update();
                    Render();
                    try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}

                
            }}, 0, 1 * 1000);
	}
	public ImageView getBlock(int x, int y)
	{
		return ((ImageView)tetris_pane.getChildren().get(y * 10 + x));
	}
	

	public void setMain(Main main)
	{
		this.main = main;
	}
	
	@FXML 
	private void handleQuit()
	{	
		this.main.getMain().getPrimaryStage().close();
	}
	
	@FXML
	private void handlePause()
	{
		this.main.getMediaPlayer().pause();
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/PauseMenu.fxml"));
			AnchorPane pausePane = (AnchorPane) loader.load();
			
			Stage pauseStage = new Stage();
			pauseStage.setTitle("Pause Menu");
			pauseStage.initModality(Modality.WINDOW_MODAL);  // While window is open, nothing can happen to primary stage or its content
			pauseStage.initOwner(main.getPrimaryStage());
			Scene scene = new Scene(pausePane);
			pauseStage.setScene(scene);
			
			pauseStage.showAndWait();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		this.main.getMediaPlayer().play();
	}
}
