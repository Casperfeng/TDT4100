package stateandbehavior;

public class Digit {
	
	int tallsystem;
	int siffer = 0;
	
	int getValue() {
		return siffer;
	}
	
	boolean increment() {
		if (siffer + 1 == tallsystem) {
			siffer = 0;
			return true;
		} else {
			siffer = siffer + 1;
			return false;
		}
	}
	
	
	String numbersAndAlphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public String toString() {
		return String.valueOf(numbersAndAlphabet.charAt(siffer));
	}

	public Digit(int tallsystem) {
		this.tallsystem = tallsystem;
	}
}