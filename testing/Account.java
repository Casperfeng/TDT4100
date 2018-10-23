package testing;
/**
 * 11/4/18
 * @author casperfeng (Kode gitt av øvingsopplegget)
 *
 */
public class Account {

	private double balance;
	private double interestRate;
	private double accuracy = 0.000001;

	public Account(double balance, double interestRate) {
		validateAmount(balance);
		validateInterestRate(interestRate);
		this.balance = balance;
		this.interestRate = interestRate;
	}
	
	/**
	 * Validerer interestraten (må være større enn 0)
	 * @param interestRate
	 */
	private void validateInterestRate(double interestRate) {
		if (interestRate < 0) {
			throw new IllegalStateException("Account can not have negative interest rate");
		}
	}
	
	/**
	 * amount skal være større enn 0
	 * @param amount
	 */
	private void validateAmount(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Negative amount");
		}
	}

	public double getBalance() {
		return this.balance;
	}

	public double getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(double interestRate) {
		validateInterestRate(interestRate);
		this.interestRate = interestRate;
	}

	public void deposit(double amount) {
		validateAmount(amount);
		this.balance += amount;
	}

	public void withdraw(double amount) {
		validateAmount(amount);
		balance -= amount;
	}

	public void addInterest() {
		this.balance += this.balance * this.interestRate;
	}
}
