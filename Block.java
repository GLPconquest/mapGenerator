package mapGenerator;

import java.util.Arrays;

public class Block {
	private static final int DIMENSIONS = 3;
	private int[][] block;
	
	public Block() {
		block = new int[DIMENSIONS][DIMENSIONS];
	}

	public int[][] getBlock() {
		return block;
	}

	public void setBlock(int[][] block) {
		this.block = block;
	}

	@Override
	public String toString() {
		return "Block [block=" + Arrays.toString(block) + "]";
	}
}
