/**
 * Interface providing the baseline for a max heap.
 * @author Joseph
 */
public interface MaxHeapInterface
{
	/**
	 * Adds an entry to the heap.
	 * @param newEntry The entry to add.
	 */
	public void add(int newEntry);
	
	/**
	 * Removes the max (the root).
	 * @return The entry removed.
	 */
	public int removeMax();
	
	/**
	 * Retrieves the max (the root).
	 * @return The max entry.
	 */
	public int getMax();
	
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
