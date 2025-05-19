package tdd;

/**
 * Task 1 - TDD for Smart Door Lock
 * Represents a Smart Door Lock with PIN authentication and a lock state.
 * The door lock can be locked, unlocked, and reset.
 * If too many failed attempts occur, the lock goes into a "blocked" state.
 * In such state, the door cannot be unlocked until the lock is reset.
 */
public interface SmartDoorLock {

    /**
     * Sets a new PIN for the door lock.
     * You can set only when the system is open (not locked or blocked).
     * @param pin The new 4-digit PIN.
     */
    void setPin(int pin);

    /**
     * Attempts to unlock the door with a given PIN.
     * If the PIN is correct, the door unlocks.
     * If incorrect, the failed attempts counter increases.
     * If too many failed attempts occur, the lock goes into a "blocked" state.
     *
     * @param pin The PIN entered by the user.
     */
    void unlock(int pin);

    /**
     * Locks the door.
     * If the pin is not set, an exception is thrown.
     *
     */
    void lock();

    /**
     * Checks if the door is currently locked.
     *
     * @return true if the door is locked, false otherwise.
     */
    boolean isLocked();

    /**
     * Checks if the lock is in a blocked state due to too many failed attempts.
     *
     * @return true if the lock is blocked, false otherwise.
     */
    boolean isBlocked();

    /**
     * Gets the maximum number of unlock attempts before the lock goes into a blocked state.
     *
     * @return The maximum number of attempts.
     */
    int getMaxAttempts();

    /**
     * Gets the number of failed unlock attempts.
     *
     *
     * @return The number of failed attempts.
     */
    int getFailedAttempts();

    /**
     * Resets the door lock.
     * This completely resets the lock, including the PIN, failed attempts, and blocked state.
     * It sets the lock to the initial state (open).
     */
    void reset();
}