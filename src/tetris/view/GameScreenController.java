package tetris.view;

import java.util.concurrent.Callable;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import tetris.Main;
import tetris.model.Images;
import tetris.model.Preview;
import tetris.model.TetrisBoard;
import tetris.model.Tetromino;
import tetris.model.Vector2;

public class GameScreenController {
	
	@FXML
	Pane tetris_pane;

	@FXML
	Pane preview;
	
	@FXML 
	Label score;
	
	@FXML 
	Label level;
	
	@FXML 
	Label lines;
	
	private Main main;
	private Timeline updateLabels;
	
	ImageView[] Tiles = new ImageView[200];
	ImageView[] Tiles2 = new ImageView[25];
	TetrisBoard Board = new TetrisBoard();
	Preview  previewBoard = new Preview();
	Timer timer = new Timer();
	boolean Running = true;
	boolean paused = false;
	boolean gameover = false;
	double speed = 1;
	double levelSpeed = 1;
	
	
	//@FXML
	//private void Initialize()
	//{
	//}
	public void Update(double deltaTime)
	{
		if (!this.paused)
		{
			Board.Fall();
			this.levelSpeed = Board.getLevel();
			if (Board.isGameOver())
				this.gameover = true;
		}
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
		
		nextTetromino(Board.getNextTetromino());
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
		
		nColumns = 5;
		maxWidth = preview.getWidth() / 5;
		maxHeight = preview.getHeight() / 5;
		i = 0;
		for (ImageView image : Tiles2)
		{
			image = new ImageView();
			image.setImage(Images.EmptyTile);
			image.setX((i % (int) nColumns) * maxWidth);
			image.setY((i / (int) nColumns) * maxHeight);
			
			image.setFitWidth(maxWidth);
			image.setFitHeight(maxHeight);
			
			preview.getChildren().add(image);

			i++;
		}
		
		this.updateLabels = new Timeline( 
                new KeyFrame(new Duration(110.0), t -> { //game will speed up and you progress
                	if (!this.gameover)
                	{
                		this.level.setText(this.Board.getLevel().toString()); //update the level that is displayed
            			this.lines.setText(this.Board.getLinesCleared().toString()); //update the lines cleared that is displayed
            			this.score.setText(this.Board.getScore().toString()); //update the score that is displayed
                	}
                })
        );
		
		this.updateLabels.setCycleCount(Timeline.INDEFINITE);//play the animation in an infinite loop
        this.updateLabels.play(); //we have to tell the animation to start (similar to telling a thread to start)
		
		timer.schedule(new TimerTask() {
            public void run() {
            	long start = System.nanoTime(), end = System.nanoTime();
            	while (Running)
            	{
            		
            		end = System.nanoTime();
                	Update((double)(end - start) / 1000000000);
                    Render();
                    start = System.nanoTime();
                    
                    try {
						Thread.sleep((long) (500 / (levelSpeed * speed)));
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
		System.exit(1);
	}
	
	@FXML
	private void handlePause()
	{
		this.paused = true;
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
			
			pauseStage.show();
			
			pauseStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					paused = false;
				}
			});
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		this.main.getMediaPlayer().play();
	}
	
	public void setSceneListener(Scene scene) //scene must be passed in from main as the pane cannot respond to key presses (not in focus)
	{
        scene.setOnKeyPressed(k -> { //adds the onKeyPressed listener to the scene obtained from the main application
        	/*if(k.getCode() == KeyCode.SPACE){ //used for pausing the game
        		this.paused = !this.paused;
        		if(this.paused)
        		{
        			this.snakeMovement.pause();
        			this.status.setText("PAUSED");
        			this.status.setLayoutX(this.habitat.getWidth()/2 - 100);
        			this.status.setLayoutY(this.habitat.getHeight()/2 - 50);
        			this.status.setVisible(true);
        			this.habitat.getChildren().add(this.status);
        		}
        		else
        		{
        			this.snakeMovement.play();
        			this.habitat.getChildren().remove(this.status);
        		}
        	}*/
        	if(this.gameover && k.getCode() == KeyCode.ENTER) //used for starting a new game after gameover
        		Initialize();
        	
        	//the arrow keys are used for moving the snake
        	//orientation is from the top left corner, meaning the top left corner is (0,0)
        	if(!this.paused && !this.gameover) //do not allow the snake to be moved if the game is paused or in gameover
        	{
	        	if (k.getCode() == KeyCode.DOWN)
	            {
	            	this.speed = 8;
	            }
	            else if (k.getCode() == KeyCode.UP)
	            {
	            	Board.Rotate();
	            }
	            else if (k.getCode() == KeyCode.RIGHT)
	            {
	            	Board.MoveRight();
	            }
	            else if (k.getCode() == KeyCode.LEFT)
	            {
	            	Board.MoveLeft();
	            }
        	}
        });
        
        scene.setOnKeyReleased(m -> {
        	if(!this.paused && !this.gameover) //do not allow the snake to be moved if the game is paused or in gameover
        	{
	        	if (m.getCode() == KeyCode.DOWN)
	            {
	            	this.speed = levelSpeed;
	            }
        	}
        });
	}
	public void ClearPreviewPane()
	{
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				getPreviewBlock(i, j).setImage(Images.getByType(0));
	}
	
	public void nextTetromino(Tetromino next)
	{
		ClearPreviewPane();
		Tetromino temp = next.Clone();
		temp.SetPosition(2, 2);
		getPreviewBlock(temp.getPosition().x, temp.getPosition().y).setImage(Images.getByType(temp.getType()));
		for (Vector2<Integer> block : temp.getBlocks())
		{
			getPreviewBlock(temp.getPosition().x + block.x, temp.getPosition().y + block.y).setImage(Images.getByType(temp.getType()));
		}
	}
	
	public ImageView getPreviewBlock(int x, int y)
	{
		return ((ImageView)preview.getChildren().get(y * 5 + x));
	}
}
