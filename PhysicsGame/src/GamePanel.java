import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;
import javax.swing.Timer;
class GamePanel extends JPanel implements MouseMotionListener, MouseListener,KeyListener{
    // ------------ Variables ----------------------------------------------
	private int mx,my;//Mouse position x and y
	private boolean[]keys;//If the keys are pressed
	private Boolean mousepressed=false;//If the mouse is pressed down
	private Game mainframe;//The panel that created this GamePanel
	private int fade;
	private boolean fadetrue;
	private double gravity;
	private Color backmenuc;
	private Color backcolor;
	private String selectedobj;
	int waterspawnnum;
	private boolean spawnvalid;
  	
	private Menu_Start startmenu;
	private Menu_Game gamemenu;
	private Menu_Water watermenu;
	private Menu_Solid solidmenu;
	private Menu_BounceBall bounceballmenu;
	private bounceBalls bballs;
	private Color ballcolour;
	private int ballmass;
	private boolean ballmovement;
	
	private waterBalls wballs;
	
	private boolean mouseclicked;
	
	public int getWidth(){
		return 1000;
	}
	public int getHeight(){
		return 800;
	}
	// ------------ Constructor --------------------------------------------
	public GamePanel(Game m){
		//----- Listeners -----
		addMouseMotionListener(this);//LISTEN TO ME!!!
		addMouseListener(this);//Listen to me!
		addKeyListener(this);//listen to me pls
		fade=0;
		//----- Variables -----
		mainframe=m;
		keys=new boolean[KeyEvent.KEY_LAST+1];//creates the array for keys
		backcolor=new Color(0,0,0);
		backmenuc=new Color(255,255,255);
		
		selectedobj="Water";
		
		bballs=new bounceBalls(this);
		ballcolour=new Color(255/2,255/2,255/2);
		ballsize=10;
		ballmass=25;
		ballmovement=true;
		
		wballs=new waterBalls(this);
		waterspawnnum=6;
		
		spawnvalid=true;
		gravity=.1;
		//----- Menus -----
		startmenu=new Menu_Start(this);
		bounceballmenu=new Menu_BounceBall(this);
		watermenu=new Menu_Water(this);
		gamemenu=new Menu_Game(this);
		solidmenu=new Menu_Solid(this);
		//----- Load Images -----
		//----- End -----
	}
	// ------------ Update Menues ------------------------------------------
	public void updateBounceBallMenu(){
		bounceballmenu.update();
	}
	public void updateWaterMenu(){
		watermenu.update();
	}
	public void updateStartMenu(){
		startmenu.update();
	}
	public void updateGameMenu(){
		gamemenu.update();
	}
	public void updateSolidMenu(){
		solidmenu.update();
	}
	public void updateMenus(){
		spawnvalid=true;
		updateGameMenu();
		if(selectedobj=="BBall"){
			updateBounceBallMenu();
		}
		else if(selectedobj=="Water"){
			updateWaterMenu();
		}
		else if(selectedobj=="Solid"){
			updateSolidMenu();
		}
	}
	// ------------ Update Pysics Objects ----------------------------------
	private int ballsize;
	public void updateBBalls(){
		bballs.update();
	}
	public void updateWBalls(){
		wballs.update();
	}
	public void setFade(Boolean b){
		fade=0;
		fadetrue=b;
	}
	// ------------ Drawing ------------------------------------------------
    public void paintComponent(Graphics gg){
    	Graphics2D g=(Graphics2D) gg;
    	paint.drawRect(g,0,0,getWidth(),getHeight(),backcolor);
    	bballs.draw(g);
	    wballs.draw(g);
	    g.setColor(new Color(0,0,0));
  	    	if(mainframe.getStartMenuStatus()){
	    		startmenu.draw(g);
	    		if(wballs.getBalls().size()<200){
	    			//bballs.addBall((int)(Math.random()*1000),(int)(Math.random()*160)+40,(int)(Math.random()*14)+5,(int)(Math.random()*49)+1,new Color(255,255,255),true);
	    			wballs.addBall((int)(Math.random()*1000),(int)(Math.random()*160)+40);
	    		}
	    		else{
	    			if((int)(Math.random()*2500)==512){
	    			wballs.clear();
	    		}
	    		}
	    		if((int)(Math.random()*100)==5){
	    			wballs.setViscosity(Math.random()*10/100);
	    		}
	    		if((int)(Math.random()*500)==434){
	    			gravity*=-1;
	    		}
	    		if((int)(Math.random()*600)==5){
	    			gravity=gravity==.1?0:.1;
	    		}
	    		if((int)(Math.random()*300)==5){
	    			gravity=gravity==0?.1:gravity;
	    		}
	    		if((int)(Math.random()*300)==5){
	    			wballs.setSize((int)(Math.random()*3+4));
	    		}
	    		
	    		
	    	}
	    	
	    	if(mainframe.getGameStatus()){
	    		
	    		gamemenu.draw(g);
	    		if(selectedobj=="BBall"){
	    			bounceballmenu.draw(g);
	    			paint.drawAACircle(g,mx,my,ballsize,ballcolour,.2f);
	    		}
	    		else if(selectedobj=="Water"){
	    			watermenu.draw(g);
	    			paint.drawAACircle(g,mx,my,wballs.getSize(),Color.BLUE,.2f);
	    		}
	    		else if(selectedobj=="Solid"){
					solidmenu.draw(g);
					paint.drawAACircle(g,mx,my,solidsize,solidcolour,.2f);
				}
	    		
	    		
	    	}
	    if(fadetrue){
    		if(fade>100){
    			fadetrue=false;
    		}
    		fade+=1;
    		paint.drawRect(g,0,0,getWidth(),getHeight(),backcolor,(float)(1-((float)fade/102)));
    	}
    }
    
