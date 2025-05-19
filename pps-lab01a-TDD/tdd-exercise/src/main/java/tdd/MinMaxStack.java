package tdd;

/**
 * Task 2 - TDD for Min Max Stack
 * A stack that supports retrieving the minimum and maximum values in constant time.
 * A stack is a data structure that allows adding and removing elements in a last-in-first-out (LIFO) manner.
 * Therefore, giving a stack [1, 2, 3], the first element to be removed is 3, then 2, and finally 1.
 * When adding elements, like 4, the stack becomes [1, 2, 3, 4].
 * NB!! You should not call Collections.min or Collections.max to get the min/max values.
 */
public interface MinMaxStack {

    /**
     * Pushes an integer onto the stack.
     *
     * @param value The integer to push.
     */
    void push(int value);

    /**
     * Removes and returns the top element of the stack.
     *
     * @return The popped element.
     * @throws IllegalStateException if the stack is empty.
     */
    int pop();

    /**
     * Retrieves, but does not remove, the top element of the stack.
     *
     * @return The top element of the stack.
     * @throws IllegalStateException if the stack is empty.
     */
    int peek();

    /**
     * Gets the minimum value currently in the stack.
     *
     * @return The minimum value in the stack.
     * @throws IllegalStateException if the stack is empty.
     */
    int getMin();

    /**
     * Gets the maximum value currently in the stack.
     *
     * @return The maximum value in the stack.
     * @throws IllegalStateException if the stack is empty.
     */
    int getMax();

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Gets the number of elements currently in the stack.
     *
     * @return The size of the stack.
     */
    int size();
}