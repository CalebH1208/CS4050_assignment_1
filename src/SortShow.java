/**
 *
 * @author Ouda
 */

//importing the libraries that will be needed in this program

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Random;

//The class that has all the sorts in it
public class SortShow extends JPanel { 

	
		// An array to hold the lines_lengths to be sorted
		public int[] lines_lengths;
		//The amount of lines needed
		public final int total_number_of_lines = 256;
		 // An array to holds the scrambled lines_lengths
		public int[] scramble_lines;
		//A temp Array that is used later for sorts
		public int[] tempArray;
		
		//the default constructor for the SortShow class
		public SortShow(){
			//assigning the size for the lines_lengths below
			lines_lengths = new int[total_number_of_lines];
			for(int i = 0; i < total_number_of_lines; i++) 
				lines_lengths[i] =  i+5;
			
		}
		

		//A method that scrambles the lines
		public void scramble_the_lines(){
			//A random generator
			Random num = new Random(); 
			//Randomly switching the lines
			for(int i = 0; i < total_number_of_lines; i++){
				//getting a random number using the nextInt method (a number between 0 to i + 1)
				int j = num.nextInt(i + 1); 
				//swapping The element at i and j 
				swap(i, j);
			}
			//assigning the size for the scramble_lines below
			scramble_lines = new int[total_number_of_lines];
			//copying the now scrambled lines_lengths array into the scramble_lines array 
			//to store for reuse for other sort methods
			//so that all sort methods will use the same scrambled lines for fair comparison 
			for (int i = 0; i < total_number_of_lines; i++)
			{
				scramble_lines[i] = lines_lengths[i];
			}
			//Drawing the now scrambled lines_lengths
			paintComponent(this.getGraphics());
		}
		
		//Swapping method that swaps two elements in the lines_lengths array
		public void swap(int i, int j){
			//storing the i element in lines_lengths in temp
			int temp = lines_lengths[i];
			//giving i element in lines_lengths the value of j element in lines_lengths
			lines_lengths[i] = lines_lengths[j];
			//giving j element in lines_lengths the value of temp
			lines_lengths[j] = temp;
		}
		
		//The selectionSort method
		public void SelectionSort(){
			//getting the date and time when the selection sort starts
			Calendar start = Calendar.getInstance();
			//Using the selection sort to lines_lengths sort the array

			//Iterate over every line, swapping with the next smallest item
			for (int i = 0; i < total_number_of_lines - 1; i++)
			{
				//Find next smallest
				int next_smallest = getIndexOfSmallest(i, total_number_of_lines - 1);
				//Swap it
				swap(i, next_smallest);
				//Paint to screen
				paintComponent(this.getGraphics());
			}

			//getting the date and time when the selection sort ends
			Calendar end = Calendar.getInstance();
			//getting the time it took for the selection sort to execute 
			//subtracting the end time with the start time
	        SortGUI.selectionTime = end.getTime().getTime() - start.getTime().getTime();
		}
		
		//this method gets the smallest element in the array of lines_lengths
		public int getIndexOfSmallest(int first, int last){
			int min_value = lines_lengths[first];
			int min_index = first;
			for (int i = first + 1; i <= last; i++) {
				//if current element is less than minimum value, replace min_value and min_index
				if (lines_lengths[i] < lines_lengths[min_index])
				{
					min_value = lines_lengths[i];
					min_index = i;
				}
				//Paint to screen
				paintComponent(this.getGraphics());
				//Include necessary delay
				delay(1);
			}
			return min_index;
		}
		
	///////////////////////////////////////////////////////////////////////////////////
		
		//recursive merge sort method
		public void R_MergeSort(){
			//getting the date and time when the recursive merge sort starts
			Calendar start = Calendar.getInstance();
			//assigning the size for the tempArray below
			tempArray = new int[total_number_of_lines];

			R_MergeSort(0,total_number_of_lines-1);
			//You need to complete this part.

			Calendar end = Calendar.getInstance();
			//getting the time it took for the iterative merge sort to execute
			//subtracting the end time with the start time
	        SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();
			
		}
		
