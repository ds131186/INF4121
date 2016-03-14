import java.util.Scanner;

public class Ranking{

	private final int MAX_PEOPLE_LIMIT=5;
	private String[] name;
	private int[] record;
	private int last;
	//constructor
	Ranking(){
		name=new String[MAX_PEOPLE_LIMIT];//name is the array of string values which is limit upto 5
		record=new int[MAX_PEOPLE_LIMIT];//record are the result associated with different user 
		last=0; // number of users
	}

	public void show() {
		if(last==0){
			System.out.println("Still no results");
			return;
		}
		System.out.println("N Name\t\t Result");// prints the table of the users
		for(int i=0;i<last;i++){
			System.out.println((i+1)+" "+name[i]+"\t"+record[i]); // fetches the name and its corresponding value
		}
	}
	//this method takes the result as a parameter and save it with the name provided by the user
	public void recordName(int result) {
		System.out.print("\nPlease enter your name -");
		Scanner in=new Scanner(System.in);//input name
		String newName=in.nextLine();
		record(result, newName);// call to record method
		sort();// call to sort method
		show();// call to show method
	}
	//it inserts the data of the user for first five users and if memory is full then it checks for the top results   
	private void record(int result, String newName){
		if((last==MAX_PEOPLE_LIMIT)&&record[MAX_PEOPLE_LIMIT-1]>result){
			System.out.println("\nSorry you cannot enter top "+(MAX_PEOPLE_LIMIT)+" players");
			return;
		}
		else if(last==MAX_PEOPLE_LIMIT){
			name[MAX_PEOPLE_LIMIT-1]=newName;
				record[MAX_PEOPLE_LIMIT-1]=result;
			
		}
		else{
			name[last]=newName;
				record[last]=result;
					last++;	
		}	
	}
	
	// it sorts the record of the users in decreasing order with regards to user result.	
	private void sort(){
		if(last<2) return;
		boolean unsorted=true;
		while(unsorted){
			unsorted=sortCont();
		}
	}
	//it returns true if the sort is in random order
	private boolean sortCont(){
		boolean unsorted=false;
		for(int i=0;i<(last-1);i++){
			if(record[i+1]>record[i]){
				int swapR=record[i];
					record[i]=record[i+1];
						record[i+1]=swapR;
						String swapN=name[i];
						name[i]=name[i+1];
						name[i+1]=swapN;
						unsorted=true;	
			}
		}
		return unsorted;
	}
}
