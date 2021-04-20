import java.io.*;
import java.util.*;

public class Driver 
{
	public static void main(String[] args) throws FileNotFoundException 
	{

		final int MAX_CAP = 101;

		// Reading from file.
		File file1 = new File("data_random.txt");
		Scanner read1 = new Scanner(file1);

		// Create array to contain entries from file.
		int[] randomEntries = new int[MAX_CAP];

		// Read random data into the array.
		int i = 0;
		while (read1.hasNext()) 
		{
			randomEntries[i+1] = read1.nextInt();
			i++;
		}


		// Sequential insertions with random data
		MaxHeap heap1 = new MaxHeap();
		
		// Optimal method with random data
		MaxHeap heap2 = new MaxHeap(randomEntries);
		
		File file2 = new File("data_sorted.txt");
		Scanner read2 = new Scanner(file2);
		
		int[] sortedEntries = new int[MAX_CAP];
		
		// Read sorted data into the array.
		i = 0;
		while (read2.hasNext())
		{
			sortedEntries[i + 1] = read2.nextInt();
			i++;
		}
		
		// Sequential insertions with sorted data
		MaxHeap heap3 = new MaxHeap();
		
		// Optimal method with sorted data
		MaxHeap heap4 = new MaxHeap(sortedEntries);


		// Sequentially adding the random entries to the heap.
		for (int p: randomEntries) 
		{
			heap1.add(p);
		}
		
		// Sequentially adding the sorted entries to the heap.
		for (int p: sortedEntries)
		{
			heap3.add(p);
		}
		
		heap1.reheap(1);

		// Sequential Insertions with random data
		System.out.println("Heap built using sequential insertions with random data: " + Arrays.toString(heap1.printTen()));
		System.out.println("Number of swaps in the heap creation: " + heap1.getReheapCount());
		System.out.println("Heap after 10 removals: ");

		System.out.println();

		// Optimal method with random data
		System.out.println("Heap built using optimal method with random data: " + Arrays.toString(heap2.printTen()));
		System.out.println("Number of swaps in the heap creation: " + heap2.getReheapCount());
		System.out.println("Heap after 10 removals: ");
		
		System.out.println();
		
		// Sequential Insertions with sorted data
		System.out.println("Heap built using sequential insertions with sorted data: " + Arrays.toString(heap3.printTen()));
		System.out.println("Number of swaps in the heap creation: " + heap3.getReheapCount());
		System.out.println("Heap after 10 removals: ");

		System.out.println();

		// Optimal method with sorted data
		System.out.println("Heap built using optimal method with sorted data: " + Arrays.toString(heap4.printTen()));
		System.out.println("Number of swaps in the heap creation: " + heap4.getReheapCount());
		System.out.println("Heap after 10 removals: ");
		
		read1.close();
		read2.close();
	}
}