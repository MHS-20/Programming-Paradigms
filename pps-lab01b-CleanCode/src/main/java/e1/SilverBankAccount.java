package e1;

public class SilverBankAccount extends AbstractBankAccount{

    public SilverBankAccount(BankAccount ba){
        super(ba);
    }

    public void withdraw(int amount) {
        if (this.getBalance() < amount + 1){
            throw new IllegalStateException();
        }
        base.withdraw(amount + 1);
    }
}
