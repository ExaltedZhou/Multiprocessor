package sudoku;
/*
Name: Zhou, Albert
Email: azhou2@lsu.edu
Project: PA-1 (Multithreading)
Instructor: Feng Chen
Class: cs4103-sp23
Login ID: cs410388
*/
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;



public class ViableColumn implements Callable<Integer> {
	private int[][] grid;
	public ViableColumn(int[][] grid) {
		this.grid=grid;
	}
	public Integer call() {
		int resultColumn = 0;
		Set<Integer> setRow = new HashSet<Integer>();
	    for (int j = 0; j < grid.length; j++) {
	    	for (int i = 0; i < grid[0].length; i++) {
	    		if (grid[i][j]>=1 && grid[i][j]<=9) 
	    			setRow.add(grid[i][j]);
	    	}
	    	long threadID = Thread.currentThread().getId()%11 +1;
	    	System.out.print("[Thread " + threadID + "]");
	    	if(setRow.size()==9) {
	    		System.out.println("Column" + j + ": valid");
	    		resultColumn += 1;
	    	}
	    	else 
	    		System.out.println("Column" + j + ": invalid");
	    	setRow = new HashSet<Integer>();
	    }
	    return resultColumn;
	}
}