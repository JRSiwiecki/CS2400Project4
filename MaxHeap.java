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
	
	public T getMax()
	{
		checkInitialization();
		T root = null;
		if (!isempty())
		{
			root = heap[1];
		}
		return root;
	}
	
	public int getSize()
	{
		return lastIndex;
	}
	
	public void clear()
	{
		checkInitialization();
		while (lastIndex > -1)
		{
			heap[lastIndex] = null;
			lastIndex--;
		}
		lastIndex = 0;
	}
	
	public void add(T newEntry)
	{
		checkInitialization(); // Ensure initialization of data fields
		int newIndex = lastIndex + 1;
		int parentIndex = newIndex / 2;
		while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
		{
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
		}
		
		heap[newIndex] = newEntry;
		lastIndex++;
		ensureCapacity();
	}
	
	public T removeMax()
	{
		checkInitialization(); // Ensure initialization of data fields
		T root = null;
		
		if (!isEmpty())
		{
			root = heap[1];				// Return value
			heap[1] = heap[lastIndex];  // Form a semiheap
			lastIndex--;				// Decrease size
			reheap(1);					// Transform to a heap
		}
		
		return root;		
	}
}
