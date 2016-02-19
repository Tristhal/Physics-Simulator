import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;

public class Menu_Game {
	private GamePanel gp;
	private menuScreen menu;
	private int selected;
	private int counter=0;
    public Menu_Game(GamePanel g) {
    	gp=g;
    	selected=1;
    	menu=new menuScreen(0,0,140,165,gp,"Global");
    	menu.setColour(new Color(100,100,100));
    	menu.addSlider(75,50,50,new Color(255,100,100),"Gravity");
    	menu.addBox(75,70,10,10,new Color(255,100,100),"Ball",20);
    	menu.addBox(75,95,10,10,new Color(255,100,100),"Mystery Fluid",20);
    	menu.addBox(75,120,10,10,new Color(255,100,100),"Solid",20);
    	menu.addBox(75,145,10,10,new Color(255,100,100),"Clear All",20);
    	menu.resetBoxes(1);
    }
    public void update(){
    	menu.checkHover(gp.getMX(),gp.getMY());
    	int pressed=menu.checkCollide(gp.getMX(),gp.getMY());
    	double[] slider=menu.checkSliders(gp.getMX(),gp.getMY());
    	double[] box=menu.checkBoxes(gp.getMX(),gp.getMY());
    	if(box[0]==-1){
    	}
    	else{
    		if(box[0]==0){
    			menu.getBox(2).setActive(false);
    			menu.getBox(1).setActive(false);
    			menu.getBox(0).setActive(true);
    			gp.setSelectedObj("BBall");
    		}
    		else if(box[0]==1){
    			menu.getBox(2).setActive(false);
    			menu.getBox(1).setActive(true);
    			menu.getBox(0).setActive(false);
    			gp.setSelectedObj("Water");
    		}
    		else if(box[0]==2){
    			menu.resetBoxes(2);
    			menu.getBox(2).setActive(true);
    			menu.getBox(1).setActive(false);
    			menu.getBox(0).setActive(false);
    			gp.setSelectedObj("Solid");
    		}
    		else if(box[0]==3){
    			menu.getBox(3).toggleActive();    			
    		}
    	}
    	if(menu.getBox(3).getActive()){
    		gp.clearAllBalls();
    		counter+=1;
    		if(counter==30){
	    		menu.getBox(3).setActive(false);
	    		counter=0;
    		}
    	}
    	if(slider[0]==-1){
    	}
    	else{
    		if(slider[0]==0){
    			gp.setGravity(.5*slider[1]);
    		}
    	}
    }
    public void draw(Graphics2D g){
    	menu.draw(g);
    }
}