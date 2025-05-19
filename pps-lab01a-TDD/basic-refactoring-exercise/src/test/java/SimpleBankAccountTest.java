import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private AccountHolder accountHolder1;
    private AccountHolder accountHolder2;
    private BankAccount bankAccount;
    private final int fee = 1;

    @BeforeEach
    void beforeEach(){
        accountHolder1 = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder1, 0);
        accountHolder2 = new AccountHolder("Maria", "Rossi", 2);
    }

    @Test
    void testInitialBalance() {
        int initial_balance = 0;
        assertEquals(initial_balance, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        int deposit_amount = 100;
        bankAccount.deposit(accountHolder1.getId(), deposit_amount);
        assertEquals(deposit_amount, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        int deposit_amount_account1 = 100;
        int deposit_amount_account2 = 50;
        bankAccount.deposit(accountHolder1.getId(), deposit_amount_account1);
        bankAccount.deposit(accountHolder2.getId(), deposit_amount_account2);
        assertEquals(deposit_amount_account1, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        int deposit_amount = 100;
        int withdraw_amount = 70;
        int expected = deposit_amount - withdraw_amount - this.fee;
        bankAccount.deposit(accountHolder1.getId(), deposit_amount);
        bankAccount.withdraw(accountHolder1.getId(), withdraw_amount);
        assertEquals(expected, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        int deposit_amount = 100;
        int withdraw_amount = 70;
        bankAccount.deposit(accountHolder1.getId(), deposit_amount);
        bankAccount.withdraw(accountHolder2.getId(), withdraw_amount);
        assertEquals(deposit_amount, bankAccount.getBalance());
    }
}
