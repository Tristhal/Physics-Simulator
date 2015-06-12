import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;
class menuScreen {
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean moveable;
	private ArrayList<menuButton>buttons;
	private ArrayList<intSlider>sliders;
	private ArrayList<menuBox>boxes;
	private Color colour;
	private GamePanel gp;
	private String label;
	private int lastx=-1;
	private int lasty=-1;
	private boolean moving;
	public menuScreen(int x, int y, int width, int height,GamePanel panel,String name){
		moveable=false;
		label=name;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		buttons=new ArrayList<menuButton>();
		sliders=new ArrayList<intSlider>();
		boxes=new ArrayList<menuBox>();
		gp=panel;
	}
	public void draw(Graphics2D g){
		paint.setAA(g,true);
		paint.setAlpha(g,.5f);
		g.setColor(colour);
		g.fillRect(x,y,width,height);
		paint.setAlpha(g,.5f);
		g.fillRect(x,y,width,15);
		paint.setAlpha(g,.8f);
		for(menuButton button:buttons){
			button.draw(g);
		}
		for(intSlider slider:sliders){
			slider.draw(g);
		}
		for(menuBox box:boxes){
			box.draw(g);
		}
		g.setColor(new Color(255,255,255));
		g.drawString(label,x+5,y+12);
		paint.setAlpha(g,1f);
		paint.setAA(g,false);
	}
	public void setSpawnValid(boolean b){
		gp.setSpawnValid(b);
	}
	public void resetBoxes(int keep){
		for(int i=0;i<boxes.size();i++){
			if(i!=keep){
				boxes.get(i).setActive(false);
			}
			else{
				boxes.get(i).setActive(true);
			}
		}
	}
	public int checkCollide(int mx, int my){
		if(moveable){
			if(gp.getMousePressed() && ((mx>x && mx<x+width && my>y && my<y+15)|| moving)){
				moving=true;
				if(lastx==-1 || lasty==-1){
				}
				else{
					x+=mx-lastx;
					y+=my-lasty;
					if(x<0){
						x=0;
						moving=false;
					}
					else if(x+width>1000){
						x=1000-width;
						moving=false;
					}
					if(y<0){
						y=0;
						moving=false;
					}
					else if(y+height>800){
						y=800-height;
						moving=false;
					}
				}
				gp.setSpawnValid(false);
				lastx=mx;
				lasty=my;
				return 1;
			}
			else{
				moving=false;
				lastx=-1;
				lasty=-1;
				return -1;
			}
		}
		return-1;
	}
	public int checkButtons(int mx, int my){
		for(int i=0;i<buttons.size();i++){
			if(buttons.get(i).checkCollide(mx,my)){
				if(gp.getMouseClicked()){
					return i;
				}
					
			}	
		}
		return -1;
	}
	public double[] checkBoxes(int mx,int my){
		double[] output=new double[2];
		boolean collided;
		for(int i=0;i<boxes.size();i++){
			collided=boxes.get(i).checkCollide(mx,my);
			if(collided){
				if(gp.getMouseClicked()){
					output[0]=i;
					output[1]=(collided) ? 1 : 0;
					return output;
				}
					
			}	
		}
		output[0]=-1;
		output[1]=-1;
		return output;
		
	}
	public double[] checkSliders(int mx, int my){
		double[] output=new double[2];
		double val;
		for(int i=0;i<sliders.size();i++){
			output[0]=(double)i;
			val=sliders.get(i).checkMove(mx,my,gp.getMousePressed());
			if(val!=-1){
				output[1]=val;
				gp.setSpawnValid(false);
				return(output);
			}
			
		}
		output[0]=-1;
		output[1]=-1;
		return output;
	}
	public void toggleBoxActive(int box){
		boxes.get(box).toggleActive();
	}
	public menuBox getBox(int i){
		return boxes.get(i);
	}
	
	public void checkHover(int mx, int my){
		if(mx>x && mx<(x+width) && my>y && my<(y+height)){
			gp.setSpawnValid(false);
		}
		else{
			gp.setSpawnValid(gp.getSpawnValid()? true : false);
		}
	}
	public void addBox(int buttonx, int buttony,int buttonwidth,int buttonheight,Color colour,String text){
		boxes.add(new menuBox(buttonx,buttony,buttonwidth,buttonheight,colour,this,text));
	}
	public void addButton(int buttonx, int buttony,int buttonwidth,int buttonheight,Color colour){
		buttons.add(new menuButton(buttonx,buttony,buttonwidth,buttonheight,colour,this));
	}
	public void addSlider(int x, int y, int wid,Color colour,String s){
		sliders.add(new intSlider(x,y,wid,colour,this,s));
	}
	public void setMoveable(boolean b){
		moveable=b;
	}
	public int getOffX(){
		return x;
	}
	public int getOffY(){
		return y;
	}
	public int getY(){
		return y;
	}
	public int getX(){
		return x;
	}
	public void setColour(Color col){
		colour=col;
	}
}
