package tetris.model;

public abstract class Tetromino {
	
	private Vector2<Integer> Position;//Position of the pivot block
	protected Vector2<Integer>[] Blocks;
	
	public Tetromino(Vector2<Integer> initialPosition)
	{
		Position = initialPosition;
		SetBlocks();
	}
	
	public Vector2<Integer> getPosition()
	{
		return Position;
	}
	public void SetPosition(int x, int y)
	{
		Position.x = x;
		Position.y = y;
	}
	
	protected abstract void SetBlocks();
	
	public abstract Vector2<Integer>[] getBlocks();
	
	public void Rotate()
	{
		for (Vector2<Integer> block : Blocks)
		{
			double theta = Math.PI / 2;

			double cs = Math.cos(theta);
			double sn = Math.sin(theta);

			int newX = (int)Math.round(block.x * cs - block.y * sn);
			int newY = (int)Math.round(block.x * sn + block.y * cs);
			block.x = newX;
			block.y = newY;
		}
	}
	public abstract int getType();
}
