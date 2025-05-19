package e1;

public class GoldBankAccount extends AbstractBankAccount {

    public GoldBankAccount(BankAccount ba) {
       super(ba);
    }

    public void withdraw(int amount) {
        if (this.getBalance() + 500 < amount)
            throw new IllegalStateException();
        base.withdraw(amount);
    }
}