package tetris.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	public static javafx.scene.image.Image EmptyTile;
	
	public void LoadImages() throws IOException
	{
		EmptyTile = new javafx.scene.image.Image("emptyTile.png");
	}
}
