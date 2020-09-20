package project1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinesweepOperationsImpl implements MinesweepOperationsInterface {
	
	MinesweepOperationsImpl(){
		
	}

	private String[] layoutPatterns;
	private int arrayRowSize;
	private int[][] inputArray = { { 1, 1, 0 }, { 1, 1, 1 }, { 1, 0, 0 } };
	private int mineCount = 6;
	private int normalCount = 3;
	private HashMap<String, Integer> flagCount = new HashMap<>();

	private String[][] outputArray = { { "x", "x", "x" }, { "x", "x", "x" }, { "x", "x", "x" } };

	@Override
	public void handleMinefieldLayout(String layout) {

		if ((null != layout) && ("" != layout)) {
			setLayoutPatterns(layout.split(","));
			setArrayRowSize(layoutPatterns[0].length());

			for (int rowIndex = 0; rowIndex < arrayRowSize; rowIndex++) {
				for (int colIndex = 0; colIndex < arrayRowSize; colIndex++) {
//					inputArray[rowIndex][colIndex] = 0;
					outputArray[rowIndex][colIndex] = MinesweepConstants.LAYOUT;
				}
			}
		} else {
			System.out.println("Please enter a valid layout");
		}
	}

	@Override
	public boolean processUserOption(String option) {

		char flagOrOpen = option.charAt(0);
		int rowIndex = Integer.parseInt(Character.toString(option.charAt(2)));
		int colIndex = Integer.parseInt(Character.toString(option.charAt(4)));

		boolean askForOtherOption = true;
		
		boolean isMine =(flagOrOpen=='o')? checkForMine(rowIndex, colIndex):false;

		if (isMine) {
			printGameOver();
			askForOtherOption = false;
		} else {
			setOutputArray(flagOrOpen, rowIndex, colIndex);
			if (Character.toString(flagOrOpen).equals(MinesweepConstants.OPEN)){
				String key = Integer.toString(rowIndex) + Integer.toString(colIndex);
				if(flagCount.containsKey(key)) {
					if (flagCount.get(key) > 0) {
						flagCount.put(key, 0);
					}
				}
				
				normalCount--;
				askForOtherOption = true;
			} else {
				String key = Integer.toString(rowIndex) + Integer.toString(colIndex);
				flagCount.put(key, 1);
				askForOtherOption = true;
			}
		}

		if (normalCount == 0) {
			int userFlagCount = 0;
			for (Map.Entry<String, Integer> e : flagCount.entrySet()) {
				if (e.getValue() > 0) {
					userFlagCount++;
				}
			}
			if (userFlagCount == mineCount)
				printGameWin();
			else
				askForOtherOption = true;
		}
		return askForOtherOption;
	}

	public void setOutputArray(char flagOrOpen, int rowIndex, int colIndex) {
		outputArray[rowIndex][colIndex] = Character.toString(flagOrOpen);
		displayOutputLayout();
	}

	boolean checkForMine(int rowIndex, int colIndex) {

		if (inputArray[rowIndex][colIndex] == 1)
			return true;
		else
			return false;
	}

	@Override
	public void displayOutputLayout() {

		for (int rowIndex = 0; rowIndex < arrayRowSize; rowIndex++) {
			for (int colIndex = 0; colIndex < arrayRowSize; colIndex++) {
				
				System.out.print(outputArray[rowIndex][colIndex]);
				
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("--------------");

	}

	@Override
	public void printGameOver() {

		System.out.println("Game Over you selected mine");

	}

	@Override
	public void printGameWin() {
		System.out.println("Game Over you have won the game");

		displayOutputLayout();
	}

	public String[] getLayoutPatterns() {
		return layoutPatterns;
	}

	public void setLayoutPatterns(String[] layoutPatterns) {
		this.layoutPatterns = layoutPatterns;
	}

	public int getArrayRowSize() {
		return arrayRowSize;
	}

	public void setArrayRowSize(int arrayRowSize) {
		this.arrayRowSize = arrayRowSize;
	}

	public int[][] getInputArray() {
		return inputArray;
	}

	public void setInputArray(int[][] inputArray) {
		this.inputArray = inputArray;
	}

	public String[][] getOutputArray() {
		return outputArray;
	}

	public void setOutputArray(String[][] outputArray) {
		this.outputArray = outputArray;
	}

}
