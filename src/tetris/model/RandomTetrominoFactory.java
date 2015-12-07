package tetris.model;

import java.util.Random;

public class RandomTetrominoFactory {
	static Random random = new Random();

	public static Tetromino getRandomTetromino()
	{
		int randomNum = random.nextInt((6) + 1);
		switch (randomNum)
		{
			case 0:
				return new StraightPolymino(new Vector2(5, 0));
			case 1:
				return new Square(new Vector2(4,0));
			case 2:
				return new Tturn(new Vector2(5,0));
			case 3:
				return new RightSnake(new Vector2(5,0));
			case 4:
				return new LeftSnake(new Vector2(5,0));
			case 5:
				return new LeftGun(new Vector2(5,0));
			case 6:
				return new RightGun(new Vector2(5,0));
			default:
				return new StraightPolymino(new Vector2(5, 0));
		}
	}
}
