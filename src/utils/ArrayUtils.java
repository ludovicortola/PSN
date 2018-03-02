package utils;

public class ArrayUtils {

	
	public static int[][] cloneSquareMatrix(int[][] input) {
		int[][] output = new int[input.length][input.length];
		for (int i=0; i<input.length; i++) {
			for (int j=0; j<input.length; j++) {
				output[i][j] = input[i][j];
			}
		}
		return output;
	}
}
