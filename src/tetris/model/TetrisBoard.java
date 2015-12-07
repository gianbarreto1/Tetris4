package tetris.model;

public class TetrisBoard {
	private int[][] Blocks;
	Tetromino workingTetromino;
	Tetromino next;
	double TetrominoProgrss = 21d;
	double speed = 1;
	boolean gameOver = false;
	RandomTetrominoFactory factory = new RandomTetrominoFactory();
	double Speed = 1d;
	int level = 1;
	int linesCleared = 0;
	int score = 0;
	int linesClearedCount = 0;
	
	public TetrisBoard()
	{
		Blocks = new int[10][20];
		workingTetromino = factory.getRandomTetromino();
		next = factory.getRandomTetromino();
	}
	public void MoveLeft()
	{
		if (canLeft()){
			workingTetromino.SetPosition(workingTetromino.getPosition().x - 1, workingTetromino.getPosition().y);
		}
	}
	public void MoveRight()
	{
		if (canRight()){
		workingTetromino.SetPosition(workingTetromino.getPosition().x + 1, workingTetromino.getPosition().y);
		}
	}
	public void NextTetromino(Tetromino next)
	{
		workingTetromino = next;
	}
	public void Fall()
	{
		if (canFall())
		{
			workingTetromino.SetPosition(workingTetromino.getPosition().x, workingTetromino.getPosition().y + 1);
		}
		else
		{
			ApplyTetromino();
			
			if (canPlay(next))
			{
				this.score += 20;
				NextTetromino(next);
				next = factory.getRandomTetromino();
			}
			else
				this.gameOver = true;
		}
	}
	public void ApplyTetromino()
	{
		int count;
		int clearedCount = 0;
		Blocks[workingTetromino.getPosition().x][workingTetromino.getPosition().y] = workingTetromino.getType();
		for (Vector2<Integer> block : workingTetromino.getBlocks())
		{
			Blocks[workingTetromino.getPosition().x + block.x][workingTetromino.getPosition().y + block.y]  = workingTetromino.getType();
		}
		
		for (int i = 19; i > 0; i--)
		{
			count = 0;
			for (int j = 0; j < 10; j++)
			{
				if (Blocks[j][i] != 0)
					count++;
			}
			if (count == 10)
			{
				for (int k = i; k > 0; k--)
					for (int l = 0; l < 10; l++)
						Blocks[l][k] = Blocks[l][k-1];
				i++;
				clearedCount += 1;
				this.linesClearedCount += 1;
			}
		}
		
		if (clearedCount == 1)
			this.score += 40 * this.level;
		else if (clearedCount == 2)
			this.score += 100 * this.level;
		else if (clearedCount == 3)
			this.score += 300 * this.level;
		else if (clearedCount == 4)
			this.score += 1200 * this.level;
		
		if (this.linesClearedCount == 10)
		{
			this.level += 1;
			this.linesClearedCount = 0;
		}
		this.linesCleared += clearedCount;
	}
	
	public void Rotate()
	{
		boolean b = canRotate();
		if (b)
		{
			workingTetromino.Rotate();
		}
	}
	
	public boolean canPlay(Tetromino next)
	{
		if (Blocks[next.getPosition().x][next.getPosition().y] == 0)
		{
			boolean check = true;
			for (Vector2<Integer> block : next.getBlocks())
			{
				int b = Blocks[next.getPosition().x + block.x][next.getPosition().y + block.y];
				if (b != 0)
					check = false;
			}
			return check;
		}
		return false;
	}
	
	public boolean canRotate(){
		Tetromino temp = workingTetromino.Clone();
		temp.Rotate();
		if (temp.getPosition().x > -1 && temp.getPosition().x < 10 && temp.getPosition().y > -1 && temp.getPosition().y < 20)
		{
			if (Blocks[temp.getPosition().x][temp.getPosition().y] == 0)
			{
				boolean check = true;
				for (Vector2<Integer> block : temp.getBlocks())
				{
					if (temp.getPosition().x + block.x > -1 && temp.getPosition().x + block.x < 10 && temp.getPosition().y + block.y > -1 && temp.getPosition().y + block.y < 20)
					{
						int b = Blocks[temp.getPosition().x + block.x][temp.getPosition().y + block.y];
						if ( b != 0)
						 check = false;
					}
					else
					{
						check = false;
					}

				}
				return check;
			}
		}
		return false;
	}
	
	public boolean canFall()
	{
		if (workingTetromino.getPosition().y + 1 < 20)
		{
			if (Blocks[workingTetromino.getPosition().x][workingTetromino.getPosition().y + 1] == 0)
			{
				boolean check = true;
				for (Vector2<Integer> block : workingTetromino.getBlocks())
				{
					if (workingTetromino.getPosition().y + block.y + 1 < 20)
					{
						int b = Blocks[workingTetromino.getPosition().x + block.x][workingTetromino.getPosition().y + block.y + 1];
						if ( b != 0)
						 check = false;
					}
					else
					{
						check = false;
					}

				}
				return check;
			}
		}
		return false;
	}
	public boolean canLeft()
	{
		if (workingTetromino.getPosition().x - 1 > -1)
		{
			if (Blocks[workingTetromino.getPosition().x - 1][workingTetromino.getPosition().y] == 0)
			{
				boolean check = true;
				for (Vector2<Integer> block : workingTetromino.getBlocks())
				{
					if (workingTetromino.getPosition().x + block.x - 1 > -1)
					{
						int b = Blocks[workingTetromino.getPosition().x + block.x - 1][workingTetromino.getPosition().y + block.y];
						if ( b != 0)
						 check = false;
					}
					else
						check = false;

				}
				return check;
			}
		}
		return false;
	}
	public boolean canRight()
	{
		if (workingTetromino.getPosition().x + 1 < 10)
		{
			if (Blocks[workingTetromino.getPosition().x + 1][workingTetromino.getPosition().y] == 0)
			{
				boolean check = true;
				for (Vector2<Integer> block : workingTetromino.getBlocks())
				{
					if (workingTetromino.getPosition().x + block.x + 1 < 10)
					{
						int b = Blocks[workingTetromino.getPosition().x + block.x + 1][workingTetromino.getPosition().y + block.y];
						if ( b != 0)
						 check = false;
					}
					else
						check = false;

				}
				return check;
			}
		}
		return false;
	}
	
	public int getBlock(int x, int y)
	{
		return Blocks[x][y];
	}
	
	public Tetromino getWorkingTetromino()
	{
		return workingTetromino;
	}
	
	public boolean isGameOver()
	{
		return this.gameOver;
	}
	
	public Tetromino getNextTetromino()
	{
		return next;
	}
	
	public Integer getLevel()
	{
		return this.level;
	}
	
	public Integer getLinesCleared()
	{
		return this.linesCleared;
	}
	
	public Integer getScore()
	{
		return this.score;
	}
}
