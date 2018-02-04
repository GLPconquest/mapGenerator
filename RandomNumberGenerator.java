package mapGenerator;

import java.util.Random;

public class RandomNumberGenerator {
	
	private Random rng;

	public RandomNumberGenerator() {
		setRng(new Random());
	}
	public int generate(int max, int min) {
		return getRng().nextInt((max-min)+1)+min;
	}
	public int[] generateMultiples(int toGenerate, int max, int min) {
		int[] randomNumber = new int[toGenerate];
		for(int i = 0; i<randomNumber.length; i++) {
			randomNumber[i]=getRng().nextInt((max-min)+1)+min;
		}
		return randomNumber;
	}
	public Random getRng() {
		return rng;
	}
	public void setRng(Random rng) {
		this.rng = rng;
	}
}
