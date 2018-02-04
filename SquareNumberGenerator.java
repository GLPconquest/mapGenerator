package mapGenerator;

public class SquareNumberGenerator {
	private RandomNumberGenerator rng;
	
	public SquareNumberGenerator() {
		setRng(new RandomNumberGenerator());
	}
	
	public int generate(int[] probabilities) {
		int dice = getRng().generate(20, 0);
		if(dice<probabilities[0]) {
			return 0;
		}
		else if(dice<probabilities[0]+probabilities[1]) {
			return 1;
		}
		else if(dice<probabilities[0]+probabilities[1]+probabilities[2]) {
			return 2;
		}
		else if(dice<probabilities[0]+probabilities[1]+probabilities[2]+probabilities[3]) {
			return 3;
		}
		else {
			return 4;
		}
	}

	public RandomNumberGenerator getRng() {
		return rng;
	}

	public void setRng(RandomNumberGenerator rng) {
		this.rng = rng;
	}
}
