import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface <T>
{
    private Node top = null;
    private int qtyOfItems;

    /**
     * Adds a new entry to the top of this stack.
     * @param newEntry  An object to be added to the stack.
     */
    public void push(T newEntry)
    {
        Node newStartingNode = new Node (newEntry, top);
        top = newStartingNode;
        qtyOfItems++;
    }

    /**
     * Removes and returns this stack's top entry.
     * @return  The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty before the operation.
     */
    public T pop() {
        if (qtyOfItems == 0)
            throw new EmptyStackException();
        else {
            T popped = top.item;
            top = top.next;
            qtyOfItems --;
            return popped;
        }
    }

    /**
     * Retrieves this stack's top entry.
     * @return  The object at the top of the stack.
     * @throws  EmptyStackException if the stack is empty.
     */
    public T peek()
    {
        if (qtyOfItems == 0)
            throw new EmptyStackException();
        else
            return top.item;
    }

    /**
     * Detects whether this stack is empty.
     * @return  True if the stack is empty.
     */
    public boolean isEmpty()
    {
        if (qtyOfItems == 0)
            return true;
        return false;
    }

    /** Removes all entries from this stack. */
    public void clear()
    {
        for (int i = 1; i <= qtyOfItems; i++) {
            pop();
        }
        top = null;
        qtyOfItems = 0;
    }

    /**
     * Node class given from other projects
     */
    private class Node {
        T item;
        Node next;

        private Node() {
            this(null,null);
        }

        private Node(T item) {
            this(item,null);
        }

        private Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
