import java.util.EmptyStackException;

public class ArrayStack<T> implements StackInterface <T>
{
    public static final int minSize = 50;
    public static final int maxSize = 10000;
    private T[] stack = (T[]) new Object[minSize];
    private int qtyOfItems = 0;

    /**
     * Adds a new entry to the top of this stack.
     * @param newEntry  An object to be added to the stack.
     */
    public void push(T newEntry)
    {
        // If array has reached the max size then won't push
        if (qtyOfItems != maxSize) {
            // Checking that array is not full
            if (qtyOfItems == minSize)
                resizeStack();

            stack[qtyOfItems] = newEntry;
            qtyOfItems++;
        }
    }


    /**
     * If array is full then double the size
     * Continues to double as needed until array equals maxSize
     */
    private void resizeStack()
    {
        // Store the current contents of the array
        T[] tempArray = stack;

        // Set array to a new empty array that is either maxSize or double previous size
        stack = (T[]) new Object[Math.min(qtyOfItems * 2, maxSize)];

        // Copy data from the original array into the new larger array starting at array[0]
        System.arraycopy(tempArray, 0, stack, 0, qtyOfItems);
    }


    /**
     * Removes and returns this stack's top entry.
     * @return  The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty before the operation.
     */
    public T pop()
    {
        try
        {
            // return top element and set to null
            qtyOfItems--;
            T popped = stack[qtyOfItems];
            stack[qtyOfItems] = null;
            return popped;

        } catch (ArrayIndexOutOfBoundsException stackEmpty) {
            System.out.println("Error: Stack is empty, 'pop' cannot be executed");
            return null;
        }
    }

    /**
     * Retrieves this stack's top entry.
     * @return  The object at the top of the stack.
     * @throws  EmptyStackException if the stack is empty.
     */
    public T peek()
    {
        try
        {
            // return top element
            return stack[qtyOfItems--];

        } catch (ArrayIndexOutOfBoundsException stackEmpty) {
            System.out.println("Error: Stack is empty, 'peek' cannot be executed");
        }
        return null;
    }

    /**
     * Detects whether this stack is empty.
     * @return  True if the stack is empty.
     */
    public boolean isEmpty()
    {
        if (qtyOfItems > -1)
            return true;
        return false;
    }

    /** Removes all entries from this stack. */
    public void clear()
    {
        for (int i = 0; i < qtyOfItems; i++) {
            stack[i] = null;
            qtyOfItems = 0;
        }
    }
}
