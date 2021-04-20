import java.io.*;
import java.util.*;

public class Driver 
{
	public static void main(String[] args) throws FileNotFoundException 
	{

		final int MAX_CAP = 101;

		// Reading from file.
		File file = new File("data.txt");
		Scanner readIn = new Scanner(file);

		// Create array to contain entries from file.
		int [] entries = new int[MAX_CAP];

		// Read data into the array.
		int i = 0;
		while (readIn.hasNext()) 
		{
			entries [i+1] = readIn.nextInt();
			i++;
		}


		// Sequential insertions
		MaxHeap myHeap = new MaxHeap();
		
		// Optimal method
		MaxHeap myHeap2 = new MaxHeap(entries);


		// Sequentially adding elements to the heap.
		for (int p: entries) 
		{
			myHeap.add(p);
		}
		
		myHeap.reheap(1);

		// Sequential Insertions
		System.out.println("Heap built using sequential insertions: " + Arrays.toString(myHeap.printTen()));
		System.out.println("Number of swaps in the heap creation: " );
		System.out.println("Heap after 10 removals: " );

		System.out.println();

		// Optimal method 
		System.out.println("Heap built using optimal method: " + Arrays.toString(myHeap2.printTen()));
		System.out.println("Number of swaps in the heap creation: " + myHeap2.getReheapCount());
		System.out.println("Heap after 10 removals: " );
		
		readIn.close();
	}
}