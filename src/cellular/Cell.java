package cellular;
public class Cell {
	public final int MAX = 8;
	
	private boolean isAlive;
	private boolean neighbors[];
	
	public Cell (boolean isAlive) {
		this.isAlive = isAlive;
		neighbors = new boolean[MAX];
		for (int i = 0; i < MAX; i++) {
			neighbors[i] = false;
		}
	}

	public boolean isAliveNext () {
		int nc = getNeighborCount();
		if (isAlive()) {
			if (nc <= 1)
				return false;
			else if (nc == 2 || nc == 3)
				return true;
			else
				return false;
		} else {
			return (nc == 3);
		}
			
	}
	
	public boolean isAlive () {
		return isAlive;
	}
	
	public void setIsAlive (boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public void setNeighbor (int i, boolean cell) {
		neighbors[i] = cell;
	}
	private int getNeighborCount() {
		int count = 0;
		for (int i = 0; i < neighbors.length; i++)
			if (neighbors[i])
				count++;
		return count;
	}
}
