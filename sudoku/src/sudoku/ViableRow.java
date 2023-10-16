package sudoku;
/*
Name: Zhou, Albert
Email: azhou2@lsu.edu
Project: PA-1 (Multithreading)
Instructor: Feng Chen
Class: cs4103-sp23
Login ID: cs410388
*/
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

public class ViableRow implements Callable<Integer> {
	private int[][] grid;
	public ViableRow(int[][] grid) {
		this.grid=grid;
	}
	public Integer call() {
		int resultRow = 0;
		Set<Integer> setRow = new HashSet<Integer>();
	    for (int i = 0; i < grid.length; i++) {
	    	for (int j = 0; j < grid[0].length; j++) {
	    		if (grid[i][j]>=1 && grid[i][j]<=9) 
	    			setRow.add(grid[i][j]);
	    	}
	    	long threadID = Thread.currentThread().getId()%11 +1;
	    	System.out.print("[Thread " + threadID + "]");
	    	if(setRow.size()==9) {
	    		System.out.println("Row" + i + ": valid");
	    		resultRow += 1;
	    	}
	    	else 
	    		System.out.println("Row" + i + ": invalid");
	    	setRow = new HashSet<Integer>();
	    }
	    return resultRow;
	}
}
