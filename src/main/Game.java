package main;

import javax.swing.JFrame;

class Game {

	public static void main(String[] args) {
		
		//this is going to be the main window
		JFrame window = new JFrame();
		  
		//some basic setting 
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //setting exit button
		window.setResizable(false); //we can't resize the window
		window.setTitle("GAME"); //naming the window
		window.setVisible(true); //so we can see the window
		//we don't need this because we will depend on the panel screen setting
		//window.setSize(new Dimension(500,500)); //setting window's dimensions
		 
		GamePanel gamePanel = new GamePanel(); //creating game panel object
		window.add(gamePanel); //adding the panel so its displayed in the window 
		window.pack();//to apply size setting
		
		//window.setLocationRelativeTo(null); //to center the window + this needs to be below

	}

}
