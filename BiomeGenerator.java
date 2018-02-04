package mapGenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
		Block[] blocks = new Block[9];
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
		for(int i = 0; i<3; i++) {
			blocks[i] = generateGroundBlock(type, probabilities);
		}
		
		try {
			currentLine = reader.readLine().split("#");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		probabilities = transformIntoInteger(currentLine[1].split(","));
		for(int i = 3; i<6; i++) {
			blocks[i] = generateGroundBlock(type, probabilities);
		}
		for(int i = 6; i<9; i++) {
			blocks[i] = getBlockGenerator().generate(type, 9);
		}
		if(startingBiome) {
			biome = buildStartingBiome(biome, blocks);
		}
		else {
			biome = buildStartingBiome(biome, blocks);
		}
		return biome;
	}
	
	public Block generateGroundBlock(int type, int[] probabilities) 
			throws InvalidBiomeNumberException, InvalidSquareNumberException{
		Block result;
		int dice;
		try {
			dice = getRng().generate(20, 0);
			if(dice<probabilities[0]) {
				result=getBlockGenerator().generate(type, 0);
			}
			else if(dice<probabilities[0]+probabilities[1]) {
				result=getBlockGenerator().generate(type, 1);
			}
			else if(dice<probabilities[0]+probabilities[1]+probabilities[2]) {
				result=getBlockGenerator().generate(type, 2);
			}
			else if(dice<probabilities[0]+probabilities[1]+probabilities[2]+probabilities[3]) {
				result=getBlockGenerator().generate(type, 3);
			}
			else {
				result=getBlockGenerator().generate(type, 4);
			}
		}catch(InvalidBiomeNumberException error) {
			throw error;
		}catch(InvalidSquareNumberException error) {
			throw error;
		}
		return result;
	}
	
	public Block generateBuildingsBlock(int type, int[] probabilities) 
			throws InvalidBiomeNumberException, InvalidSquareNumberException{
		Block result;
		int dice;
		try {
			dice = getRng().generate(20, 0);
			if(dice<probabilities[0]) {
				result=getBlockGenerator().generate(type, 5);
			}
			else if(dice<probabilities[0]+probabilities[1]) {
				result=getBlockGenerator().generate(type, 6);
			}
			else if(dice<probabilities[0]+probabilities[1]+probabilities[2]) {
				result=getBlockGenerator().generate(type, 7);
			}
			else {
				result=getBlockGenerator().generate(type, 8);
			}
		}catch(InvalidBiomeNumberException error) {
			throw error;
		}catch(InvalidSquareNumberException error) {
			throw error;
		}
		return result;
	}
	
	public Biome buildStartingBiome(Biome biome, Block[] blocks) {
		biome.getBiome()[1][1]=blocks[9];
		for(int i = 0; i<3; i++) {
			for (int j = 0; j<3; j++) {
				if (i!=1 || j!=1) {
					
				}
			}
		}
		return biome;
	}
	
	public Biome buildBiome(Biome biome, Block[] blocks) {
		
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
