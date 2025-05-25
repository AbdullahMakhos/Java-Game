package main;

import javax.swing.JFrame;

class Game {

  public static void main(String[] args) {
    
    // Create the main window
    JFrame window = new JFrame();
      
    // Basic settings
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
    window.setResizable(false); // Prevent resizing
    window.setTitle("GAME"); // Set window title
    
    GamePanel gamePanel = new GamePanel(); // Create game panel object
    window.add(gamePanel); // Add panel to the window 
    window.pack(); // Apply size settings
    window.setLocationRelativeTo(null); // Center the window
    window.setVisible(true); // Make the window visible
  }
}