    // ------------ Misc ---------------------------------------------------
    public void end(){
    	mouseclicked=false;
    	mxlast=mx;
    	mylast=my;
    }
    public ArrayList<ArrayList<ArrayList<bounceBall>>> getBBalls2D(){
    	return bballs.getBalls2D();
    }
    public void addNotify() {
        super.addNotify();
    }
    public void setWaterSpawnNum(int d){
    	waterspawnnum=d;
    }
    public void setBallMovement(boolean b){
    	ballmovement=b;
    }
    public void setBallSize(int s){
    	ballsize=s;	
    }
    public void setWaterSize(int s){
    	wballs.setSize(s);
    }
    public boolean getSpawnValid(){
    	return spawnvalid;
    }
    public void setSelectedObj(String s){
    	selectedobj=s;
    }
    public void setSpawnValid(boolean b){
    	spawnvalid=b;
    }
    public double getGravity(){
    	return gravity;
    }
    public void setGravity(double g){
    	gravity=g;
    }
    public void setBBallColour(Color c){
    	ballcolour=c;
    }
    public void setBBallMass(int m){
    	ballmass=m;
    }
    public void setViscosity(double d){
    	wballs.setViscosity(d);
    }
    public void setMassMode(boolean b){
    	bballs.setMassMode(b);
    }
    public void setWFancy(boolean b){
    	wballs.setFancy(b);
    }
    public void clearWater(){
    	wballs.clear();
    }
    public void clearBBalls(){
    	bballs.clear();
    }
    public void clearAllBalls(){
    	bballs.clear();
    	wballs.clear();
    	bballs.clearStatic();
    }
    public void setSBallColour(int r,int g, int b){
    	solidcolour=new Color(r,g,b);
    }
    public void setSBallSize(int s){
    	solidsize=s;
    }
    public void clearSBalls(){
    	bballs.clearStatic();
    }
    // ------------ MouseListener ------------------------------------------
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {
    	mousepressed=false;
    	mouseclicked=false;
    	
    }    
    public void mouseClicked(MouseEvent e){	
    }
    private int mxlast=0;
    private int mylast=0;
    private int solidsize=10;
    private Color solidcolour=new Color(255/2,255/2,255/2);
    public void mousePressed(MouseEvent e){
    	mousepressed=true;
    	mouseclicked=true;
    	if(mainframe.getGameStatus() && spawnvalid){
    		if(selectedobj=="BBall"){
    			bballs.addBall(mx,my,ballsize,ballmass,ballcolour,ballmovement);
    		}
    		else if(selectedobj=="Water"){
    			for(int i=0;i<waterspawnnum;i++){
    				wballs.addBall(mx+i*wballs.getSize(),my);
    			}
    		}
    	}
	}
    // ---------- MouseMotionListener --------------------------------------
    public void mouseDragged(MouseEvent e){
    	mx=e.getX();
    	my=e.getY();
    	if(mainframe.getGameStatus() && spawnvalid){
	    	if(selectedobj=="Solid"){
	    		bballs.addBall(mx,my,solidsize,0,solidcolour,false);
	    	}
    	}
    }
    public void mouseMoved(MouseEvent e){
    	
    	mx=e.getX();
    	my=e.getY();
    }
   	// ---------- KeyListener -----------------------------------------------
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    public boolean getMousePressed(){
    	return mousepressed;
    }
    public boolean getMouseClicked(){
    	return mouseclicked;
    }
    public int getMX(){
    	return mx;
    }
    public int getMY(){
    	return my;
    }
    public Game getMainFrame(){
		return mainframe;
	}
}