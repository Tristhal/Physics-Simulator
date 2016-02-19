import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;

public class Menu_Water {
	private GamePanel gp;
	private menuScreen menu;
	private int counter=0;
    public Menu_Water(GamePanel g) {
    	gp=g;
    	menu=new menuScreen(0,165,140,210,gp,"Mystery Fluid");
    	menu.setMoveable(true);
    	menu.setColour(new Color(100,100,100));
    	menu.addSlider(75,50,50,new Color(255,100,100),"Viscosity");
    	menu.addSlider(75,90,50,new Color(255,100,100),"Size");
    	menu.addSlider(75,130,50,new Color(255,100,100),"Particles");
    	menu.addBox(75,160,10,10,new Color(255,100,100),"Fancy");
    	menu.addBox(75,190,10,10,new Color(255,100,100),"Clear");
    	menu.getBox(0).setActive(true);
    }
    
    public void update(){
    	menu.checkHover(gp.getMX(),gp.getMY());
    	int pressed=menu.checkCollide(gp.getMX(),gp.getMY());
    	double[] slider=menu.checkSliders(gp.getMX(),gp.getMY());
    	double[] box=menu.checkBoxes(gp.getMX(),gp.getMY());
    	if(slider[0]==-1){
    	}
    	else{
    		if(slider[0]==0){
    			gp.setViscosity(.2*slider[1]);
    		}
    		else if(slider[0]==1){
    			gp.setWaterSize((int)(10*slider[1]));
    		}
    		else if(slider[0]==2){
    			gp.setWaterSpawnNum((int)(1+10*slider[1]));
    		}
    	}
    	if(box[0]!=-1){
    		if(box[0]==0){
    			menu.getBox(0).toggleActive();
    		}
    		if(box[0]==1){
    			menu.getBox(1).toggleActive();
    		}
    	}
    	gp.setWFancy(menu.getBox(0).getActive());
    	if(menu.getBox(1).getActive()){
    		gp.clearWater();
    		counter+=1;
    		if(counter==30){
	    		menu.getBox(1).setActive(false);
	    		counter=0;
    		}
    	}
    }
    public void draw(Graphics2D g){
    	menu.draw(g);
    }
}