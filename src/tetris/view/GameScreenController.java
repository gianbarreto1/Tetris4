package tetris.view;


import java.awt.Button;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tetris.model.Images;

public class GameScreenController {
	
	@FXML
	GridPane tetrisPane;

	@FXML
	Pane preview;
	
	ImageView[] Tiles = new ImageView[200];
	
	public void InitializeGrid()
	{
		int nColumns = 10;
		int i = 0;

		for (ImageView image : Tiles)
		{
			GridPane.setRowIndex(image,       i / (int) nColumns);
			GridPane.setColumnIndex(image, i % (int) nColumns);
			
			image = new ImageView();
			image.setImage(Images.EmptyTile);
			
			tetrisPane.getChildren().add(image);

			i++;
		}
	}
}
