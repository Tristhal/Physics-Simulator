import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;
class menuButton {
	private Color colour;
	private int buttonx;
	private int buttony;
	private int buttonwidth;
	private int buttonheight;
	private boolean collided;
	private menuScreen menu;
	private double sizechange=1;
	private double direction=.25;
	public void setColor(Color colour){
		this.colour=colour;
	}
	
	public menuButton(int buttonx, int buttony,int buttonwidth,int buttonheight,Color colour,menuScreen mn){
		menu=mn;
		this.colour=colour;
		this.buttonx=buttonx;
		this.buttony=buttony;
		this.buttonwidth=buttonwidth;
		this.buttonheight=buttonheight;
	}
	public void draw(Graphics2D g){
		int buttonx=this.buttonx+menu.getX();
		int buttony=this.buttony+menu.getY();
		if(collided){
			sizechange=Math.max(1,sizechange+direction);
		}
		else{
			sizechange=Math.min(2,sizechange+direction);
		}
		for(double i=0;i<20;i+=sizechange){
			for(int x=0;x<20;x+=2){
				paint.drawRect(g,buttonx+x,buttony+(int)i,buttonwidth-2*x,buttonheight,colour,.008f);
			}
		}
	}
	public boolean checkCollide(int mx, int my){
		int buttonx=this.buttonx+menu.getX();
		int buttony=this.buttony+menu.getY();
		if(mx>buttonx &&mx<buttonx+buttonwidth && my>buttony &&my<buttony+10+buttonheight){
			collided=true;
			direction=-.25;
		}
		else{
			collided=false;
			direction=.25;
		}
		return collided;
	}
}
