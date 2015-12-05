package tetris.model;

public class TetrisBoard {
	private int[][] Blocks;
	Tetromino workingTetromino;
	
	RandomTetrominoFactory factory = new RandomTetrominoFactory();
	double Speed = 1d;
	
	public TetrisBoard()
	{
		Blocks = new int[10][20];
	}
	
	public void AddTetromino(Tetromino tetromino)
	{
		
	}
	
	public void Update()
	{
		if (workingTetromino == null)
			workingTetromino = factory.getRandomTetromino();
		else
		{
			workingTetromino.SetPosition(workingTetromino.getPosition().x, workingTetromino.getPosition().y + 1);
		}
	}
	
	public int getBlock(int x, int y)
	{
		return Blocks[x][y];
	}
	
	public Tetromino getWorkingTetromino()
	{
		return workingTetromino;
	}
}
