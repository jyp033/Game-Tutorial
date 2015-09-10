package tutorial;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import tutorial.Game.STATE;

public class Menu extends MouseAdapter {

	private Game game;
	private HUD hud;
	private Handler handler;
	private Random r = new Random();

	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.gameState == STATE.Menu) {

			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player((Game.WIDTH / 2 - 32),
						(Game.HEIGHT / 2 - 32), ID.Player, handler));

				handler.clearEnemys();

				handler.addObject(new BasicEnemy((float) r.nextInt(Game.WIDTH),
						(float) r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}

			// help button
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}

			// quit button
			if (game.gameState == STATE.Menu) {
				if (mouseOver(mx, my, 210, 350, 200, 64)) {
					game.gameState = STATE.Game;
					handler.addObject(new Player((Game.WIDTH / 2 - 32),
							(Game.HEIGHT / 2 - 32), ID.Player, handler));

					handler.clearEnemys();

					handler.addObject(new BasicEnemy((float) r
							.nextInt(Game.WIDTH), (float) r
							.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				}
			}
		}
		// back button
		if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		if (game.gameState == STATE.End) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Game;
				hud.setLevel(1);
				hud.setScore(0);
				handler.addObject(new Player((Game.WIDTH / 2 - 32),
						(Game.HEIGHT / 2 - 32), ID.Player, handler));
				handler.addObject(new BasicEnemy((float) r.nextInt(Game.WIDTH),
						(float) r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				return;
			}
		}

	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width,
			int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {

			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Menu", 240, 70);

			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 270, 190);

			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 270, 290);

			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);
		} else if (game.gameState == STATE.Help) {

			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);

			g.setFont(fnt2);
			g.drawString("Use WASD keys to move player and dodge enemies", 50,
					200);

			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		} else if (game.gameState == STATE.End) {

			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 240, 70);

			g.setFont(fnt2);
			g.drawString("You Lost with a score of: " + hud.getScore(), 50, 200);

			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try Again", 270, 390);
		}
	}
}
