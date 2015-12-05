package tetris.model;

import java.util.Random;

public class RandomTetrominoFactory {
	static Random random = new Random();

	public static Tetromino getRandomTetromino()
	{
		int randomNum = random.nextInt((1) + 1);
		switch (randomNum)
		{
			case 0:
				return new StraightPolymino(new Vector2(5, 0));
			default:
				return new StraightPolymino(new Vector2(5, 0));
		}
	}
}
