package encapsulation;

public class Account {
	
	private double balance;
	private double interestRate;
	
	public Account(double startBalance, double startInterestRate) throws IllegalArgumentException {
		if (startBalance < 0) {
			throw new IllegalArgumentException("The balance can't be negative.");
		}
		else if (startInterestRate < 0) {
			throw new IllegalArgumentException("The interestrate can't be negative.");
		}
		else {
			this.balance = startBalance;
			this.interestRate = startInterestRate;
		}
		
		
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public double getInterestRate() {
		return this.interestRate;
	}
	
	public void deposit(double amount) throws IllegalArgumentException{
		if (amount > 0) {
			this.balance += amount;
		}
		else {
			throw new IllegalArgumentException("Amount can't be negative.");
		}
	}
	
	public void withdraw(double amount) throws IllegalArgumentException {
		if (0 > this.balance - amount) {
			throw new IllegalArgumentException("The withdrawal cannot be greater than the current balance.");
		}
		if (amount > 0) {
			this.balance -= amount;
		}
		else {
			throw new IllegalArgumentException("Amount can't be negative.");
		}
	}
	
	public void setInterestRate(double amount) {
		if (amount > 0) {
			this.interestRate = amount;
		}
		else {
			throw new IllegalArgumentException("The interestrate has to be of legal value");
		}
	}
	
	public void addInterest() {
		this.balance += this.balance*this.interestRate*0.01;
	}
	
	
} 
