Name: Zhou, Albert
Email: azhou2@lsu.edu
Project: PA-1 (Multithreading)
Instructor: Feng Chen
Class: cs4103-sp23
Login ID: cs410388

Program was written in java using JavaThread
To compile and run the program under the command line 
	javac Sudoku.java
	java Sudoku (input file path and name) (output file path and name)
	
11 threads run concurrently: one to check viable columns,
							 one to check viable rows,
							 nine to check viable subgrids

Expected input
text file, each entry in a row is separated by spaces
no blank lines between rows
all training white spaces are trimmed

Expected output
Thread Id, row column or subgrid was examined
the validation result of row column or subgrid
the number of valid rows columns subgrid are given
the overall validation result is given
....
[Thread 5]Column7: valid
[Thread 5]Column8: valid
[Thread 6]Subgrid R012C012: valid
[Thread 7]Subgrid R012C345: valid
[Thread 8]Subgrid R012C678: valid
[Thread 9]Subgrid R345C012: valid
[Thread 10]Subgrid R345C345: valid
[Thread 11]Subgrid R345C678: valid
[Thread 1]Subgrid R678C012: valid
[Thread 2]Subgrid R678C345: valid
[Thread 3]Subgrid R678C678: valid
Valid rows: 9
Valid columns: 9
Valid blocks: 9
This sudoku solution is valid
