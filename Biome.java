package mapGenerator;

import java.util.Arrays;

public class Biome {
	private static final int DIMENSIONS = 3;
	private Block[][] biome;
	
	public Biome() {
		biome = new Block[DIMENSIONS][DIMENSIONS];
	}

	public Block[][] getBiome() {
		return biome;
	}

	public void setBiome(Block[][] biome) {
		this.biome = biome;
	}

	@Override
	public String toString() {
		return "Biome [biome=" + Arrays.toString(biome) + "]";
	}
	
}
