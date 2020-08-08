package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball 
{
	public double x,y;
	
	public double dx, dy;
	public double bx, by;
	public final double VELOCITY = 2.7;
	static boolean win , lose;
	
    public Ball(int x , int y)
    {
    	this.x = x;
    	this.y = y;
    	
    	int angle = new Random().nextInt(359);
    	dx = Math.cos(Math.toRadians(angle));
    	dy = Math.sin(Math.toRadians(angle));
    }
	public void tick() 
	{
		int angle = new Random().nextInt(75) + 45;
		if(Game.restart) 
		{
			win = false;
			lose = false;
			dx = Math.cos(Math.toRadians(angle));
	    		dy = Math.sin(Math.toRadians(angle));
	    		x = 70;
	    		y = 59;
		}
		bx = x + dx*VELOCITY;
		by = y + dy*VELOCITY;
		if(bx + 4 >= Game.WIDHT || bx < 0) 
		{
			dx *= -1;
		}
		if(by  <= Enemy.y + 3) 
		{
			if(bx >= Enemy.x && bx <= Enemy.x + 40) 
			{
				dx = Math.cos(Math.toRadians(angle));
				dy *= -1;
			}
			else 
			{
				win = true;
			}
		}
		if(by  >= Player.y - 1.81) 
		{
			if(bx >= Player.x - 3 && bx <= Player.x + 40) 
			{
				dx = Math.cos(Math.toRadians(angle));
				dy *= -1;
			}
			else 
			{
				lose = true;
			}
		}
		else 
		{
			x += dx*VELOCITY;
			y += dy*VELOCITY;
		}
	}
	public void render(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect((int)x,(int)y, 4, 4);
	}
}
