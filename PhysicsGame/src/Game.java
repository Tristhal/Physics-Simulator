import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;
import javax.swing.Timer;
//###################################################\\
public class Game extends JFrame implements ActionListener{
	private Timer myTimer;   
	private GamePanel panel;
	private boolean startmenustatus;
	private boolean gamestatus;
	private boolean simulationstatus;	
    public Game() {
		super("Things.py");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,800);
		panel = new GamePanel(this);
		add(panel);
		myTimer = new Timer(15, this);
		myTimer.start();
		
		setResizable(false);
		setVisible(true);
		
		startmenustatus=true;
		gamestatus=false;
		simulationstatus=true;
    }
	int timerhold=0;
	public void actionPerformed(ActionEvent evt){
		timerhold+=1;
		if(simulationstatus){
			panel.updateBBalls();
			panel.updateWBalls();
			panel.updateMenus();
		}
		if(startmenustatus){
			panel.updateStartMenu();
		}
		if(timerhold==3){
			panel.repaint();
			timerhold=0;
		}
		panel.end();
				
	}
    public boolean getGameStatus(){
    	return gamestatus;
    }
    public boolean getStartMenuStatus(){
    	return startmenustatus;
    }
    public boolean getSimulationStatus(){
    	return simulationstatus;
    }
    public void setGameStatus(boolean b){
    	gamestatus=b;
    }
    public void setStartMenuStatus(boolean b){
    	startmenustatus=b;
    	
    }
    public void setSimulationStatus(boolean b){
    	simulationstatus=b;
    }
}
