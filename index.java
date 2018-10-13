package index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;



public class index {
	 private static String[] list1;//array for the each line of the input
	 private static ArrayList<Integer> citations =new ArrayList<Integer>();// used to store the researchers citations 
	 private static ArrayList<Researcher> rlist = new ArrayList<Researcher>();//used to store the researcher objects
	 private static int counter; 
	

	public static void main(String[] args) { 
		System.out.println("This program ranks researchers with respect to their h-index ");
		System.out.println("If you want the program to quit enter an empty line. ");
		Scanner keyboard  = new Scanner(System.in); 
		// sets the string input to the users input (( the whole line)) 
		String input = keyboard.nextLine();
		while (!input.isEmpty()){
			list1=input.split(" ");
			citations.clear();
			int counter2 = 2;
			while (counter2<list1.length) {
				citations.add(Integer.parseInt(list1[counter2]));
				counter2++;
			}
			String name; 
			String last; 
			name = list1[1];
			last = list1[0]; 
			
			citations.sort(Collections.reverseOrder());
			int h = Hindex(citations); 
			
			
			Researcher person =  new Researcher(last, name , h); 
			
			rlist.add(person);
		
			input = keyboard.nextLine();
		}
		
		 Collections.sort(rlist, new Comparator<Researcher>() {
				@Override
				public int compare(Researcher arg0, Researcher arg1) {
					// TODO Auto-generated method stub
					int x =arg1.getHindex()-arg0.getHindex();
					if(x==0) {
						
						x=arg0.getLast().compareToIgnoreCase(arg1.getLast());
						if(x==0) {
							
							x=arg0.getFirst().compareToIgnoreCase(arg1.getFirst());
		}
						
		}
					
					return x;
		}
		});
		 
		 
		 
		 while (counter<rlist.size()) {
			 System.out.print("Researcher name: ");
			 System.out.print(rlist.get(counter).getLast());
			 System.out.print(", ");
			 System.out.print(rlist.get(counter).getFirst());
			 System.out.print(" - H-index: ");
			 System.out.print(rlist.get(counter).getHindex());
			 System.out.println();
			 
			 counter ++;
		 }
		
		
	System.out.println("Program execution ended.");	
	keyboard.close();
	System.exit(0);
	
	
	
	
}

	
	private static class Researcher{
		private String first;
		private String last;
		private int hindex; 
		
		//constructor 
		public Researcher(String lastname , String firstname, int index) {
			this.setFirst(firstname); 
			this.setLast(lastname); 
			this.setHindex(index); 	
		}
		//getter and setting methods for the researcher class 
		public String getFirst() {
			return first;
		}
		public void setFirst(String first) {
			this.first = first;
		}
		public String getLast() {
			return last;
		}
		public void setLast(String last) {
			this.last = last;
		}
		public int getHindex() {
			return hindex;
		}
		public void setHindex(int hindex) {
			this.hindex = hindex;
		}
		
	}
	
	
	// method to find the H-index based on my algorithm
	public static int Hindex(ArrayList<Integer> arrayList){
		int low = 0; 
		int high = arrayList.size() - 1; 
		int result = 0; 
		while (low <= high) {
			int index = Math.floorDiv((high + low),  2);
			if (arrayList.get(index) >= (index + 1)) {
				result = index + 1; 
				low = index + 1; 	
			}
			else
				high = index - 1; 
						
	}
	return result; 
}
} 