import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class Gui {
	@SuppressWarnings("unused")
	private int frameSize;
	private JFrame frame;
	private JButton guiGrid[][];
	private JLabel leftAnnots[];
	private JLabel rightAnnots[];
	
	// CONSTRUCTOR
	public Gui(int frameSize, Grid grid) {
		// We create the main window
		frame = new JFrame("John Conway's Game of Life");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // We ask to stop the app when the window is closed
	    //frame.setSize(frameSize,frameSize);
	    
	    // We create the main panel
	    JPanel mainPanel = new JPanel();
	    frame.setContentPane(mainPanel); // We set the primary container
	    mainPanel.setLayout(new GridBagLayout());
	   
	    // Left panel +  constraints
	    JPanel leftPanel = new JPanel();
	    GridBagConstraints c1 = new GridBagConstraints();
	    c1.fill = GridBagConstraints.VERTICAL;
	    c1.gridx = 0;
	    mainPanel.add(leftPanel, c1);
	    leftPanel.setLayout(new GridLayout(grid.getGridSize(), 1));
	    // We create the left border annotations
	    leftAnnots = new JLabel[grid.getGridSize()];
	    for(int i = 0; i < grid.getGridSize(); i++) {
	    	leftAnnots[i] = new JLabel("[+ 0/- 0]");
	    	leftPanel.add(leftAnnots[i]);
	    }
	    
	    // Center panel +  constraints
	    JPanel centerPanel = new JPanel();
	    GridBagConstraints c2 = new GridBagConstraints();
	    c2.fill = GridBagConstraints.VERTICAL;
	    c2.gridx = 1;
	    mainPanel.add(centerPanel, c2);
	    centerPanel.setLayout(new GridLayout(grid.getGridSize(),grid.getGridSize()));
	    // We create the grid of buttons in the center panel
	    guiGrid = new JButton[grid.getGridSize()][grid.getGridSize()];
	    for(int i = 0; i < grid.getGridSize(); i++) {
			for(int j = 0; j < grid.getGridSize(); j++) {
				guiGrid[i][j] = new JButton();
				guiGrid[i][j].setPreferredSize(new Dimension(20, 20));
				centerPanel.add(guiGrid[i][j]);
			}
	    }
	    	    
	    // Right panel +  constraints
	    JPanel rightPanel = new JPanel();
	    GridBagConstraints c3 = new GridBagConstraints();
	    c3.fill = GridBagConstraints.VERTICAL;
	    c3.gridx = 2;
	    mainPanel.add(rightPanel, c3);
	    rightPanel.setLayout(new GridLayout(grid.getGridSize(), 1));
	    // We create the right border annotations
	    rightAnnots = new JLabel[grid.getGridSize()];
	    for(int i = 0; i < grid.getGridSize(); i++) {
	    	rightAnnots[i] = new JLabel("[+ 0/- 0]");
	    	rightPanel.add(rightAnnots[i]);
	    }	    
	    
	    // We update the color of GUI for the first time
	    updateGui(grid);
	    
	    // We ask the frame to fit the main panel
	    frame.pack();
	    
	    // We display the GUI
	    frame.setVisible(true);   
	}
	
	
	/*
	 * Method that change the color of buttons.
	 */
	public void setButtonColor(int row,  int col, Color color) {
		guiGrid[row][col].setBackground(color);
	}
	
	/*
	 * Method that updates the color of each GUI button depending on cell status as well as the counter on the border sides.
	 */
	public void updateGui(Grid grid) {
		for(int i = 0; i < grid.getGridSize(); i++) {
			String tempStrLeft = "";
			String tempStrRight = "";
			tempStrLeft += "[+" + String.format("%3d", ((BorderCell) grid.getCell(i, 0)).getBirthCounter()) + "/-";
			tempStrLeft += String.format("%3d", ((BorderCell) grid.getCell(i, 0)).getKillCounter()) + "]";
			tempStrRight += "[+" + String.format("%3d", ((BorderCell) grid.getCell(i, grid.getGridSize() - 1)).getBirthCounter()) + "/-";
			tempStrRight += String.format("%3d", ((BorderCell) grid.getCell(i, grid.getGridSize() - 1)).getKillCounter()) + "]";
			leftAnnots[i].setText(tempStrLeft);
			rightAnnots[i].setText(tempStrRight);
			for(int j = 0; j < grid.getGridSize(); j++) {
				if(grid.getCell(i, j).isAlive() == true) {
					setButtonColor(i, j, Color.BLACK);
				} else {
					setButtonColor(i, j, Color.WHITE);
				}
			}
	    }
	}
	
}
