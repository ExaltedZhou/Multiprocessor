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
import java.lang.reflect.Array;
import java.lang.System;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Sudoku {
	
	public static void main(String[] args) throws ExecutionException, InterruptedException, FileNotFoundException {
		if(args.length!=2) {
			System.out.println("in & output file path and names in command line");
		}
		FileReader inf = new FileReader(args[0]);
		Scanner s = new Scanner(inf);
		int k = 0;
		int[][] sudokuSolution=new int[9][9];
		while(s.hasNextLine()&&k<9) {
			String[] sudokuRow = s.nextLine().trim().split("\\s");
			if(sudokuRow.length!=9)
				System.err.println("error! each row must have 9 digits");
			for(int j=0; j<sudokuRow.length; j++) {
				sudokuSolution[k][j]=Integer.parseInt(sudokuRow[j]);
			}
			k++;
		}
		if(k!=9) {
			System.err.println("error! each column must have 9 digits");	
		}
		s.close();
		PrintStream out = new PrintStream(new File(args[1]));
		System.setOut(out);
		ExecutorService pool = Executors.newFixedThreadPool(11);
		List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
		tasks.add(new ViableRow(sudokuSolution));
		tasks.add(new ViableColumn(sudokuSolution));
		for(int i=0; i<9; i++) {
			tasks.add(new ViableBlock(sudokuSolution, i));
		}
		int[] result = new int[tasks.size()];
		for(int i=0; i<tasks.size(); i++) {
			Future<Integer> r = pool.submit(tasks.get(i));
			result[i] = r.get();
		}
		pool.shutdown();
		int blockResult = 0;
		for(int i=0; i<9; i++) {
			blockResult += result[i+2];
		}
		int overall = result[0]+result[1]+blockResult;
		System.out.println("Valid rows: " + result[0]);
		System.out.println("Valid columns: " + result[1]);
		System.out.println("Valid blocks: " + blockResult);
		if(overall==27) 
			System.out.println("This sudoku solution is valid");
		else
			System.out.println("This sudoku solution is invalid");


	}
	
	
	

}
