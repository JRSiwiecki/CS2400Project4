import java.util.Arrays;

/**
 * MaxHeap class implemented with an array.
 * @author Joseph
 */
public class MaxHeap implements MaxHeapInterface
{
	private int[] heap; // Array of heap entries
	private int lastIndex; // Index of last entry
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	private int reheapCount = 0;
	
	/**
	 * Constructs a MaxHeap with the default capacity.
	 */
	public MaxHeap()
	{
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Constructs a MaxHeap with a given capacity.
	 * @param initialCapacity The provided capacity.
	 */
	public MaxHeap(int initialCapacity)
	{
		// Is initialCapacity too small?
		if (initialCapacity < DEFAULT_CAPACITY)
		{
			initialCapacity = DEFAULT_CAPACITY;
		}
		
		// Is initialCapacity too big?
		else
		{
			checkCapacity(initialCapacity);
		}
		
		int[] tempHeap = new int[initialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
		initialized = true;
	}
	
	/**
	 * Constructs a MaxHeap using reheap when given an array of entries.
	 * @param entries The array of entries.
	 */
	public MaxHeap(int[] entries)
	{
		this(entries.length + 1); // Call other constructor
		
		assert initialized = true;
		lastIndex = entries.length;
		
		// Copy given array to data field
		for (int index = 0; index < entries.length; index++)
		{
			heap[index] = entries[index];
		}
		
		// Create heap
		for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
		{
			reheap(rootIndex);
		}
	}
	
	@Override
	public void add(int newEntry)
	{
		checkInitialization(); // Ensure initialization of data fields
		int newIndex = lastIndex + 1;
		int parentIndex = newIndex / 2;
		
		while ( (parentIndex > 0) && newEntry < heap[parentIndex] )
		{
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
			reheapCount++;
		}
		
		heap[newIndex] = newEntry;
		lastIndex++;
		ensureCapacity();
	}
	
	@Override
	public int removeMax()
	{
		checkInitialization(); // Ensure initialization of data fields
		int root = 0;
		
		if (!isEmpty())
		{
			root = heap[1];				// Return value
			heap[1] = heap[lastIndex];  // Form a semiheap
			lastIndex--;				// Decrease size
			reheap(1);					// Transform to a heap
		}
		
		return root;		
	}
	
	@Override
	public int getMax()
	{
		checkInitialization();
		int root = 0;
		if (!isEmpty())
		{
			root = heap[1];
		}
		return root;
	}
	
	@Override
	public boolean isEmpty() 
	{
		return lastIndex == 0;
	}
	
	@Override
	public int getSize()
	{
		return lastIndex;
	}
	
	@Override
	public void clear()
	{
		checkInitialization();
		
		while (lastIndex > -1)
		{
			heap[lastIndex] = 0;
			lastIndex--;
		}
		
		lastIndex = 0;
	}
	
	/**
	 * The smarter way of constructing a max-heap, by 
	 * repeatedly using reheap, which is faster than adding the 
	 * entries by size the normal way.
	 * @param rootIndex The index to reheap.
	 */
	public void reheap(int rootIndex)
	{
		boolean done = false;
		int orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;
		
		while (!done && (leftChildIndex <= lastIndex))
		{
			int largerChildIndex = leftChildIndex; // Assume larger
			int rightChildIndex = leftChildIndex + 1;
			
			if ((rightChildIndex <= lastIndex) && 
					heap[rightChildIndex] > heap[largerChildIndex])
			{
				largerChildIndex = rightChildIndex;
			}
			
			if (orphan < heap[largerChildIndex])
			{
				heap[rootIndex] = heap[largerChildIndex];
				rootIndex = largerChildIndex;
				leftChildIndex = 2 * rootIndex;
				reheapCount++;
			}
			
			else 
			{
				done = true;
			}
		}
		
		heap[rootIndex] = orphan;
	}
	
	/**
	 * If we have the space to do so, ensure capacity for new elements
	 * by doubling the size of the array.
	 */
	private void ensureCapacity()
	{
		if (lastIndex >= heap.length - 1)
		{
			int newLength = 2 * heap.length;
			checkCapacity(newLength);
			heap = Arrays.copyOf(heap, newLength);
		}
	}
	
	/**
	 * Checks to see if we are over the max capacity allocated.
	 * @param capacity The capacity to check.
	 */
	private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
        {
            throw new IllegalStateException("Attempt to create a bag whose " +
                                        "capacity exceeds allowed " +
                                        "maximum of " + MAX_CAPACITY);
        }
    }	
	
	/**
	 * Ensures that the heap has been initialized.
	 */
	private void checkInitialization()
    {
        if (!initialized)
        {
            throw new SecurityException("ResizeableArrayBag object is corrupt.");
        }
    }
	
	/**
	 * To array.
	 * @return The array.
	 */
	public int[] toArray()
	{
		int[] copy = new int[heap.length];
		copy = heap;
		return copy;
	}
	
	/**
	 * Returns first ten integers of MaxHeap.
	 * @return Array of first ten integers.
	 */
	public int[] printTen()
	{
		int[] ten = new int[10];
		int[] copy = toArray();
		
		for (int i = 1; i < 10; i++)
		{
			ten[i] = copy[i];
		}
		
		return ten;
	}
	
	/**
	 * Returns number of reheaps.
	 * @return The number of reheaps.
	 */
	public int getReheapCount()
	{
		return reheapCount;
	}
}
