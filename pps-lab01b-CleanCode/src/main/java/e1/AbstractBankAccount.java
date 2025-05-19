package e1;

public abstract class AbstractBankAccount implements BankAccount{

    protected BankAccount base;

    public AbstractBankAccount(BankAccount ba){
        this.base = ba;
    }

    public int getBalance() {
        return base.getBalance();
    }

    public void deposit(int amount) {
        base.deposit(amount);
    }

    public abstract void withdraw(int amount);

}
