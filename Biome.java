package mapGenerator;

import java.util.Arrays;

public class Biome {
	private static final int DIMENSIONS = 3;
	private Block[][] blocks;
	
	public Biome() {
		setBlocks(new Block[DIMENSIONS][DIMENSIONS]);
	}
	
	public Block[][] getBlocks() {
		return blocks;
	}

	public void setBlocks(Block[][] blocks) {
		this.blocks = blocks;
	}

	@Override
	public String toString() {
		return "Biome [blocks=" + Arrays.toString(blocks) + "]";
	}

}
