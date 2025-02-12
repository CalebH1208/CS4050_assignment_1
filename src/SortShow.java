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

			//You need to complete this part.

			for (int i = 0; i < total_number_of_lines - 1; i++)
			{
				int next_smallest = getIndexOfSmallest(i, total_number_of_lines - 1);
				swap(i, next_smallest);
			}

			//getting the date and time when the selection sort ends
			Calendar end = Calendar.getInstance();
			//getting the time it took for the selection sort to execute 
			//subtracting the end time with the start time
	        SortGUI.selectionTime = end.getTime().getTime() - start.getTime().getTime();
		}
		
		//this method gets the smallest element in the array of lines_lengths
		public int getIndexOfSmallest(int first, int last){
			//You need to complete this part.
			int min_value = lines_lengths[first];
			int min_index = first;
			for (int i = first + 1; i <= last; i++) {
				//if current element is less than minimum value, replace min_value and min_index
				if (lines_lengths[i] < lines_lengths[min_index])
				{
					min_value = lines_lengths[i];
					min_index = i;
				}
			}
			return min_index; //modify this line
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
			int center = first + ((last-first)/2);

			if(first < last) {
				R_MergeSort(first, center);
				R_MergeSort(center + 1, last);
				R_Merge(first, center, last);
				//Causing a delay for 10ms
				delay(10);
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
			paintComponent(this.getGraphics());
		}
		
		//

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
			delay(10);
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
		//paintComponent(this.getGraphics());

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

	void IncrementalInsertionSort(int first, int last, int space)
	{
		int unsorted, i;
		for (unsorted = first + space; unsorted <= last; unsorted = unsorted + space)
		{
			int first_unsorted_element = lines_lengths[unsorted];
			for (i = unsorted - space; (i >= first) && (first_unsorted_element < lines_lengths[i]); i = i - space)
			{
				lines_lengths[i + space] = lines_lengths[i];
			}
			lines_lengths[i + space] = first_unsorted_element;
		}
	}


	public void ShellSort(int first, int last)
	{
		int array_elements = last - first + 1;
		for (int space = array_elements / 2; space > 0; space = space / 2)
		{
			for (int i = first; i < first + space; i++)
			{
				incrementalInsertionSort(i, last, space);
			}
		}
	}
	//////////////////////////////////////////////////////////////////////

	public void BubbleSort(){
		//getting the date and time when the selection sort starts
		Calendar start = Calendar.getInstance();
		//Using the bubble sort to lines_lengths sort the array

		for(int lastIndex = total_number_of_lines-1; lastIndex > 0; lastIndex--){
			for(int index = 0; index < lastIndex; index++){
				order_Elements(lines_lengths, index, index + 1);
				paintComponent(this.getGraphics());
				delay(0);
			}
		}
		//getting the date and time when the bubble sort ends
		Calendar end = Calendar.getInstance();
		//getting the time it took for the bubble sort to execute
		//subtracting the end time with the start time
		SortGUI.bubbleTime = end.getTime().getTime() - start.getTime().getTime();
	}

	public void order_Elements(int[] a, int i, int j){
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
		//assigning the size for the tempArray below
		tempArray = new int[total_number_of_lines];

		QuickSort(0,total_number_of_lines-1);
		//You need to complete this part.

		Calendar end = Calendar.getInstance();
		//getting the time it took for the iterative merge sort to execute
		//subtracting the end time with the start time
		SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();

	}

	public void QuickSort(int first, int last){
		int pivot = lines_lengths[last -1];
		int i = first;
		int j = last -1;

		if(first == last)return;

		for(;;){
			while(lines_lengths[++i] < pivot){}
			while(lines_lengths[--j]> pivot){}
			if(i < j) swap(i,j);
			else break;
		}
		swap(i,last-1);
		QuickSort(first,i -1);
		QuickSort(i+1,last);
	}


	//recursive merge sort method

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

