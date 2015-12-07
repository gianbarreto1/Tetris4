package tetris.model;

public class StraightPolymino extends Tetromino{

	
	
	public StraightPolymino(Vector2<Integer> initialPosition)
	{
		super(initialPosition);
	}
	
	@Override
	protected void SetBlocks() {
		// TODO Auto-generated method stub
		Blocks = new Vector2[3];
		Blocks[0] = new Vector2(-1, 0);
		Blocks[1] = new Vector2(1, 0);
		Blocks[2] = new Vector2(2, 0);
	}

	@Override
	public Vector2<Integer>[] getBlocks() {
		return Blocks;
	}

	@Override
	public int getType() {
		return 2;
	}

	@Override
	public Tetromino Clone() {
		// TODO Auto-generated method stub
		Tetromino clone = new StraightPolymino(new Vector2(2,2));
		clone.SetPosition(this.getPosition().x, this.getPosition().y);
		for (int i = 0; i < this.Blocks.length; i++)
		{
			clone.Blocks[i] = new Vector2<Integer>(this.Blocks[i].x, this.Blocks[i].y);
		}
		return clone;
	}
}
