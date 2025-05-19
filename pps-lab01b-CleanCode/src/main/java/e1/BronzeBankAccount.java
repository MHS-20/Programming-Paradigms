package e1;

public class BronzeBankAccount extends AbstractBankAccount{

    public BronzeBankAccount(BankAccount ba){
        super(ba);
    }

    public void withdraw(int amount) {
        if (this.getBalance() < amount + 1){
            throw new IllegalStateException();
        }

        if(amount > 100 )
            base.withdraw(amount + 1);
        else
            base.withdraw(amount);
    }

}
