package mapGenerator;

public class SquareNumberGenerator {
	private RandomNumberGenerator rng;
	
	public SquareNumberGenerator() {
		setRng(new RandomNumberGenerator());
	}
	
	public int generate(int[] probabilities){
		int dice = getRng().generate(19, 0);
		int stats = probabilities[0];
		int square = 0;
		while (dice>=stats && square<probabilities.length) {
			square++;
			stats+=probabilities[square];
		}
		return square;
	}

	public RandomNumberGenerator getRng() {
		return rng;
	}

	public void setRng(RandomNumberGenerator rng) {
		this.rng = rng;
	}
}
