package cellular;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CellWorld {
	private int row;
	private int column;
	
	private Cell world[][];
	
	public CellWorld (int r, int c) {
        row = r;
        column = c;
        Scanner scan = null;
        try {
        	scan = new Scanner(new File("data.txt"));
        	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		world = new Cell[r][c];

		for (int i = 0; i < r; i++) {
			Scanner scanner = new Scanner(scan.nextLine());
		    scanner.useDelimiter(" ");
			for (int j = 0; j < c; j++) {
				String value = scanner.next().trim();
				if (value.equals("0")) {
                world[i][j] = new Cell(false);
				} else {
					world[i][j] = new Cell(true);
				}
            }
        }
		scan.close();
        
  /*      for (int i=0; i < nc; i++) { 
            int rr = 0;
            int rc = 0;
            do {
                rr = (int)(Math.random() * row);
                rc = (int)(Math.random() * column);
            } while (world[rr][rc].isAlive());
            world[rr][rc].setIsAlive(true);
        }*/
	} 
	
	
	public void setCell (int r, int c, Cell cell) {
		world[r][c] = cell;
	}
	
	public Cell getCell (int r, int c) {
		return world[r][c];
	}

    public void nextIteration () {
		updateNeighbors();
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (world[i][j].isAliveNext())
					world[i][j].setIsAlive(true);
				else
					world[i][j].setIsAlive(false);
			}
		}
			
		
	}

    /***
     *   7 0 1
     *   6 C 2
     *   5 4 3
     */
	public void updateNeighbors() {
		for (int i = 0; i < row; i++)
			for (int j = 0; j < column; j++) {
                // System.out.println(i + "," + j);
				Cell cell = world[i][j];
				// start at 12 oclock cell, clockwise
				
				if (i != 0) // there is top
					cell.setNeighbor(0, world[i-1][j].isAlive());
					 
				if ((i != 0) && j != (column - 1))
					cell.setNeighbor(1, world[i-1][j+1].isAlive());
					
				if (j != column - 1)
					cell.setNeighbor(2, world[i][j+1].isAlive());
					
				if ((i != row - 1) && (j != column - 1))
					cell.setNeighbor(3, world[i+1][j+1].isAlive());
					
				if (i != row - 1)
					cell.setNeighbor(4, world[i+1][j].isAlive());
					
				if ((i != row - 1) && (j != 0))
                    cell.setNeighbor(5, world[i+1][j-1].isAlive());
					

                if (j != 0)
					cell.setNeighbor(6, world[i][j-1].isAlive());
					
				if (i != 0 && j != 0)
					cell.setNeighbor(7, world[i-1][j-1].isAlive());
			}
	}

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void print() {
    	for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (world[i][j].isAlive())
					System.out.print (" + ");
				else
					System.out.print (" X ");
			}
            System.out.println();
		}

    }
}
