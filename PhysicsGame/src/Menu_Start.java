import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;

public class Menu_Start {

    private menuScreen menu;
	private GamePanel gp;
    public Menu_Start(GamePanel g) { 
    	gp=g;
    	menu=new menuScreen(0,0,1000,800,gp,"");
    	for(int i=0;i<2;i++){	
			menu.addButton(1000/2-100,300+80*i,200,30,new Color(0,155,255)); 
		}
    }
    public void update(){
    	menu.checkCollide(gp.getMX(),gp.getMY());
    	int pressed=menu.checkButtons(gp.getMX(),gp.getMY());
		if(pressed==-1){
			
		}
		else if(pressed==0){
			gp.setFade(true);
			gp.setGravity(.25);
			gp.setViscosity(.1);
			gp.setWaterSize(5);
			gp.getMainFrame().setGameStatus(true);
			gp.getMainFrame().setStartMenuStatus(false);
			gp.getMainFrame().setSimulationStatus(true);
		}
		else if(pressed==1){
			gp.getMainFrame().dispose();			
		}
    }
    public void draw(Graphics2D g){
    	menu.draw(g);
    	g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
    	g.setColor(new Color(255,255,255));	
    	paint.setAlpha(g,.8f);
    	g.drawString("Exit",482,411);
    	g.drawString("Begin",475,330);
    	paint.setAlpha(g,1f);
    	paint.setAA(g,true);
    	g.setFont(new Font("TimesRoman", Font.PLAIN, 60)); 
    	g.drawString("The Simulator 4891",250,200);
    	paint.setAA(g,false);
    }
}