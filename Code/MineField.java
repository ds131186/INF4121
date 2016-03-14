import java.util.Random;

class MineField{

	private boolean[][] mines,visible;
	private boolean boom;
	private final int rowMax = 5;// row size
	private final int colMax = 10;	//column size
	
	/*Constructor*/
	MineField(){
		mines=new boolean[rowMax][colMax];
		visible=new boolean[rowMax][colMax];
		boom=false;
		initMap();
		mapInit();		
	}	
	
	/*it generates 15 mines at random places */
	private void mapInit(){
		int counter=15;//number of mines
		int randomRow,randomCol;
		Random RGenerator=new Random();//random generator for placing the mines in random positions.
		while(counter>0){	
			randomRow=Math.abs(RGenerator.nextInt()%rowMax);
			randomCol=Math.abs(RGenerator.nextInt()%colMax);
			//if method try move returns a true value then the counter is reduced
			if(trymove(randomRow,randomCol)){
				counter--;
			}
		}	
	}
	
	/*It generates and table for row and column and assigns mines and visible false values*/
	private void initMap(){
		for(int row=0;row<rowMax;row++){
			for(int col=0;col<colMax;col++){
				mines[row][col]=false;
				visible[row][col]=false;
			}
		}
	}
	/*Here if the random generator makes a same combination of row and col for mine then that is not accepted and a false value is returned   */
	private boolean trymove(int randomRow, int randomCol) {
		if(mines[randomRow][randomCol]){
			return false;
		}
		else{
			mines[randomRow][randomCol]=true;
			return true;
		}
	}
	/* it returns the boom value which can be true or false*/
	public boolean getBoom(){	
		return boom;
	}
	/*it checks whether the provided input is valid in terms of rows and columns */
	public boolean legalMoveString(String input) {
		String[] separated=input.split(" ");
		int row;
		int col;
		try{	
			row=Integer.parseInt(separated[0]);
			col=Integer.parseInt(separated[1]);
			if(row<0||col<0||row>=rowMax||col>=colMax){
				throw new java.io.IOException();
			}
		}
		catch(Exception e){
			System.out.println("\nInvalid Input!");
			return false;
		}
		
		if(legalMoveValue(row,col)){
			return true;	
		}
		else{
			return false;
		}
	}
	/*Checks whether the user has made a valid move*/
	private boolean legalMoveValue(int row, int col) {
		if(visible[row][col]){
			System.out.println("You stepped in already revealed area!");
			return false;
		}
		else{
			visible[row][col]=true;
		}
		if(mines[row][col]){
			boom();
			return false;
		}
		return true;
	}
	/*it checks if the area provided by user has mines then it returns true value*/
	private void boom() {
		for(int row=0;row<rowMax;row++){
			boomCondition(row);
		}
		boom=true;
		show();		
	}
	/*this loop is the internal condition of the boom method, made to reduce the depth*/
	private void boomCondition(int row){
		for(int col=0;col<colMax;col++){
			if(mines[row][col]){
				visible[row][col]=true;
			}
		}	
	}
	/*It prints the minesweeper table */	
	public void show() {
		System.out.println("\n    0 1 2 3 4 5 6 7 8 9 ");
		System.out.println("   ---------------------");
		for(int row=0;row<rowMax;row++){
			System.out.print(row+" |");
			for(int col=0;col<colMax;col++){
				System.out.print(" "+drawChar(row,col));	// call to draw char
			}
			System.out.println(" |");
		}
		System.out.println("   ---------------------");
	}
	//  This method draws the refreshed table each time the user provides the input
	private char drawChar(int row, int col) {
		int count;
		if(visible[row][col])
		{
			if(mines[row][col]) 
				return '*';
			count = countNumber(row, col);
		}
		else{
			if(boom){
				return '-';
			}
				return '?';	
		}
		return filterNum(count);
	}
	//This method count the number of mines surrounding a particular spot
	private int countNumber(int row, int col){
		int count = 0;
		for(int irow=row-1;irow<=row+1;irow++)
		{
				count = countNumberCont(count, irow, col );	
		}
		return count;
	}
	//This method is a part of the countNumber() splitted up to reduce the depth of the program
	private int countNumberCont(int count, int irow, int col){
		for(int icol=col-1;icol<=col+1;icol++)
		{
			if(icol>=0&&icol<colMax&&irow>=0&&irow<rowMax)
			{
				if(mines[irow][icol]) 
				count++;
			}
		}
		return count;
	}
	//This method returns the number for the counted neighbouring mine
	private char filterNum(int count){
		switch(count){
		case 0:return '0';
		case 1:return '1';
		case 2:return '2';
		case 3:return '3';
		case 4:return '4';
		case 5:return '5';
		case 6:return '6';
		case 7:return '7';
		case 8:return '8';
		
		default:return 'X';
		}	
	}	
}
