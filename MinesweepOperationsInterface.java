package project1;

public interface MinesweepOperationsInterface {

	public void handleMinefieldLayout(String layout);

	public boolean processUserOption(String option);

	public void displayOutputLayout();

	public void printGameOver();

	public void printGameWin();
}
