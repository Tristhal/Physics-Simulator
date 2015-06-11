import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;

public class Menu_Game {
	GamePanel gp;
	menuScreen menu;
	int selected;
    public Menu_Game(GamePanel g) {
    	gp=g;
    	selected=1;
    	menu=new menuScreen(0,0,140,140,gp,"Global");
    	menu.setColour(new Color(100,100,100));
    	menu.addSlider(75,50,50,new Color(255,100,100),"Gravity");
    	menu.addBox(75,70,10,10,new Color(255,100,100),"Ball");
    	menu.addBox(75,95,10,10,new Color(255,100,100),"Water");
    	menu.addBox(75,120,10,10,new Color(255,100,100),"Solid");
    	menu.resetBoxes(0);
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
    			menu.resetBoxes(0);
    			gp.setSelectedObj("BBall");
    		}
    		else if(box[0]==1){
    			menu.resetBoxes(1);
    			gp.setSelectedObj("Water");
    		}
    		else if(box[0]==2){
    			menu.resetBoxes(2);
    			gp.setSelectedObj("Solid");
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