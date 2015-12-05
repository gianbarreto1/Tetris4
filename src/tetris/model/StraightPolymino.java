package tetris.model;

import java.awt.Image;

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
		return 1;
	}

}
