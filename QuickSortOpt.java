/*
 * 
 * I used the following:
 * http://fpl.cs.depaul.edu/jriely/class/300/publish/extras/princeton/23Quicksort.pdf 
 * as a reference and help in understanding the three way partition quick sort method
 * 
 */

import java.io.*;
import java.util.*;

public class QuickSortOpt {
	
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
			
			System.err.println("Failed to read");
			
		}

		long start = System.currentTimeMillis();
		quickSortOpt(array, 0, array.length-1);
		long end = System.currentTimeMillis();
		long time = end - start;
		
		for (int i = 0; i < array.length; i++) {
			
			System.out.println(array[i]);
			
		}
		
		System.out.println("Runtime: " + time);
		System.out.println("Comparisons: " + comparisons);
		System.out.println("Swaps: " + swaps);
		

		try{
	 
			FileWriter fstream = new FileWriter("quicksort_opt.txt");
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
	
	private static void quickSortOpt(int[] a, int low, int high) { 

		if (high <= low) {
			
			return;
			
		}
		
		int lowTwo = low, highTwo = high;
		int value = a[low]; 
		int counter = low; 
		
		while (counter <= highTwo) {
			
			comparisons++;
			
			if (a[counter] < value) {
				
				exch(a, lowTwo++, counter++);
				swaps++;
				
			}
			
			else if (a[counter] > value) {
				
				exch(a, counter, highTwo--);
				swaps++;
				
			}
			
			else counter++;
			
		}
		
		quickSortOpt(a, low, lowTwo - 1); 
		quickSortOpt(a, highTwo + 1, highTwo);
		
	} 
	
    private static void exch(int[] a, int i, int j) {
    	
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
        
    }

}
