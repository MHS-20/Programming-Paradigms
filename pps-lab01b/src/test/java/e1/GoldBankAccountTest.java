package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GoldBankAccountTest extends AbstractBankAccountTest {

    private GoldBankAccount account;

    @BeforeEach
    void init() {
        super.init();
        this.account = new GoldBankAccount(coreAccount);
    }

    @Test
    public void testCanWithdraw() {
        this.account.deposit(1000);
        this.account.withdraw(200);
        assertEquals(800, this.account.getBalance());
    }

    @Test
    public void testCanWithdrawMoreThanAvailable() {
        this.account.deposit(1000);
        this.account.withdraw(1500);
        assertEquals(-500, account.getBalance());
    }

    @Test
    public void testCannotWithdrawMoreThanOverdraft() {
        this.account.deposit(1000);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(2000));
    }
}
