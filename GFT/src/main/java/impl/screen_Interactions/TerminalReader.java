package impl.screen_Interactions;

import java.util.Scanner;

public class TerminalReader {
	Scanner screenReader = new Scanner(System.in);

	public int getIntegerFromTerminal() {
	return screenReader.nextInt();
	}
	
	public double getDoubleFromTerminal() {
		return screenReader.nextDouble();
	}
	
	public void closeScanner() {
		screenReader.close();
	}
	
}
