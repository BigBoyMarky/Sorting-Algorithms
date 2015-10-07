/*
 * 
 * I used the following:
 * http://www.youtube.com/watch?v=P00xJgWzz2c
 * as a reference and help in understanding bubble sort
 * 
 */

import java.io.*;
import java.util.*;

public class BubbleSort {

	private static int comparisons = 0;
	private static int swaps = 0;
	
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
		array = bubbleSort(array);
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
	
	public static int[] bubbleSort(int[] data) {
		
		if (data.length < 2) {
			
			return data;
			
		}
		
		int toMove;
		
		for (int i = 0; i < data.length; i++) {
			
			for (int j = 0; j < data.length - 2 - i; j++) {

				if (data[j] > data[j + 1]) {
					
					toMove = data[j];
					data[j] = data[j + 1];
					data[j + 1] = toMove;
					
					swaps++;
					
				}
				
				comparisons++;
				
			}
			
		}
		
		return data;
		
	}

}