package stateandbehavior;

public class Account {
	
	private double balance;
	private double interestRate;
	
	public Account() {
		this.balance = 0;
		this.interestRate = 0;
	}
	
	public Account(double balanceAtStart) {
		this.balance = balanceAtStart;
		this.interestRate = 0;
	}
	
	public Account(double balanceAtStart, double interestRateAtStart) {
		this.balance = balanceAtStart;
		this.interestRate = interestRateAtStart;
	}
	
	public void deposit(double amount) {
		if (amount>0) {
			this.balance += amount;
		}
	}
	
	public void addInterest() {
		this.balance += (this.balance*this.interestRate)*0.01;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public double getInterestRate() {
		return this.interestRate;
	}
	
	public void setInterestRate(double newInterestRate) {
		this.interestRate = newInterestRate;
	}
	
	public String toString() {
		return ("Balance: " + this.balance + ", InterestRate: " + this.interestRate);
	}
	
}
