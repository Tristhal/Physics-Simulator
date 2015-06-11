import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;
public class Menu_BounceBall {
	private menuScreen menu;
	private GamePanel gp;
	private int r,g,b;
    public Menu_BounceBall(GamePanel g) { 
    	gp=g;
    	r=255/2;
    	this.g=255/2;
    	b=255/2;
    	menu=new menuScreen(0,140,140,320,gp,"Bounce Balls");
    	menu.setMoveable(true);
    	menu.setColour(new Color(100,100,100));
    	menu.addSlider(75,50,50,new Color(255,100,100),"Size");
    	menu.addSlider(75,90,50,new Color(255,100,100),"Colour: R");
    	menu.addSlider(75,130,50,new Color(255,100,100),"Colour: G");
    	menu.addSlider(75,170,50,new Color(255,100,100),"Colour: B");
    	menu.addSlider(75,210,50,new Color(255,100,100),"Mass");
    	menu.addBox(75,240,10,10,new Color(255,100,100),"Static");
    	menu.addBox(75,270,10,10,new Color(255,100,100),"MMode");
    	menu.addBox(75,300,10,10,new Color(255,100,100),"Clear");
    }
    private int counter=0;
    public void update(){
    	menu.checkHover(gp.getMX(),gp.getMY());
    	int pressed=menu.checkCollide(gp.getMX(),gp.getMY());
    	double[] slider=menu.checkSliders(gp.getMX(),gp.getMY());
    	double[] box=menu.checkBoxes(gp.getMX(),gp.getMY());
    	if(slider[0]==-1){
    	}
    	else{
    		
    		if(slider[0]==0){//0 = slider num 1=value
    			gp.setBallSize(Math.max(1,(int)(20*slider[1])));
    			
    		}
    		else if(slider[0]==1){
    			
    			r=(int)(255*slider[1]);
    			gp.setBBallColour(new Color(r,g,b));
    		}
    		else if(slider[0]==2){
    			g=(int)(255*slider[1]);
    			gp.setBBallColour(new Color(r,g,b));
    		}
    		else if(slider[0]==3){
    			b=(int)(255*slider[1]);
    			gp.setBBallColour(new Color(r,g,b));
    		}
    		else if(slider[0]==4){
    			gp.setBBallMass((int)(50-49*slider[1]));
    		}
    	}
    	if(box[0]==-1){
    	}
    	else{
    		if (box[0]==0){
    			gp.setBallMovement(menu.getBox(0).getActive());
    			menu.toggleBoxActive(0);
    		}
    		if(box[0]==1){
    			menu.toggleBoxActive(1);
    		}
    		if(box[0]==2){
    			menu.toggleBoxActive(2);
    		}
    		
    	}
    	gp.setMassMode(menu.getBox(1).getActive());
    	if(menu.getBox(2).getActive()){
    		gp.clearBBalls();
    		counter+=1;
    		if(counter==30){
	    		menu.getBox(2).setActive(false);
	    		counter=0;
    		}
    	}
    }
    public void draw(Graphics2D g){
    	menu.draw(g);
    }
    
    
}