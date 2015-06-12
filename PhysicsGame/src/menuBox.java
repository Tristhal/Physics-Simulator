import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;

public class menuBox {
	private Color colour;
	private int x;
	private int y;
	private int width;
	private int height;
	private menuScreen menu;
	private boolean active;
	private boolean collided;
	private String label;
    public menuBox (int x, int y,int width,int height,Color colour,menuScreen mn,String l){
    	menu=mn;
		this.colour=colour;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		label=l;
    }
    public boolean checkCollide(int mx, int my){
    	int x=this.x+menu.getX();
		int y=this.y+menu.getY();
		if(mx>x &&mx<x+width && my>y &&my<y+height){
			collided=true;
		}
		else{
			collided=false;
		}
		return collided;
    }
    public void draw(Graphics2D g){
    	g.setColor(colour);
    	g.drawRect(x+menu.getX(),y+menu.getY(),width,height);
    	if(active){
    		g.fillRect(x+menu.getX()+2,y+menu.getY()+2,width-3,height-3);
    	}
    	g.drawString(label,x+menu.getX()-59,y+menu.getY()+10);
    }
    public void toggleActive(){
    	active=active?false:true;
    }
    public boolean getActive(){
    	return active;
    }
    public void setActive(boolean b){
    	active=b;
    }
}