/*
 * 
 * I used the following
 * http://fpl.cs.depaul.edu/jriely/class/300/publish/extras/princeton/23Quicksort.pdf
 * as a reference and help in understanding the quick sort algorithm
 */

import java.io.*;
import java.util.*;

public class QuickSort {

	private static int comparisons;
	private static int swaps;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int[] array = null;
		int numElements = 0;
		int counter = 0;
		
		System.out.println("Enter the file name: ");
		String filename = sc.nextLine();
		
		try {
			
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			while ((strLine = br.readLine()) != null) {
				
				if (numElements == 0) {
				
					numElements = Integer.parseInt(strLine);
					array = new int[numElements];
				}
				
				else {
						
					array[counter] = Integer.parseInt(strLine);
					counter++;
						
				}
				
			}
			
			in.close();
			
		} catch (Exception e) {
			
			System.err.println("Error: " + e.getMessage());
			
		}

		long start = System.currentTimeMillis();
		array = quickSort(array);
		long end = System.currentTimeMillis();
		long time = end - start;
		
		for (int i = 0; i < array.length; i++) {
			
			System.out.println(array[i]);
			
		}
		
		System.out.println("Runtime: " + time);
		System.out.println("Comparisons: " + comparisons);
		System.out.println("Swaps: " + swaps);


		try{
			 
			FileWriter fstream = new FileWriter("quicksort.txt");
			BufferedWriter out = new BufferedWriter(fstream);
		  
			for (int i = 0; i < array.length; i++) {
			  
				out.write(array[i] + "\n");
				out.newLine();
			  
			}
		  
			out.write("Runtime: " + time);
			out.newLine();
			out.write("Comparisons: " + comparisons);
			out.newLine();
			out.write("Swaps: " + swaps);
	
			out.close();
	  
		}catch (Exception e) {
		  
			System.err.println("Failed to write");
		  
		}

	}
	
	public static int[] quickSort(int[] data) {
		
		if (data.length < 2) {

			return data; 
			
		}
		
		int[] left = new int[data.length];
		int[] right = new int[data.length];
		int[] sorted = new int[data.length];
		int pivot = data[data.length / 2];
		int leftCounter = 0;
		int rightCounter = 0;
		
		for (int i = 0; i < data.length; i++) {
			
			if (i != data.length / 2) {
				
				if (data[i] < pivot) {
					
					left[leftCounter] = data[i];
					leftCounter++;
					
				}
				
				else {
					
					right[rightCounter] = data[i];
					rightCounter++;
					
				}
				
				swaps++;
				
			}
			
			comparisons++;
			
		} // end for
		
		int[] sortedLeft = new int[leftCounter];
		int[] sortedRight = new int[rightCounter];
		
		for (int i = 0; i < leftCounter; i++) {
			
			sortedLeft[i] = left[i];
			
		}
		
		for (int i = 0; i < rightCounter; i++) {
			
			sortedRight[i] = right[i];
			
		}
		
		sortedLeft = quickSort(sortedLeft);
		sortedRight = quickSort(sortedRight);
		
		System.arraycopy(sortedLeft, 0, sorted, 0, leftCounter);
		sorted[leftCounter] = pivot;
		System.arraycopy(sortedRight, 0, sorted, leftCounter + 1, rightCounter);
		
		return sorted;
		
	}
		
		
		
}