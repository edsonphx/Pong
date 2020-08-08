package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy 
{
	static public double x,y;
	
    public Enemy(int x , int y)
    {
    	Enemy.x = x;
    	Enemy.y = y;
    }
	public void tick() 
	{
		if(Game.restart) 
		{
			x = 70;
			y = 0;
		}
		x += (Game.ball.x - x - 20) * 0.06;
		if(x >= 140) 
		{
			x = 140;
		}
		else if(x <= 0) 
		{
			x = 0;
		}
	}
	public void render(Graphics g) 
	{
		g.setColor(Color.red);
		g.fillRect((int)x,(int)y, 40, 5);
	}
}
