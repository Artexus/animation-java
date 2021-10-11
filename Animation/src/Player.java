import java.awt.event.KeyEvent;

public class Player {
	public int playerX ;
	public int playerY;
	private int dx;
	public int backgroundX;
	private int nx;
	private int playerWidth;
	private int playerHeight;
	Block blocks;
	TextureBoard board;
	public Player(int x,int y)
	{
		this.playerX = x;
		this.playerY = y;
		this.playerWidth = 40;
		this.playerHeight = 45;
	}
	public void move() {
		playerX = playerX + dx;
	}
	public int getPlayerX() {
		return playerX;
	}
	public int getPlayerY() {
		return playerY;
	}
	public int getPlayerWidth() {
		return playerWidth;
	}
	public int getPlayerHeight() {
		return playerHeight;
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			dx = 1;
		}
		if(key == KeyEvent.VK_LEFT) {
			dx = -1;
		}
		if(key == KeyEvent.VK_UP) {
			nx = 0;
			dx = 0;
		}
	}
	public int getCol(int n)
	{
		return n * 32;
	}
	public int getRow(int n)
	{
		return n * 32;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			nx = 0;
			dx = 0;
		}
		if(key == KeyEvent.VK_LEFT) {
			nx = 0;
			dx = 0;
		}
		if(key == KeyEvent.VK_UP) {
			nx = 0;
			dx = 0;
		}
	}

	
}
