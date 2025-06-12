package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//to get user input
public class KeyHandler implements KeyListener{
	
	public boolean upPressed = false;
	public boolean downPressed = false;
	public boolean rightPressed = false;
	public boolean leftPressed = false;
	public boolean oPressed = false;
	public boolean ePressed = false;
	public boolean pPressed = false;
	public boolean escPressed = false;
	public boolean nPressed = false;
	public boolean qPressed = false;
	
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
		// to open doors
		if(code == KeyEvent.VK_O) {
			oPressed = !oPressed;
		}
		//to open inventory
		if(code == KeyEvent.VK_E) {
			ePressed = !ePressed;
		}
		//to pick some object 
		if(code == KeyEvent.VK_P) {
			pPressed = true;
		}
		//to pause the game
		if(code == KeyEvent.VK_ESCAPE) {
			escPressed = !escPressed;
		}
		//to go to the next map
		if(code == KeyEvent.VK_N) {
			nPressed = true;
		}
		//to eat
		if(code == KeyEvent.VK_Q) {
			qPressed = true;
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
		
		if(code == KeyEvent.VK_P) {
			pPressed  = false;
		}
		
		if(code == KeyEvent.VK_N) {
			nPressed = false;
		}
		
		if(code == KeyEvent.VK_Q) {
			qPressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
