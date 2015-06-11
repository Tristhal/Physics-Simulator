import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;
class bounceBalls{
	GamePanel gp;
	ArrayList<bounceBall> balls;
	ArrayList<ArrayList<ArrayList<bounceBall>>> balls2D;
	double gravity;
	public static final int asize=20;
	public bounceBalls(GamePanel gamep){
		gp=gamep;
		gravity=.1;
		balls=new ArrayList<bounceBall>();//Creating balls
		balls2D=new ArrayList<ArrayList<ArrayList<bounceBall>>>();//Creating balls2D
		for(int i=0;i<=(1000+asize)/asize;i++){							  //Creating balls2D
			balls2D.add(new ArrayList<ArrayList<bounceBall>>());  //Creating balls2D
		}														  //Creating balls2D
		for(ArrayList<ArrayList<bounceBall>> lis:balls2D){	      //Creating balls2D
			for(int i=0;i<=((800+asize)/asize);i++){                        //Creating balls2D
				lis.add(new ArrayList<bounceBall>());             //Creating balls2D
			}                                               	
		}
	}

	public void clear(){
		for(bounceBall ball:balls.toArray(new bounceBall[balls.size()])){
			if(ball.getMovement()){
				balls2D.get((int)ball.getX()/asize).get((int)ball.getY()/asize).remove(ball);
				balls.remove(ball);
			}
		}
	}
	public void draw(Graphics2D g){
		paint.setAA(g,true);
		for(bounceBall ball:balls){
			ball.draw(g);
		}
		paint.setAA(g,false);
	}

	public void update(){
		for(bounceBall ball:balls.toArray(new bounceBall[balls.size()])){
			ball.resetCollided();//resets the balls2D arraylist of circles on the screen
			ball.resetMove();
			if (Double.isNaN(ball.getX()) || Double.isNaN(ball.getY())){
				balls.remove(ball);
			}
			
		}
		for(bounceBall ball:balls.toArray(new bounceBall[balls.size()])){
			if((int)ball.getX()/asize>=0 && (int)ball.getX()/asize<=(1000/asize) && (int)ball.getY()/asize>=0 && (int)ball.getY()/asize<=(800/asize)){
			balls2D.get((int)ball.getX()/asize).get((int)ball.getY()/asize).remove(ball);
			ball.checkCollision(balls2D);
			balls2D.get((int)ball.getX()/asize).get((int)ball.getY()/asize).add(ball);
			}
			else{
				balls.remove(ball);
			}
			
		}
		for(bounceBall ball:balls.toArray(new bounceBall[balls.size()])){
			if((int)ball.getX()/asize>=0 && (int)ball.getX()/asize<=(1000/asize) && (int)ball.getY()/asize>=0 && (int)ball.getY()/asize<=(800/asize)){
				balls2D.get((int)ball.getX()/asize).get((int)ball.getY()/asize).remove(ball);
				ball.addGravity();
				ball.move(balls2D);
				balls2D.get((int)ball.getX()/asize).get((int)ball.getY()/asize).add(ball);//Puts back in in new posiionballs2D.get((int)ball.getX()/20).get((int)ball.getY()/20).remove(ball);
			}
			else{
				balls.remove(ball);
			}
			
		}
	}
	public void addBall(int x, int y, int radius,int mass,Color colour,boolean move){
		balls.add(new bounceBall(x,y,radius,mass,colour,this,move));
	}
	public void setGravity(double g){
		gravity=g;
	}
	public double getGravity(){
		return gp.getGravity();
	}
	public int getSize(){
		return balls.size();
	}
	public void setMassMode(boolean b){
		for(bounceBall ball:balls.toArray(new bounceBall[balls.size()])){
			ball.setMassMode(b);
		}
	}
	public void clearStatic(){
		for(bounceBall ball:balls.toArray(new bounceBall[balls.size()])){
			if(ball.getMovement()==false){
				balls2D.get((int)ball.getX()/asize).get((int)ball.getY()/asize).remove(ball);
				balls.remove(ball);
			}
		}
	}
}