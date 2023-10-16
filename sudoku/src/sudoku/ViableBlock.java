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

public class ViableBlock implements Callable<Integer> {
	private int[][] grid;
	private int blockNum;
	public ViableBlock(int[][] grid, int blockNum) {
		this.grid=grid;
		this.blockNum=blockNum;
	}
	public Integer call() {
		int resultBlock = 0;
		Set<Integer> setBlock = new HashSet<Integer>();
		int blockRow=blockNum/3;
	    for (int i = 3*blockRow; i < 3*blockRow+3; i++) {
	    	for (int j = 3*blockNum; j < 3*blockNum+3; j++) {
	    		if (grid[i][j%9]>=1 && grid[i][j%9]<=9) 
	    			setBlock.add(grid[i][j%9]);
	    	}
	    }
	    	long threadID = Thread.currentThread().getId()%11 +1;
	    	System.out.print("[Thread " + threadID + "]");
	    	if(setBlock.size()==9) {
	    		System.out.println("Subgrid R" + 3*blockRow + (3*blockRow+1) + (3*blockRow+2) + 
	    						   "C" + 3*blockNum%9 + (3*blockNum%9+1) + (3*blockNum%9+2)
	    							+ ": valid");
	    		resultBlock += 1;
	    	}
	    	else 
	    		System.out.println("Subgrid R" + 3*blockRow + (3*blockRow+1) + (3*blockRow+2) + 
						   "C" + 3*blockNum%9 + (3*blockNum%9+1) + (3*blockNum%9+2)
							+ ": invalid");
	    
	    return resultBlock;
	}
}
/*
(0,0)	(0,1)	(0,2)				(0,3)	(0,4)	(0,5)
(1,0)	(1,1)	(1,2)				(1,3)	(1,4)	(1,5)
(2,0)	(2,1)	(2,2)				(2,3)	(2,4)	(2,5)
*/