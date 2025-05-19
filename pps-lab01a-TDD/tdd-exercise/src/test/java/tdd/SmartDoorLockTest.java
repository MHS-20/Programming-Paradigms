package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLock doorLock;
    private final int pin = 0000;
    private final int MAX_ATTEMPTS = 3;

    @BeforeEach
    void beforeEach() {
        doorLock = new SmartDoorLocking(pin);
    }

    @Test
    public void isInitiallyOpen() {
        assertFalse(doorLock.isLocked());
    }

    @Test
    public void isNotInitiallyBlocked() {
        assertFalse(doorLock.isBlocked());
    }

    @Test
    public void NewPinUnlocks() {
        int pin = 1111;
        doorLock.setPin(pin);
        doorLock.unlock(pin);
        assertFalse(doorLock.isLocked());
    }

    @Test
    public void DoorLocksAfterUnlock() {
        doorLock.lock();
        doorLock.unlock(pin);
        doorLock.lock();
        assertTrue(doorLock.isLocked());
    }

    @Test
    public void systemBlockAfterFailedAttempts() {
        int wrong_pin = 1111;
        for (int i = 0; i < MAX_ATTEMPTS + 5; i++) {
            doorLock.unlock(wrong_pin);
        }
        assertTrue(doorLock.isBlocked());
    }

    @Test
    public void resetAfterSystemBlock() {
        int wrong_pin = 1111;
        for (int i = 0; i < MAX_ATTEMPTS + 5; i++) {
            doorLock.unlock(wrong_pin);
        }

        assertTrue(doorLock.isBlocked());
        doorLock.reset();
        assertAll(
                () -> assertFalse(doorLock.isLocked()),
                () -> assertFalse(doorLock.isBlocked()),
                () -> assertEquals(0, doorLock.getFailedAttempts())
        );
    }


}