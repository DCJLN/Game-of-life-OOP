
public class GameOfLife {

	public static void main(String[] args) {
		int firstArg;
		int secondArg;
		if(args.length == 2) {
			try {
				firstArg = Integer.parseInt(args[0]);
				secondArg = Integer.parseInt(args[1]);
				if((firstArg < secondArg) || (secondArg <= 0)) {
					System.err.println("The arguments must follow: 0 < Square size <= Grid size. ");
					System.exit(1);
				} else if ((firstArg + secondArg) % 2 != 0) {
					System.err.println("The 2 arguments must have the same parity.");
					System.exit(1);
				} else {
					Grid grid = new Grid(firstArg, secondArg);
					//grid.displayConsoleGrid();
					Gui gui = new Gui(firstArg * 20, grid);
					while(true) {
						grid.computeNeighbors();
						grid.cellsGod();
						try {
							Thread.sleep(1000);
							
						} catch(InterruptedException ex) {
							System.err.println(ex.getMessage());
						}
						//grid.displayConsoleGrid();
						gui.updateGui(grid);
					}
				}
			} catch (NumberFormatException e) {
				System.err.println("Arguments must be integer.");
				System.exit(1);
			} 
		} else {
			System.err.println("You must have exactly 2 arguments: Grid size & Square size");
			System.exit(1);
		}
		

	}

}
