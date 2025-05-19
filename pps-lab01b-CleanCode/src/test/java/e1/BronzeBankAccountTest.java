package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BronzeBankAccountTest extends AbstractBankAccountTest {

    private BronzeBankAccount account;

    @BeforeEach
    void init(){
        super.init();
        this.account = new BronzeBankAccount(coreAccount);
    }

    @Test
    public void testCanWithdraw() {
        // With Fee
        this.account.deposit(1000);
        this.account.withdraw(200);
        assertEquals(799, this.account.getBalance());

        // Without Fee
        this.account.withdraw(50);
        assertEquals(749, this.account.getBalance());
    }

    @Test
    public void testCannotWithdrawMoreThanAvailable(){
        this.account.deposit(1000);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(1200));
    }
}
