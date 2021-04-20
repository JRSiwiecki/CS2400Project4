import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * MaxHeap class implemented with an array.
 * @author Joseph
 *
 * @param <T> Generic data type.
 */
public class MaxHeap<T extends Comparable<? super T>>
		implements MaxHeapInterface<T>
{
	private T[] heap; // Array of heap entries
	private int lastIndex; // Index of last entry
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
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
		
		// The cast is safe because the new array contains all null entries
		@SuppressWarnings("unchecked")
		T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
		initialized = true;
	}
	
	/**
	 * Constructs a MaxHeap using reheap when given an array of entries.
	 * @param entries The array of entries.
	 */
	public MaxHeap(T[] entries)
	{
		this(entries.length); // Call other constructor
		assert initialized = true;
		
		// Copy given array to data field
		for (int index = 0; index < entries.length; index++)
		{
			heap[index + 1] = entries[index];
		}
		
		// Create heap
		for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
		{
			reheap(rootIndex);
		}
	}
	
	@Override
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
	
	@Override
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
	
	@Override
	public T getMax()
	{
		checkInitialization();
		T root = null;
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
			heap[lastIndex] = null;
			lastIndex--;
		}
		lastIndex = 0;
	}
	
	/**
	 * Method to write elements of MaxHeap to a file.
	 * @param fileName The file to be written to.
	 */
	public void writeToFile(String fileName)
	{
		try 
		{
			File file = new File(fileName);
			FileWriter writer = new FileWriter(file);
			
			for (int i = 0; i < lastIndex; i++)
			{
				writer.write(heap[i] + "\n");
			}
			
			writer.close();
		} 
		
		catch (IOException e)
		{
			System.out.println("ERROR: Unable to write to file.");
		}
	}
	
	/**
	 * The smarter way of constructing a max-heap, by 
	 * repeatedly using reheap, which is faster than adding the 
	 * entries by size the normal way.
	 * @param rootIndex The index to reheap.
	 */
	private void reheap(int rootIndex)
	{
		boolean done = false;
		T orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;
		
		while (!done && (leftChildIndex <= lastIndex))
		{
			int largerChildIndex = leftChildIndex; // Assume larger
			int rightChildIndex = leftChildIndex + 1;
			
			if ((rightChildIndex <= lastIndex) && 
					heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
			{
				largerChildIndex = rightChildIndex;
			}
			
			if (orphan.compareTo(heap[largerChildIndex]) < 0)
			{
				heap[rootIndex] = heap[largerChildIndex];
				rootIndex = largerChildIndex;
				leftChildIndex = 2 * rootIndex;
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
}
