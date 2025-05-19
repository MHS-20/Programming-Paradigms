package tdd;

public class SmartDoorLocking implements SmartDoorLock {

    private int pin;
    private boolean locked;
    private boolean systemBlock;
    private int failedAttempts;
    final private int MAX_ATTEMPTS = 3;

    public SmartDoorLocking() {
        this.locked = false;
        this.systemBlock = false;
        this.failedAttempts = 0;
    }

    public SmartDoorLocking(int pin) {
        this.pin = pin;
        this.locked = false;
        this.systemBlock = false;
        this.failedAttempts = 0;
    }

    @Override
    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (this.pin == pin && failedAttempts < MAX_ATTEMPTS){
            locked = false;
            failedAttempts = 0;
            return;
        }

        if (failedAttempts < MAX_ATTEMPTS){
            this.failedAttempts++;
        }
        else {
            this.systemBlock = true;
        }
    }

    @Override
    public void lock() {
        this.locked = true;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean isBlocked() {
        return systemBlock;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return failedAttempts;
    }

    @Override
    public void reset() {
        this.locked = false;
        this.systemBlock = false;
        this.failedAttempts = 0;
    }
}
