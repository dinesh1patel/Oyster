package oystercardsimulator.model;

public class Card {

	private float balance;

	public Card(float f) {
		this.balance = f;
	}

	public Card() {

	}

	public float getBalance() {
		return balance;
	}

	public void deductFromBalance(float amount) {
		if (balance < amount) {
			throw new RuntimeException("Insufficient funds");
		} else {
			balance -= amount;
		}
		
	}

	public void TopUp(float amount) {
		this.balance += amount;
	}

	public void add(float amount) {
		this.balance += amount;
	}

}
