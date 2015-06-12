import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;
public class Menu_Solid {
	private menuScreen menu;
	private GamePanel gp;
	private int r,g,b;
    public Menu_Solid(GamePanel g) {
    	gp=g;
    	r=255/2;
    	this.g=255/2;
    	b=255/2;
    	menu=new menuScreen(0,165,140,220,gp,"Solid");
    	menu.setMoveable(true);
    	menu.setColour(new Color(100,100,100));
    	menu.addSlider(75,50,50,new Color(255,100,100),"Size");
    	menu.addSlider(75,90,50,new Color(255,100,100),"Colour: R");
    	menu.addSlider(75,130,50,new Color(255,100,100),"Colour: G");
    	menu.addSlider(75,170,50,new Color(255,100,100),"Colour: B");
    	menu.addBox(75,200,10,10,new Color(255,100,100),"Clear");
    	
    }
    private int counter;
    public void update(){
    	menu.checkHover(gp.getMX(),gp.getMY());
    	int pressed=menu.checkCollide(gp.getMX(),gp.getMY());
    	double[] slider=menu.checkSliders(gp.getMX(),gp.getMY());
    	double[] box=menu.checkBoxes(gp.getMX(),gp.getMY());
    	if(slider[0]==-1){
    	}
    	else{
    		
    		if(slider[0]==0){//0 = slider num 1=value
    			gp.setSBallSize(Math.max(1,(int)(20*slider[1])));
    			
    		}
    		else if(slider[0]==1){
    			
    			r=(int)(255*slider[1]);
    			gp.setSBallColour(r,g,b);
    		}
    		else if(slider[0]==2){
    			g=(int)(255*slider[1]);
    			gp.setSBallColour(r,g,b);
    		}
    		else if(slider[0]==3){
    			b=(int)(255*slider[1]);
    			gp.setSBallColour(r,g,b);
    		}
    	}
    	if(box[0]==-1){
    	}
    	else{
    		if(box[0]==0){
    			menu.toggleBoxActive(0);
    		}
    		
    	}
    	if(menu.getBox(0).getActive()){
    		gp.clearSBalls();
    		counter+=1;
    		if(counter==30){
	    		menu.getBox(0).setActive(false);
	    		counter=0;
    		}
    	}
    }
    public void draw(Graphics2D g){
    	menu.draw(g);
    }
}