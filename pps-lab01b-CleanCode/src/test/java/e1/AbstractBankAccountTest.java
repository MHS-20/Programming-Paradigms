package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractBankAccountTest {
    protected BankAccount coreAccount;

    @BeforeEach
    void init(){
        this.coreAccount = new CoreBankAccount();
    }

    @Test
    public void testInitiallyEmpty() {
        assertEquals(0, this.coreAccount.getBalance());
    }

    @Test
    public void testCanDeposit() {
        this.coreAccount.deposit(1000);
        assertEquals(1000, this.coreAccount.getBalance());
    }

    @Test
    public abstract void testCanWithdraw();
}