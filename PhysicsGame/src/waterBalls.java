import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;
public class waterBalls {
	private ArrayList<waterBall> balls;
	private ArrayList<ArrayList<ArrayList<waterBall>>> balls2D;
	private GamePanel gp;
	private int size;
	public static final int asize=20;
	private boolean fancy=true;
    public waterBalls(GamePanel gamep) {
    	gp=gamep;
    	size=5;
		clear();
    }
    public void clear(){
    	balls=new ArrayList<waterBall>();//Creating balls
		balls2D=new ArrayList<ArrayList<ArrayList<waterBall>>>();//Creating balls2D
		for(int i=0;i<=(1000+asize)/asize;i++){					 //Creating balls2D
			balls2D.add(new ArrayList<ArrayList<waterBall>>());  //Creating balls2D
		}														 //Creating balls2D
		for(ArrayList<ArrayList<waterBall>> lis:balls2D){	     //Creating balls2D
			for(int i=0;i<=((800+asize)/asize);i++){             //Creating balls2D
				lis.add(new ArrayList<waterBall>());             //Creating balls2D
			}
		}
    }
    public void setFancy(boolean g){
    	fancy=g;
    }
    public void draw(Graphics2D g){
    	paint.setAA(g,true);
    	g.setColor(Color.BLUE);
    	if(fancy){
    		paint.setAlpha(g,.4f);
    		for(waterBall ball:balls){
    			ball.drawFancy(g);
    		}
    		paint.setAlpha(g,1f);
    	}
    	else{
    		for(waterBall ball:balls){
    			ball.draw(g);
    		}
    	}	
    	paint.setAA(g,false);
    }
    public void update(){
    	for(waterBall ball:balls.toArray(new waterBall[balls.size()])){
    		balls2D.get((int)ball.getX()/asize).get((int)ball.getY()/asize).remove(ball);
    		ball.addGravity();
    		balls2D.get((int)ball.getX()/asize).get((int)ball.getY()/asize).add(ball);
    	}
    	for(waterBall ball:balls.toArray(new waterBall[balls.size()])){
    		balls2D.get((int)ball.getX()/asize).get((int)ball.getY()/asize).remove(ball);
    		ball.checkMove(balls2D);
    		balls2D.get((int)ball.getX()/asize).get((int)ball.getY()/asize).add(ball);
    	}
    	for(waterBall ball:balls.toArray(new waterBall[balls.size()])){
    		balls2D.get((int)ball.getX()/asize).get((int)ball.getY()/asize).remove(ball);
    		ball.move(gp.getBBalls2D());
    		balls2D.get((int)ball.getX()/asize).get((int)ball.getY()/asize).add(ball);
    	}
    }
    public ArrayList<waterBall> getBalls(){
    	return balls;
    }
    public void removeBall(int x){
    	waterBall ball=balls.get(x);
    	balls2D.get((int)ball.getX()/asize).get((int)ball.getY()/asize).remove(ball);
    	balls.remove(x);
    }
    public void addBall(int x, int y){
    	balls.add(new waterBall(x,y,gp,size));    	
    }
    public void setSize(int s){
    	size=s;
    }
    public int getSize(){
    	return size;
    }
    public void setViscosity(double v){
    	for(waterBall ball:balls.toArray(new waterBall[balls.size()])){
    		ball.setViscosity(v);
    	}	
    }
}