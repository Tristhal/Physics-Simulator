import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;
class intSlider {
	int x;
	int y;
	int width;
	Color colour;
	menuScreen menu;
	int posx;
	int bwid;
	int bhei;
	int lastposx;
	boolean moving;
	String label;
	public intSlider(int x, int y, int wid,Color colour,menuScreen m,String s){
		label=s;
		this.x=x;
		this.y=y;
		width=wid;
		menu=m;
		this.colour=colour;
		posx=wid/2;
		bwid=6;
		bhei=14;
		lastposx=-1;
		moving=false;
	}
	
	public double checkMove(int mx, int my,boolean mousepressed){
		if((moving || (x+posx-bwid/2+menu.getX())<mx && (x+posx-bwid/2+menu.getX()+bhei)>mx 
			&& (y-bhei/2+menu.getY())<my && (y-bhei/2+menu.getY()+bhei)>my)&& mousepressed){
			moving=true;
			if(lastposx==-1){
			}
			else{
				posx=Math.max(0,Math.min(width,mx-menu.getX()-x));
			}
			lastposx=mx;
			return (double)posx/width;
		}
		else{
			moving=false;
			lastposx=-1;
			return -1;
		}
	}
	public void draw(Graphics2D g){
		g.setColor(colour);
		g.drawLine(x+menu.getX(),y+menu.getY(),x+width+menu.getX(),y+menu.getY());
		g.fillRect(x+posx-bwid/2+menu.getX(),y-bhei/2+menu.getY(),bwid,bhei);
		g.drawString(""+(double)posx/width,x+posx-bwid/2+menu.getX()-3,y-bhei/2+menu.getY()-3);
		g.drawString(label,x+menu.getX()-60,y+menu.getY()+3);
		//g.fillRect(x+posx-bwid+menu.getX(),y-bhei/2+menu.getY(),bwid,bhei);
	}
}
