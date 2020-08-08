package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener
{
	static final int WIDHT = 180;
	static final int HEIGHT = 120;
	static final int SCALE = 4;
	private boolean isRunning = true;
	static public Player player;
	static public Enemy enemy;
	static public Ball ball;
	public BufferedImage layer;
	public static boolean restart;
	
	
	public Game()
	{
		setPreferredSize(new Dimension(WIDHT*SCALE,HEIGHT*SCALE));
		player = new Player(75,HEIGHT-5);
		enemy = new Enemy(70,0);
		ball = new Ball(70,HEIGHT/2 - 1);
		layer = new BufferedImage(WIDHT,HEIGHT,BufferedImage.TYPE_INT_RGB);
	}
	public static void main(String[] args) 
	{
		Game game = new Game();
		JFrame frame = new JFrame();
		frame = new JFrame("Pong");
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(game);
		frame.setLocationRelativeTo(null);
		new Thread(game).start();
	}
	public void tick() 
	{
		player.tick();
		enemy.tick();
		ball.tick();
		if(restart == true) 
		{
			restart = false;
		}
	}
	public void render() 
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) 
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,WIDHT,HEIGHT);
		
		if(Ball.win) 
		{
			g.setFont(new Font("Arial",Font.BOLD,20));
			g.setColor(Color.white);
			g.drawString("Você venceu.",27,60);
		}
		if(Ball.lose) 
		{
			g.setFont(new Font("Arial",Font.BOLD,20));
			g.setColor(Color.white);
			g.drawString("Você perdeu.",27,60);
		}
		else if(Ball.lose == false && Ball.win == false)
		{
			player.render(g);
			enemy.render(g);
			ball.render(g);
		}
		g = bs.getDrawGraphics();
		g.drawImage(layer,0,0,WIDHT*SCALE,HEIGHT*SCALE,null);
		
		bs.show();
	}
	@Override
	public void run() 
	{
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60;
		final double ns = 1000000000/ amountOfTicks;
		double delta = 0;
		while(isRunning) 
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) 
			{
				render();
				tick();
				delta--;
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(Ball.lose || Ball.win) 
		{
			if(e.getKeyCode() == KeyEvent.VK_SPACE) 
			{
				restart = true;
			}
		}
		else 
		{
			if(e.getKeyCode() == KeyEvent.VK_D) 
			{
				player.right = true;
			}
			else if(e.getKeyCode()  == KeyEvent.VK_A) 
			{
				player.left = true;
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) 
	{
			
	}
	@Override
	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_D) 
		{
			player.right = false;
		}
		else if(e.getKeyCode()  == KeyEvent.VK_A) 
		{
			player.left = false;
		}
	}
}
