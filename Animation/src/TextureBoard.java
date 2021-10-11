import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TextureBoard extends JPanel implements ActionListener{
	Timer time;
	Thread threads = new Thread();
	Image backGround;
	Block blocks;
	Player players;
	private boolean moving;
	public boolean jumping = false;
	public boolean readyToJump = true;
	int speed = 0;
	int jumpCount = 0;
	int jumpSpeed = 0;
	int count = 0;
	BufferedImage player_sheet = null;
	BufferedImage block_sheet = null;
	BufferedImage playerMovement[] = new BufferedImage[7];
	BufferedImage jumpingPlayer[] = new BufferedImage[4];
	BufferedImage blockTextures;
	public TextureBoard()
	{
//		jumpPower = ;
		moving = false;
		time = new Timer(10, this);
		players = new Player(0,275);
		blocks = new Block();
		addKeyListener(new AK());
		setFocusable(true);
		ImageIcon i = new ImageIcon("Mountain.png");
		backGround = i.getImage();
		//player textures
		try {
			block_sheet = ImageIO.read(new File("Textures.png"));
			player_sheet = ImageIO.read(new File("Characters.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		blockTextures = new BufferedImage(blocks.width,blocks.height,BufferedImage.TYPE_INT_RGB);
		blockTextures = block_sheet.getSubimage(blocks.getCol(5),blocks.getRow(0),32,16);
		playerMovement[0] = new BufferedImage(30,40,BufferedImage.TYPE_INT_RGB);
		playerMovement[0] = player_sheet.getSubimage(players.getCol(0),players.getRow(0), 32, 32);
		jumpingPlayer[0] = new BufferedImage(30,40,BufferedImage.TYPE_INT_RGB);
		jumpingPlayer[0] = player_sheet.getSubimage(players.getCol(0),players.getRow(0), 32, 32);
		for(int i1 = 1 ; i1 < 7 ; i1 ++) {
			playerMovement[i1] = new BufferedImage(30,40,BufferedImage.TYPE_INT_RGB);
			playerMovement[i1] = player_sheet.getSubimage(players.getCol(11 + i1), players.getRow(0), 32, 32);
		}
		for(int j = 1 ; j < 4 ; j ++) {
			jumpingPlayer[j] = new BufferedImage(30,40,BufferedImage.TYPE_INT_RGB);
			jumpingPlayer[j] = player_sheet.getSubimage(players.getCol(1 + j),players.getRow(0), 32, 32);
		}
		
		
		time.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(jumping) {
			if(jumpSpeed % 10 == 0)
			{
				jumpCount ++;
				if(jumpCount > 3)
					jumpCount = 1;
			}
			jumpSpeed ++;
		}
		else if(moving) {
			if(speed % 10 == 0)
			{
				count ++;
				if(count > 6)
					count = 1;
			}
			speed ++;
			players.move();
		}
		repaint();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
	//background
		g.drawImage(backGround,players.backgroundX, 0, 750, 500, null);
		//blocks
		for(int i = 0 ; i < 750 ; i += 32) {
			g.drawImage(blockTextures, i, blocks.y, null);
		}
		//players
		//if the player is moving
		if(moving && !jumping) {
			System.out.println(count);
			g.drawImage(playerMovement[count], players.getPlayerX(), players.getPlayerY(), players.getPlayerWidth(), players.getPlayerHeight(), null);
		}
		else if(!moving && !jumping)
			g.drawImage(playerMovement[0], players.getPlayerX(), players.getPlayerY(), players.getPlayerWidth(), players.getPlayerHeight(), null);
		//if the player is jumping
		if(jumping){
			readyToJump = false;
			if(players.playerY == 225) 
				jumping = false;
				players.playerY --;
			g.drawImage(jumpingPlayer[jumpCount], players.getPlayerX(), players.getPlayerY(), players.getPlayerWidth(), players.getPlayerHeight(), null);
		}
		if(jumping == false && players.playerY <= 275)
		{
			if(players.playerY == 275)
				readyToJump = true;
			players.playerY ++;
			g.drawImage(jumpingPlayer[0], players.getPlayerX(), players.getPlayerY(), players.getPlayerWidth(), players.getPlayerHeight(), null);
		}
		System.out.println(players.getPlayerX());
		repaint();
	}
	private class AK extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_RIGHT)
				moving = true;
			else if(key == KeyEvent.VK_LEFT)
				moving = true;
			else if(key == KeyEvent.VK_UP) {
				if(readyToJump)
					jumping = true;
			}
			players.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			jumping = false;
			moving = false;
			speed = 0;
			players.keyReleased(e);
		}
	}
}