		//recursive merge sort method
		public void R_MergeSort(int first, int last){
			//Computer center of passed elements
			int center = first + ((last-first)/2);

			//if not in base case, perform a recursive sort on each half and merge them together
			if(first < last) {
				R_MergeSort(first, center);
				R_MergeSort(center + 1, last);
				R_Merge(first, center, last);
				//Paint to screen
				paintComponent(this.getGraphics());
				//Include necessary delay
				delay(1);
			}
		}

		
		//recursive merge sort method
		public void R_Merge(int first, int mid, int last){

			//You need to complete this part.
			int BH1 = first; // beggining of first half
			int EH1 = mid;	 // End of first half
			int BH2 = mid+1; // beggining of second half
			int EH2 = last; // end of second half

			int index = BH1;
			for(; (BH1 <=EH1) && (BH2 <= EH2);index++){
				if(lines_lengths[BH1] < lines_lengths[BH2]){
					tempArray[index] = lines_lengths[BH1];
					BH1++;
				}
				else{
					tempArray[index] = lines_lengths[BH2];
					BH2++;
				}
			}
			for(; BH1 <= EH1;BH1++,index++){
				tempArray[index] = lines_lengths[BH1];
			}
			for(; BH2 <= EH2;BH2++,index++){
				tempArray[index] = lines_lengths[BH2];
			}
			for(int i = first;i <=last;i++){
				lines_lengths[i] = tempArray[i];
			}
			//Paint to screen
			paintComponent(this.getGraphics());
			//Include necessary delay
			delay(1);
		}

	//////////////////////////////////////////////////////////////////////////////////////////
		
	//iterative merge sort method
	public void I_MergeSort()
	{
		//getting the date and time when the iterative merge sort starts
		Calendar start = Calendar.getInstance();
		//assigning the size for the tempArray below
		tempArray = new int[total_number_of_lines]; 
		//saving the value of total_number_of_lines
		int beginLeftovers = total_number_of_lines;

		
		for (int segmentLength = 1; segmentLength <= total_number_of_lines/2; segmentLength = 2*segmentLength)
		{
			beginLeftovers = I_MergeSegmentPairs(total_number_of_lines, segmentLength);
			int endSegment = beginLeftovers + segmentLength - 1;
			if (endSegment < total_number_of_lines - 1) 
			{
			I_Merge(beginLeftovers, endSegment, total_number_of_lines - 1);
			}
		} 

		// merge the sorted leftovers with the rest of the sorted array
		if (beginLeftovers < total_number_of_lines) {
			I_Merge(0, beginLeftovers-1, total_number_of_lines - 1);
		}
		//getting the date and time when the iterative merge sort ends
		Calendar end = Calendar.getInstance();
		//getting the time it took for the iterative merge sort to execute 
		//subtracting the end time with the start time
	    SortGUI.imergeTime = end.getTime().getTime() - start.getTime().getTime();
	} 

	// Merges segments pairs (certain length) within an array 
	public int I_MergeSegmentPairs(int l, int segmentLength)
	{
		//The length of the two merged segments 

		//You suppose  to complete this part (Given).
		int mergedPairLength = 2 * segmentLength;
		int numberOfPairs = l / mergedPairLength;

		int beginSegment1 = 0;
		for (int count = 1; count <= numberOfPairs; count++)
		{
			int endSegment1 = beginSegment1 + segmentLength - 1;

			int beginSegment2 = endSegment1 + 1;
			int endSegment2 = beginSegment2 + segmentLength - 1;
			I_Merge(beginSegment1, endSegment1, endSegment2);

			beginSegment1 = endSegment2 + 1;
			//redrawing the lines_lengths
			paintComponent(this.getGraphics());
			//Causing a delay for 10ms
			delay(1);
		}
		// Returns index of last merged pair
		return beginSegment1;
		//return 1;//modify this line
	}

	public void I_Merge(int first, int mid, int last)
	{
		//You suppose  to complete this part (Given).
		// Two adjacent sub-arrays
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;

		// While both sub-arrays are not empty, copy the
		// smaller item into the temporary array
		int index = beginHalf1; // Next available location in tempArray
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++)
		{
			// Invariant: tempArray[beginHalf1..index-1] is in order
			if (lines_lengths[beginHalf1] < lines_lengths[beginHalf2])
			{
				tempArray[index] = lines_lengths[beginHalf1];
				beginHalf1++;
			}
			else
			{
				tempArray[index] = lines_lengths[beginHalf2];
				beginHalf2++;
			}
		}
		//redrawing the lines_lengths
		paintComponent(this.getGraphics());
		delay(1);

		// Finish off the nonempty sub-array

		// Finish off the first sub-array, if necessary
		for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
			// Invariant: tempArray[beginHalf1..index-1] is in order
			tempArray[index] = lines_lengths[beginHalf1];

		// Finish off the second sub-array, if necessary
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
			// Invariant: tempa[beginHalf1..index-1] is in order
			tempArray[index] = lines_lengths[beginHalf2];

