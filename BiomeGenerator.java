package mapGenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.InvalidBiomeNumberException;
import exceptions.InvalidSquareNumberException;

public class BiomeGenerator {
	/*
	 * 0 = Prairie
	 * 1 = Forêt tempérée
	 * 2 = Jungle
	 * 3 = Désert
	 * 4 = Savane
	 * 5 = Montagneux
	 */
	private static final int GROUND_BLOCKS = 3;
	private static final int BUILDING_BLOCKS = 3;
	private static final int CITY_BLOCKS = 3;
	private String biomeStatsFile;
	private String blockStatsFile;
	private BlockGenerator blockGenerator;
	private RandomNumberGenerator rng;
	
	public BiomeGenerator(String biomeStatsFile, String blockStatsFile) {
		setBiomeStatsFile(biomeStatsFile);
		setBlockStatsFile(blockStatsFile);
		setBlockGenerator(new BlockGenerator(getBlockStatsFile()));
		setRng(new RandomNumberGenerator());
	}
	
	public Biome generate(int type, boolean startingBiome)
			throws InvalidBiomeNumberException, InvalidSquareNumberException{
		Biome biome = new Biome();
		ArrayList<Block> blocks = new ArrayList<Block>();
		BufferedReader reader = null;
		if(type>5) {
			throw new InvalidBiomeNumberException(type);
		}
		try {
			reader = new BufferedReader(new FileReader(getBiomeStatsFile()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] currentLine = {"",""};
		while(!currentLine[0].equals(String.valueOf(type))){
			try {
				currentLine = reader.readLine().split("#");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int[] probabilities = transformIntoInteger(currentLine[1].split(","));
		//Generate 3 ground blocks
		for(int i = 0; i<GROUND_BLOCKS; i++) {
			blocks.add(generate(type, probabilities));
		}
		
		try {
			currentLine = reader.readLine().split("#");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		probabilities = transformIntoInteger(currentLine[1].split(","));
		//generate 3 building blocks
		for(int i = 0; i<BUILDING_BLOCKS; i++) {
			blocks.add(generate(type, probabilities));
		}
		//generate 3 city blocks
		for(int i = 0; i<CITY_BLOCKS; i++) {
			blocks.add(getBlockGenerator().generate(type, 9));
		}
		if(startingBiome) {
			biome = buildStartingBiome(biome, blocks);
		}
		else {
			biome = buildBiome(biome, blocks);
		}
		return biome;
	}
	
	public Block generate(int type, int[]probabilities)
			throws InvalidBiomeNumberException, InvalidSquareNumberException{
		
		Block result = null;
		int dice = getRng().generate(19, 0);
		int stats = probabilities[0];
		int mainSquare = 0;
		while (dice>=stats && mainSquare<probabilities.length) {
			mainSquare++;
			stats+=probabilities[mainSquare];
		}
		result = getBlockGenerator().generate(type, mainSquare);
		return result;
	}
	
	public Biome buildStartingBiome(Biome biome, ArrayList<Block> blocks) {
		int number = getRng().generate(CITY_BLOCKS-1, 0);
		for(Block block : blocks) {
			if (block.getSquares()[1][1]==9) {
				if (number == 0) {
					biome.getBlocks()[1][1] = block;
					blocks.remove(block);
				}
				else {
					number--;
				}
			}
		}
		for(int i = 0; i<3; i++) {
			for (int j = 0; j<3; j++) {
				if(i!=1 || j!=1) {
					number = getRng().generate(blocks.size()-1, 0);
					biome.getBlocks()[i][j] = blocks.get(number);
					blocks.remove(number);
				}
			}
		}
		
		return biome;
	}
	
	public Biome buildBiome(Biome biome, ArrayList<Block> blocks) {
		for(int i = 0; i<3; i++) {
			for (int j = 0; j<3; j++) {
				int number = getRng().generate(blocks.size()-1, 0);
				biome.getBlocks()[i][j] = blocks.get(number);
				blocks.remove(number);
			}
		}
		return biome;
	}
	
	public int[] transformIntoInteger (String[] string) {
		int[] result = new int[string.length];
		for(int i = 0; i<string.length; i++) {
			result[i] = Integer.parseInt(string[i]);
		}
		return result;
	}
	
	public String getBiomeStatsFile() {
		return biomeStatsFile;
	}

	public void setBiomeStatsFile(String biomeStatsFile) {
		this.biomeStatsFile = biomeStatsFile;
	}

	public String getBlockStatsFile() {
		return blockStatsFile;
	}

	public void setBlockStatsFile(String blockStatsFile) {
		this.blockStatsFile = blockStatsFile;
	}

	public BlockGenerator getBlockGenerator() {
		return blockGenerator;
	}

	public void setBlockGenerator(BlockGenerator blockGenerator) {
		this.blockGenerator = blockGenerator;
	}

	public RandomNumberGenerator getRng() {
		return rng;
	}

	public void setRng(RandomNumberGenerator rng) {
		this.rng = rng;
	}
}
