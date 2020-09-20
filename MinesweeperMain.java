package project1;

//import main.java.com.minesweeper.MinesweepOperationsImpl;

public class MinesweeperMain {

	MinesweeperMain(){
		
	}
	public static void main(String args[]) {
		MinesweepOperationsInterface mineSweepOperation = new MinesweepOperationsImpl();
		System.out.println("Enter the minefield layout :: ");

		try {
			String layout = "xxm,xmx,xxx";
			mineSweepOperation.handleMinefieldLayout(layout);
			boolean askForOther = true;
			String[] inputArray = { "f(0,0)", "f(0,1)", "f(1,1)", "f(1,0)", 
					"f(2,0)", "f(1,2)","o(0,2)","o(2,2)","o(2,1)" };
//			while(true){
				System.out.println("processing");
				// { { 1, 1, 0 }, { 1, 1, 1 }, { 1, 0, 0 } };
				for (int input = 0; input < inputArray.length; input++) {
					System.out.println(inputArray[input]);
					askForOther = mineSweepOperation.processUserOption(inputArray[input]);
					if (!askForOther)
						break;
				}
				
//			}
			
		} catch (Exception e) {
			System.out.println("Exception occcured ::" + e);
		}

	}
}
