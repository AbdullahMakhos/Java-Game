package myGame.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//to get user input
public class KeyHandler implements KeyListener{
	private GamePanel gp;
	public boolean upPressed = false;
	public boolean downPressed = false;
	public boolean rightPressed = false;
	public boolean leftPressed = false;
	public boolean pPressed = false;
	public boolean escPressed = false;
	public boolean spacePressed = false;
	public boolean f4Pressed = false;
	public boolean f5Pressed = false;
	
	public KeyHandler(GamePanel gp){
		this.gp = gp;
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
		//to pick some object 
		if(code == KeyEvent.VK_P) {
			pPressed = true;
        }
		//to pause the game
		if(code == KeyEvent.VK_ESCAPE) {
			escPressed = true;
		}
		//to eat
		if(code == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
		
		//to save 
		if(code == KeyEvent.VK_F4) {
			f4Pressed = true;
		}
		
		//to load 
		if(code == KeyEvent.VK_F5) {
			f5Pressed = true;
		}
		
		 //item selection (side inventory) 
		if(code == KeyEvent.VK_0) {
			gp.getPlayer().getInventory().setSelectedItemId(-1);
			gp.getUIManager().updateSelectedItemId();
		}
		
		if(code == KeyEvent.VK_1) {
			gp.getPlayer().getInventory().setSelectedItemId(0);
			gp.getUIManager().updateSelectedItemId();
        }
		
		if(code == KeyEvent.VK_2) {
			gp.getPlayer().getInventory().setSelectedItemId(1);
			gp.getUIManager().updateSelectedItemId();
		}
		
		if(code == KeyEvent.VK_3) {
			gp.getPlayer().getInventory().setSelectedItemId(2);
			gp.getUIManager().updateSelectedItemId();
		}
		
		if(code == KeyEvent.VK_4) {
			gp.getPlayer().getInventory().setSelectedItemId(3);
			gp.getUIManager().updateSelectedItemId();
		}
		
		if(code == KeyEvent.VK_5) {
			gp.getPlayer().getInventory().setSelectedItemId(4);
			gp.getUIManager().updateSelectedItemId();
		}
		
		if(code == KeyEvent.VK_6) {
			gp.getPlayer().getInventory().setSelectedItemId(5);
			gp.getUIManager().updateSelectedItemId();
		}
		
		if(code == KeyEvent.VK_7) {
			gp.getPlayer().getInventory().setSelectedItemId(6);
			gp.getUIManager().updateSelectedItemId();
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
		
		if(code == KeyEvent.VK_SPACE) {
			spacePressed = false;
			gp.getPlayer().resetCounter();
		}
		
		if(code == KeyEvent.VK_ESCAPE) {
			escPressed = false;
			gp.reseteEscCounter();
		}
		if(code == KeyEvent.VK_F4) {
			f4Pressed = false;
			gp.resetF4Counter();
		}
		
		if(code == KeyEvent.VK_F5) {
			f5Pressed = false;
			gp.resetF5Counter();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
