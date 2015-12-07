package tetris.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Images {
	public static javafx.scene.image.Image EmptyTile;
	public static javafx.scene.image.Image YellowTile;
	public static javafx.scene.image.Image CyanTile;
	public static javafx.scene.image.Image BlueTile;
	public static javafx.scene.image.Image LimeTile;
	public static javafx.scene.image.Image OrangeTile;
	public static javafx.scene.image.Image PurpleTile;
	public static javafx.scene.image.Image RedTile;
	
	public static void LoadImages() throws IOException
	{
		File emptyTilefile = new File("Content\\EmptyTile.png");
		FileInputStream  emptyTilestrean = new FileInputStream(emptyTilefile);
		EmptyTile = new javafx.scene.image.Image(emptyTilestrean);
		
		File yellowTilefile = new File("Content\\YellowTile.png");
		FileInputStream  yellowTilestrean = new FileInputStream(yellowTilefile);
		YellowTile = new javafx.scene.image.Image(yellowTilestrean);
		
		File cyanTilefile = new File("Content\\CyanTile.png");
		FileInputStream  cyanTilestrean = new FileInputStream(cyanTilefile);
		CyanTile = new javafx.scene.image.Image(cyanTilestrean);
		
		File blueTilefile = new File("Content\\BlueTile.png");
		FileInputStream  blueTilestrean = new FileInputStream(blueTilefile);
		BlueTile = new javafx.scene.image.Image(blueTilestrean);
		
		File limeTilefile = new File("Content\\LimeTile.png");
		FileInputStream  limeTilestrean = new FileInputStream(limeTilefile);
		LimeTile = new javafx.scene.image.Image(limeTilestrean);
		
		File orangeTilefile = new File("Content\\OrangeTile.png");
		FileInputStream  orangeTilestrean = new FileInputStream(orangeTilefile);
		OrangeTile = new javafx.scene.image.Image(orangeTilestrean);
		
		File purpleTilefile = new File("Content\\PurpleTile.png");
		FileInputStream  purpleTilestrean = new FileInputStream(purpleTilefile);
		PurpleTile = new javafx.scene.image.Image(purpleTilestrean);
		
		File redTilefile = new File("Content\\RedTile.png");
		FileInputStream  redTilestrean = new FileInputStream(redTilefile);
		RedTile = new javafx.scene.image.Image(redTilestrean);
	}
	
	public static javafx.scene.image.Image getByType(int type)
	{
		switch (type)
		{
			case 0:
				return EmptyTile;
			case 1:
				return YellowTile;
			case 2:
				return CyanTile;
			case 3:
				return BlueTile;
			case 4:
				return LimeTile;
			case 5:
				return OrangeTile;
			case 6:
				return PurpleTile;
			case 7:
				return RedTile;
			default:
				return null;
		}
	}
}