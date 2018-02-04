package mapGenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exceptions.InvalidSquareNumberException;
import exceptions.InvalidBiomeNumberException;

public class BlockGenerator {	
	private String statsFile;
	private SquareNumberGenerator sng;
	
	public BlockGenerator(String statsFile){
		setStatsFile(statsFile);
		setSng(new SquareNumberGenerator());
	}
	
	public Block generate(int biome, int mainSquare) 
			throws InvalidBiomeNumberException, InvalidSquareNumberException{
		
		Block block = new Block();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(getStatsFile()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(biome>5) {
			throw new InvalidBiomeNumberException(biome);
		}
		if(mainSquare>10) {
			throw new InvalidSquareNumberException(mainSquare);
		}
		String[] currentLine = {"","",""};
		while (!currentLine[0].equals(String.valueOf(biome)) &&
				!currentLine[1].equals(String.valueOf(mainSquare))) {
			try {
				currentLine = reader.readLine().split("#");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int[] probabilities = transformIntoInteger(currentLine[2].split(","));
		for(int i = 0; i<3; i++) {
			for (int j = 0; j<3; j++) {
				if (i!=1 || j!=1) {
					block.getBlock()[i][j] = sng.generate(probabilities);
				}
			}
		}
		block.getBlock()[1][1] = mainSquare;
		return block;
	}
	
	public int[] transformIntoInteger (String[] string) {
		int[] result = new int[string.length];
		for(int i = 0; i<string.length; i++) {
			result[i] = Integer.parseInt(string[i]);
		}
		return result;
	}
	
	public String getStatsFile() {
		return statsFile;
	}

	public void setStatsFile(String statsFile) {
		this.statsFile = statsFile;
	}
	
	public SquareNumberGenerator getSng() {
		return sng;
	}

	public void setSng(SquareNumberGenerator sng) {
		this.sng = sng;
	}
}