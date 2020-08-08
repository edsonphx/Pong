package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player 
{
	public boolean left,right;
	static public int x,y;
	private final int VELOCITY = 3;
	public Player(int x, int y)
	{
		Player.x = x;
		Player.y = y;
	}
	public void tick() 
	{
		if(Game.restart) 
		{
			x = 75;
			y = 115;
		}
		if(right && x <= 140) 
		{
			x += VELOCITY;
		}
		else if(left && x >= 0) 
		{
			x -= VELOCITY;
		}
	}
	public void render(Graphics g) 
	{
		g.setColor(Color.blue);
		g.fillRect(x, y, 40, 5);
	}
}
