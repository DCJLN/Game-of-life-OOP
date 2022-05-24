import java.util.Random;

public class Grid {
	private int gridSize;
	@SuppressWarnings("unused")
	private int squareSize;
	private Cell cells[][];
	
	public Grid(int gridSize, int squareSize) {
		this.gridSize = gridSize;
		this.squareSize = squareSize;
		cells = new Cell[gridSize][gridSize];
		
		// Random cell generation in the center square
		int squareLimit = (gridSize - squareSize)/2;
		Random rand = new Random();
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				int draw = rand.nextInt(2);
				if(j >= squareLimit 
						&& j < (gridSize - squareLimit)
						&& i >= squareLimit 
						&& i < (gridSize - squareLimit)
						&& draw == 1) {
					if (j == 0 || j == gridSize - 1) {
						cells[i][j] = new BorderCell(true);
					} else {
						cells[i][j] = new Cell(true);
					}
				} else {
					if (j == 0 || j == gridSize - 1) {
						cells[i][j] = new BorderCell(false);
					} else {
						cells[i][j] = new Cell(false);
					}
				}
			}
		}
	}
	
	
	/*
	 * Method that displays the grid with * for living cells and - for dead cells.
	 */
	public void displayGrid() {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				String tempStr = "";
				if (j == 0) {
					tempStr += "[+";
					tempStr += String.format("%3d", ((BorderCell) cells[i][j]).getBirthCounter()) + "/-";
					tempStr += String.format("%3d", ((BorderCell) cells[i][j]).getKillCounter()) + "]";
				}
				
				if(cells[i][j].isAlive() == false) {
					tempStr += " -";
				} else {
					tempStr += " *";
				}
				
				if(j == gridSize - 1) {
					tempStr += "[+";
					tempStr += String.format("%3d", ((BorderCell) cells[i][j]).getBirthCounter()) + "/-";
					tempStr += String.format("%3d", ((BorderCell) cells[i][j]).getKillCounter()) + "]";
					tempStr += " \n";
				}
				
				System.out.print(tempStr);
			}
		}
	}
	
	
	/*
	 * Method that computes the number of neighbors for each cell in the grid.
	 */
	public void computeNeighbors(){
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				cells[i][j].setNbNeighbors(0);
				for(int k = i - 1; k < i + 2; k++) {
					for(int l = j - 1; l < j + 2; l++) {
						if(k == -1 
								|| k >= gridSize - 1  // Pas correct -  à changer. Devrait être K > gridSize - 1
								|| l == -1 
								|| l >= gridSize - 1
								|| (k == i && l == j)) {
							continue;
						} else {
							if(cells[k][l].isAlive() == true) {
								cells[i][j].incrementNbNeighbors();	
							}
						}
					}
				}
			}
		}
	}
	
	/*
	 * Method that kill or give birth to a cell depending on its number of neighbors.
	 */
	public void cellsGod() {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				if(cells[i][j].isAlive() == true 
						&& (cells[i][j].getNbNeighbors() <= 1 || cells[i][j].getNbNeighbors() >= 4)) {
					cells[i][j].setAlive(false);
					if (cells[i][j] instanceof BorderCell) {
						((BorderCell) cells[i][j]).incrementKillCounter();
					}
				} else if(cells[i][j].isAlive() == false && cells[i][j].getNbNeighbors() == 3) {
					cells[i][j].setAlive(true);
					if (cells[i][j] instanceof BorderCell) {
						((BorderCell) cells[i][j]).incrementBirthCounter();;
					}
				}
			}
		}
	}
	
}