		// Copy the result back into the original array
		for (index = first; index <= last; index++)
			lines_lengths[index] = tempArray[index];
	}

	//////////////////////////////////////////////////////////////////////

	public void IncrementalInsertionSort(int first, int last, int space)
	{
		//Declare variables
		int unsorted, i;
		//Iterate over unsorted section
		for (unsorted = first + space; unsorted <= last; unsorted = unsorted + space)
		{
			int first_unsorted_element = lines_lengths[unsorted];
			//For loop to perform incremental insertions
			for (i = unsorted - space; (i >= first) && (first_unsorted_element < lines_lengths[i]); i = i - space)
			{
				lines_lengths[i + space] = lines_lengths[i];
				//Paint to screen
				paintComponent(this.getGraphics());
				//Include necessary delay
				delay(1);
			}
			lines_lengths[i + space] = first_unsorted_element;
		}
		paintComponent(this.getGraphics());
	}

	public void ShellSort(){
		//getting the date and time when the shell sort starts
		Calendar start = Calendar.getInstance();
		//Using the shell sort to lines_lengths sort the array

		//Perform the shell sort
		ShellSort(0, total_number_of_lines-1);

		Calendar end = Calendar.getInstance();
		//getting the time it took for the shell sort to execute
		//subtracting the end time with the start time
		SortGUI.shellTime = end.getTime().getTime() - start.getTime().getTime();
	}

	public void ShellSort(int first, int last)
	{
		//Find number of elements
		int array_elements = last - first + 1;
		//For each section of the array
		for (int space = array_elements / 2; space > 0; space = space / 2)
		{
			//Perform an incremental insertion sort on each section
			for (int i = first; i < first + space; i++)
			{
				IncrementalInsertionSort(i, last, space);
			}
		}
	}
	//////////////////////////////////////////////////////////////////////

	public void BubbleSort(){
		//getting the date and time when the bubble sort starts
		Calendar start = Calendar.getInstance();
		//Using the bubble sort to lines_lengths sort the array

		//For each element from the end to the start
		for(int lastIndex = total_number_of_lines-1; lastIndex > 0; lastIndex--){
			//For each element from the start to the current location
			for(int index = 0; index < lastIndex; index++){
				//Swap the elements if necessary
				order_Elements(lines_lengths, index, index + 1);
				//Paint to screen
				paintComponent(this.getGraphics());
				//Include necessary delay
				delay(1);
			}
		}
		//getting the date and time when the bubble sort ends
		Calendar end = Calendar.getInstance();
		//getting the time it took for the bubble sort to execute
		//subtracting the end time with the start time
		SortGUI.bubbleTime = end.getTime().getTime() - start.getTime().getTime();
	}

	public void order_Elements(int[] a, int i, int j){
		//If the elements need to swap, swap them
		if (a[i] > a[j]) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}

	////////////////////////////////Quick sort ////////////////////////////

	public void QuickSort(){
		//getting the date and time when the recursive merge sort starts
		Calendar start = Calendar.getInstance();

		//Perform quicksort
		QuickSort(0,total_number_of_lines);

		Calendar end = Calendar.getInstance();
		//getting the time it took for the iterative merge sort to execute
		//subtracting the end time with the start time
		SortGUI.quickTime = end.getTime().getTime() - start.getTime().getTime();

	}

	public void QuickSort(int first, int last){
		//If more tha five elements, perform recursive quicksort
		if(first + 5 <= last) {
			//Calculate pivot, i, j
			int pivot = lines_lengths[last - 1];
			int i = first -1;
			int j = last -1;

			//Enter loop until it is sorted
			while (true) {
				//Find the first value below pivot
				while (lines_lengths[++i] < pivot) ;
				//Find the first value above pivot
				while (--j >= first && lines_lengths[j] > pivot) ;

				//Condition to exit the partition step of the quicksort
				if (i >= j) break;
				//Else, swap elements
				swap(i, j);
				//Paint to screen
				paintComponent(this.getGraphics());
				//Include necessary delay
				delay(1);
			}
			//Move pivot to correct location
			swap(i, last - 1);
			//Perform recursive quick sort on each half
			QuickSort(first, i);
			QuickSort(i + 1, last);
		}
		//Base case of the quicksort
		else{
			//Perform an insertion sort below 5 elements (for efficiency)
			InsertionSort(first,last);
		}
		//Paint to screen
		paintComponent(this.getGraphics());
	}

	//////////////////////////////////// insertion sort ////////////////

	public void InsertionSort(){
		//getting the date and time when the recursive merge sort starts
		Calendar start = Calendar.getInstance();
		//assigning the size for the tempArray below
		tempArray = new int[total_number_of_lines];


		InsertionSort(0,total_number_of_lines-1);
		//You need to complete this part.

		Calendar end = Calendar.getInstance();
		//getting the time it took for the iterative merge sort to execute
		//subtracting the end time with the start time
		SortGUI.insertionTime = end.getTime().getTime() - start.getTime().getTime();

	}

	public void InsertionSort(int first, int last){
		//For a range within the array (passed)
		for(int p = first+1;p<= last && p < total_number_of_lines;p++){
			//Set current item
			int tmp = lines_lengths[p];
			int j;
			//For each item in the range from sorted section till correct location is found
			for(j = p;j > first && tmp < lines_lengths[j-1];j--){
				//Swap the elements
				lines_lengths[j] = lines_lengths[j-1];
				//Paint to screen
				paintComponent(this.getGraphics());
				//Include necessary delay
				delay(1);
			}
			lines_lengths[j]=tmp;
		}
		//Paint to screen
		paintComponent(this.getGraphics());
	}

	//////////////////////////////////Radix Sort////////////////////////////////////
	//method that returns maximum element of lines_lengths
	public int GetMaximumElement(int first, int last)
	{
		//Iterate over all elements from first to last and find the maximum value
		int max = lines_lengths[first];
		for (int i = first + 1; i < last; i++)
		{
			//if a new max value is found, update it
			if (lines_lengths[i] > max)
			{
				max = lines_lengths[i];
			}
		}
		return max;
	}
	//counting sort method used in radix sort
	public void CountingSort(int tens){
		//bucket array
		int[] count = new int[10];
		//sorted array result
		int[] result = new int[total_number_of_lines];

		//iterate through each lines_lengths element and counts occurrences of each base10 number
		for (int i = 0; i < total_number_of_lines; i++)
		{
			int bucket = (lines_lengths[i] / tens) % 10;
			count[bucket]++;
		}

		//change count array to show element placement in result array
		for (int i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}

		//placing sorted lines_lengths elements into result array
		for (int i = total_number_of_lines - 1; i >= 0; i--)
		{
			int digit = (lines_lengths[i] / tens) % 10;
			result[count[digit] - 1] = lines_lengths[i];
			count[digit]--;
		}

		for (int i = 0; i < total_number_of_lines; i++) {
			lines_lengths[i] = result[i];
			//Paint to screen
			paintComponent(this.getGraphics());
			//Include necessary delay
			delay(1);
		}
	}

	//radix sort wrapper
	public void RadixSort(){
		//getting the date and time when the radix sort starts
		Calendar start = Calendar.getInstance();

		//perform RadixSort
		RadixSort(0, total_number_of_lines-1);

		Calendar end = Calendar.getInstance();
		//getting the time it took for the radix sort to execute
		//subtracting the end time with the start time
		SortGUI.radixTime = end.getTime().getTime() - start.getTime().getTime();
	}

	//radix sort method
	public void RadixSort(int first, int last){
		//get the largest element in lines_lengths
		int max_element = GetMaximumElement(first, last);
		//loop through each power of 10 of the max_element
		for (int i = 1; max_element / i > 0; i *= 10)
		{
			//perform CountingSort
			CountingSort(i);
			//Paint to screen
			paintComponent(this.getGraphics());
			//Include necessary delay
			delay(1);
		}
	}

	//////////////////////////////////////////////////////////////////////	

		
		//This method resets the window to the scrambled lines display
		public void reset(){
			if(scramble_lines != null)
			{
				//copying the old scrambled lines into lines_lengths
				for (int i = 0; i < total_number_of_lines; i++)
				{
					lines_lengths[i] = scramble_lines[i] ;
				}
			//Drawing the now scrambled lines_lengths
			paintComponent(this.getGraphics());
		}
			}

		//This method colours the lines and prints the lines
		public void paintComponent(Graphics g){
 			super.paintComponent(g);
			//A loop to assign a colour to each line
			for(int i = 0; i < total_number_of_lines; i++){
				//using eight colours for the lines
				if(i % 8 == 0){
					g.setColor(Color.green);
				} else if(i % 8 == 1){
					g.setColor(Color.blue);
				} else if(i % 8 == 2){
					g.setColor(Color.yellow);
				} else if(i%8 == 3){
					g.setColor(Color.red);
				} else if(i%8 == 4){
					g.setColor(Color.black);
				} else if(i%8 == 5){
					g.setColor(Color.orange);
				} else if(i%8 == 6){
					g.setColor(Color.magenta);
				} else
					g.setColor(Color.gray);
				
				//Drawing the lines using the x and y-components 
				g.drawLine(4*i + 25, 300, 4*i + 25, 300 - lines_lengths[i]);
			}
			
		}
		
		//A delay method that pauses the execution for the milliseconds time given as a parameter
		public void delay(int time){
			try{
	        	Thread.sleep(time);
	        }catch(InterruptedException ie){
	        	Thread.currentThread().interrupt();
	        }
		}
		
	}

