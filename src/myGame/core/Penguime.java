package myGame.core;

import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

class Penguime {

  public static void main(String[] args) throws StreamReadException, DatabindException, IOException  {
    
    // Create the main window
    JFrame window = new JFrame(); 
    ImageIcon imageIcon = new ImageIcon("/home/abdullah/Projects/java/Penguime/src/resources/images/UI/icon.png");
    Image icon = imageIcon.getImage(); 
    
    // Basic settings 
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
    window.setResizable(false); // Prevent resizing
    window.setTitle("Penguime"); // Set window title
    window.setIconImage(icon);
    
    GamePanel gamePanel = new GamePanel(); // Create game panel object
    window.add(gamePanel); // Add panel to the window 
    window.pack(); // Apply size settings
    window.setLocationRelativeTo(null); // Center the window
    window.setVisible(true); // Make the window visible
    
  }
}
