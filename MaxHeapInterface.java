/**
 * Interface providing the baseline for a max heap.
 * @author Joseph
 *
 * @param <T> Generic data type.
 */
public interface MaxHeapInterface<T extends Comparable<? super T>>
{
	/**
	 * Adds an entry to the heap.
	 * @param newEntry The entry to add.
	 */
	public void add(T newEntry);
	
	/**
	 * Removes the max (the root).
	 * @return The entry removed.
	 */
	public T removeMax();
	
	/**
	 * Retrieves the max (the root).
	 * @return The max entry.
	 */
	public T getMax();
	
	/**
	 * Checks if the heap is empty.
	 * @return True if heap is empty, false otherwise.
	 */
	public boolean isEmpty();
	
	/**
	 * Gets current size of heap.
	 * @return The size of the heap.
	 */
	public int getSize();
	
	/**
	 * Clears the heap of all entries.
	 */
	public void clear();
}
