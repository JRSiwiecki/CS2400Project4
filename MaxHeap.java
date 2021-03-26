import java.util.Arrays;
public class MaxHeap<T extends Comparable<? super T>>
		implements MaxHeapInterface<T>
{
	private T[] heap; // Array of heap entries
	private int lastIndex; // Index of last entry
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	public MaxHeap()
	{
		this(DEFAULT_CAPACITY);
	}
	
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
		
		// The cast is safe because the new array contains all null entries
		@SuppressWarnings("unchecked")
		T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
		initialized = true;
	}
}
