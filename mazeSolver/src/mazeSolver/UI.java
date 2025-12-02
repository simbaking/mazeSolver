package mazeSolver;

import javax.swing.*;
import java.awt.*;

public class UI {

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	
	public UI() {
		
		frame = new JFrame();
		panel = new JPanel();
		
	}
	
	public void show(int[][] mazePath) {
    	
        panel.removeAll();
        panel.setLayout(new GridLayout(mazePath.length, mazePath[0].length));
        
        for (int i = 0; i < mazePath.length; i++) {
        	
        	for (int j = 0; j < mazePath[0].length; j++) {

            	JButton button = new JButton(getText(mazePath[i][j]));
            	
            	button.setPreferredSize(new Dimension(800 / mazePath.length, 800 / mazePath.length));
            	
            	if(mazePath[i][j] == 0) {
            		button.setBackground(new Color(0, 0, 0));
            	}
            	
            	if(mazePath[i][j] == 1 || mazePath[i][j] == 4) {
            		button.setBackground(new Color(0, 255, 0));
            	}

            	if(mazePath[i][j] == 2) {
            		button.setBackground(new Color(127, 127, 127));
            	}

            	if(mazePath[i][j] == 3) {
            		button.setBackground(new Color(255, 0, 0));
            	}
            	
            	button.setOpaque(true);
            	panel.add(button);
            	
            }
            
		}
        
        frame.add(panel);
        frame.pack();
		frame.setVisible(true);
        
	}

	public void show(int[][] mazePath, int[][] maze) {
		
	    panel.removeAll();
	    panel.setLayout(new GridLayout(mazePath.length, mazePath[0].length));
	    
	    for (int i = 0; i < mazePath.length; i++) {
	    	
	    	for (int j = 0; j < mazePath[0].length; j++) {
	
	        	JButton button = new JButton(getText(mazePath[i][j]));
	        	
	        	button.setPreferredSize(new Dimension(800 / mazePath.length, 800 / mazePath.length));
	        	
	
	        	if(maze[i][j] == 0) {
	        		button.setBackground(new Color(0, 0, 0));
	        	}
	        	
	        	else if(mazePath[i][j] == 0) {
	        		button.setBackground(new Color(127, 127, 127));
	        	}
	        	
	        	if(mazePath[i][j] == 1 || mazePath[i][j] == 4) {
	        		button.setBackground(new Color(0, 255, 0));
	        	}
	
	        	if(mazePath[i][j] == 2) {
	        		button.setBackground(new Color(0, 0, 127));
	        	}
	
	        	if(mazePath[i][j] == 3) {
	        		button.setBackground(new Color(255, 0, 0));
	        	}
	        	
	        	button.setOpaque(true);
	        	panel.add(button);
	        	
	        }
	        
		}
	    
	    frame.add(panel);
	    frame.pack();
		frame.setVisible(true);
	    
	}

	public String getText(int pathType) {

		String cellLabel = null;
		
		if(pathType == 0 || pathType == 2 || pathType == 4) {
			cellLabel = "";
		}

		else if(pathType == 1) {
			cellLabel = "S";
		}

		else if(pathType == 3) {
			cellLabel = "E";
		}

		return cellLabel;
		
	}
	
}
