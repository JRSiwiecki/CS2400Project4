/**
 * Interface providing the baseline for a max heap.
 * @author Joseph
 *
 * @param <T>
 */
public interface MaxHeapInterface<T extends Comparable<? super T>>
{
	public void add(T newEntry);
	public T removeMax();
	public T getMax();
	public boolean isEmpty();
	public int getSize();
	public void clear();
}
