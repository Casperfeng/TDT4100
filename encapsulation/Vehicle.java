package encapsulation;



public class Vehicle {
	
	private final char vehicleType;
	private final char fuelType;
	private String regNumber;
	
	public Vehicle(char vehicleType, char fuelType, String regNumber) {
		
		this.vehicleType = vehicleType;
		this.fuelType = fuelType;
		this.regNumber = regNumber;
		
		if (!validateVehicle()) {
			throw new IllegalArgumentException();
		} else if (!validateFuel()) {
			throw new IllegalArgumentException();
		} else if (!validateRegNumber(regNumber)) {
			throw new IllegalArgumentException();
		}
	}
	
	public boolean validateVehicle() {
		
		return (vehicleType == 'M' || vehicleType == 'C');
	}
	
	private boolean validateFuel() {
		
		if ("HEDG".indexOf(fuelType) == -1) {
			return false;
		} else if (fuelType == 'H' && vehicleType != 'C') {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validateRegNumber(String regnummer) {
		String[] skiltbokstaver = {"EL", "EK", "HY"};
		boolean verdi = true;
		int counterInt = 0;
		int counterLet = 0;
		for (int i = 0; i < regnummer.length(); i++) {
			
			char letter = regnummer.charAt(i);
			
			if (i == 2 && counterInt > 0) {
				verdi = false;
				break;
			}
			
			if (letter > 48 && letter < 58) {
				counterInt++;
			} else if (letter > 64 && letter < 91) {
				counterLet++;
			}
			
			if (letter == 'Æ' || letter == 'Ø' || letter == 'Å') {
				verdi = false;
				break;
			}
		}
		
		if (counterLet != 2) {
			verdi = false;
		}
		
		if ((vehicleType == 'C' && counterInt != 5) || (vehicleType == 'M' && counterInt != 4)) {
			verdi = false;
		}
		
		if ((fuelType == 'E' && !(regnummer.startsWith("EL"))) && (fuelType == 'E' && !(regnummer.startsWith("EK")))) {
			verdi = false;
		} else if (fuelType == 'H' && !regnummer.startsWith("HY")) {
			verdi = false;
		}
		
		for (int i = 0; i < skiltbokstaver.length;i++) {
			if ((fuelType == 'D' || fuelType == 'G') && regnummer.contains(skiltbokstaver[i])) {
				return false; 
			}
		}
		return verdi;
	}
	
	public char getFuelType() {
		return this.fuelType;
	}
	
	public String getRegistrationNumber() {
		return this.regNumber;
	}
	
	public void setRegistrationNumber(String regnummer) {
		
		if (validateRegNumber(regnummer)) {
			this.regNumber = regnummer;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public char getVehicleType() {
		return this.vehicleType;
	}
}
