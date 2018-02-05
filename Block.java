package mapGenerator;

import java.util.Arrays;

public class Block {
	private static final int DIMENSIONS = 3;
	private int[][] squares;
	
	public Block() {
		setSquares(new int[DIMENSIONS][DIMENSIONS]);
	}

	public int[][] getSquares() {
		return squares;
	}

	public void setSquares(int[][] squares) {
		this.squares = squares;
	}

	@Override
	public String toString() {
		return "Block [squares=" + Arrays.toString(squares) + "]";
	}

}
