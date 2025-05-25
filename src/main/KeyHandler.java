package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//to get user input
public class KeyHandler implements KeyListener{
	
	public boolean upPressed = false;
	public boolean downPressed = false;
	public boolean rightPressed = false;
	public boolean leftPressed = false;
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode(); //to get the pressed key code
		// w is pressed
		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		// s is pressed 
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		// d is pressed
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		// a is pressed
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		
	}

	@Override 
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode(); //to get the released key code
		// w is released
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		// s is released
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		// d is released
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		// a is released
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
	}
	

}
