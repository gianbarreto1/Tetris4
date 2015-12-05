package tetris.model;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Images {
	public static javafx.scene.image.Image EmptyTile;
	public static javafx.scene.image.Image YellowTile;
	
	public static void LoadImages() throws IOException
	{
		File emptyTilefile = new File("Content\\EmptyTile.png");
		FileInputStream  emptyTilestrean = new FileInputStream(emptyTilefile);
		EmptyTile = new javafx.scene.image.Image(emptyTilestrean);
		
		File yellowTilefile = new File("Content\\YellowTile.png");
		FileInputStream  yellowTilestrean = new FileInputStream(yellowTilefile);
		YellowTile = new javafx.scene.image.Image(yellowTilestrean);
	}
	
	public static javafx.scene.image.Image getByType(int type)
	{
		switch (type)
		{
			case 0:
				return EmptyTile;
			case 1:
				return YellowTile;
			default:
				return null;
		}
	}
}