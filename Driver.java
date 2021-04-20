import java.io.*;
import java.util.*;

public class Driver 
{
	public static void main(String[] args) throws IOException 
	{

		final int MAX_CAP = 101;

		FileWriter writer = new FileWriter("output.txt");
		
		// Reading from file.
		File file1 = new File("data_random.txt");
		Scanner read1 = new Scanner(file1);

		// Create array to contain entries from file.
		int[] randomEntries = new int[MAX_CAP];

		// Read random data into the array.
		int i = -1;
		while (read1.hasNext()) 
		{
			randomEntries[i + 1] = read1.nextInt();
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
		i = -1;
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
		heap1.reheap(1);
		
		

		// Sequential Insertions with random data
		writer.write("Heap built using sequential insertions with random data: " + Arrays.toString(heap1.printTen()) + "\n");
		writer.write("Number of swaps in the heap creation: " + heap1.getReheapCount() + "\n");
		
		for (int j = 0; j < 9; j++)
			heap1.removeMax();
		
		writer.write("Heap after 10 removals: " + Arrays.toString(heap1.printTen()) + "\n");

		writer.write("\n");

		// Optimal method with random data
		writer.write("Heap built using optimal method with random data: " + Arrays.toString(heap2.printTen()) + "\n");
		writer.write("Number of swaps in the heap creation: " + heap2.getReheapCount() + "\n");
		
		for (int j = 0; j < 9; j++)
			heap2.removeMax();
		
		writer.write("Heap after 10 removals: "  + Arrays.toString(heap2.printTen()) + "\n");
		
		writer.write("\n");
		
		// Sequential Insertions with sorted data
		writer.write("Heap built using sequential insertions with sorted data: " + Arrays.toString(heap3.printTen()) + "\n");
		writer.write("Number of swaps in the heap creation: " + heap3.getReheapCount() + "\n");
		
		for (int j = 0; j < 9; j++)
			heap3.removeMax();
		
		writer.write("Heap after 10 removals: "  + Arrays.toString(heap3.printTen()) + "\n");

		writer.write("\n");

		// Optimal method with sorted data
		writer.write("Heap built using optimal method with sorted data: " + Arrays.toString(heap4.printTen()) + "\n");
		writer.write("Number of swaps in the heap creation: " + heap4.getReheapCount() + "\n");
		
		for (int j = 0; j < 9; j++)
			heap4.removeMax();

		writer.write("Heap after 10 removals: "  + Arrays.toString(heap4.printTen()) + "\n");
		
		read1.close();
		read2.close();
		writer.close();
	}
}