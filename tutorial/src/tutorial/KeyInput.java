package tutorial;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean[] keyDown = new boolean[4];

	public KeyInput(Handler handler) {
		this.handler = handler;

		keyDown[0] = false; // w
		keyDown[1] = false; // s
		keyDown[2] = false; // d
		keyDown[3] = false; // a

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				// key events for player1

				switch (key) {
				case KeyEvent.VK_W:
					tempObject.setVelY(-5);
					keyDown[0] = true;
					break;

				case KeyEvent.VK_S:
					tempObject.setVelY(5);
					keyDown[1] = true;
					break;

				case KeyEvent.VK_D:
					tempObject.setVelX(5);
					keyDown[2] = true;
					break;

				case KeyEvent.VK_A:
					tempObject.setVelX(-5);
					keyDown[3] = true;
					break;

				case KeyEvent.VK_SPACE:
					
					break;
				}

			}
			if (key == KeyEvent.VK_ESCAPE)
				System.exit(1);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				// key events for player1

				switch (key) {
				case KeyEvent.VK_W:
					// tempObject.setVelY(0);
					keyDown[0] = false;
					break;

				case KeyEvent.VK_S:
					// tempObject.setVelY(0);
					keyDown[1] = false;
					break;

				case KeyEvent.VK_D:
					// tempObject.setVelX(0);
					keyDown[2] = false;
					break;

				case KeyEvent.VK_A:
					// tempObject.setVelX(0);
					keyDown[3] = false;
					break;

				case KeyEvent.VK_SPACE:
					break;
				}
				
				// vertial movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				
				//horizontal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);

			}
		}

	}
}
