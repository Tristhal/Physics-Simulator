import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;
import javax.swing.Timer;
class waterBall{
	private double bx,by,vx,vy;
	private int r,influence;
	public static final int sizer=1;
	public static final int asize=20;
	private Color colour;
	private GamePanel gp;
	private boolean movex,movey;
	private double viscosity;
	public waterBall(double bx, double by,GamePanel g,int size){
		gp=g;
		vx=0;
		vy=0;
		r=size;
		movex=true;
		movey=true;
		viscosity=.1;
		this.bx=bx;
		this.by=by;
		colour=new Color((int)bx/5,(int)by/5,0);
		influence=2*r;
	}
	
	public void move(ArrayList<ArrayList<ArrayList<bounceBall>>> balls){
		movex=true;
		movey=true;
		if(bx+vx<5+r){
			vx*=-.1;
		}
		else if(bx+vx>990-r){
			vx*=-.1;
		}
		if(by+vy-r<5+r){
			vy*=-.5;
		}
		else if(by+vy+r>760){
			//vy-=gp.getGravity();
			vy*=-.1;
		}
		double closestdistsqy;
		double closestdistsqx;
		for(int x=-sizer-1;x<=sizer+1;x++){
			for(int y=-sizer-1;y<=sizer+1;y++){
				if(x+(int)(bx/asize)>=0 && x+(int)(bx/asize)<=(1000/asize) && y+(int)(by/asize)>=0 && y+(int)(by/asize)<=(800/asize)){
					for(bounceBall ball:balls.get(x+(int)(bx/asize)).get(y+(int)(by/asize))){
						if(ball.getMovement()==false){
							closestdistsqx = Math.pow((bx+vx - ball.getX()),2)
							     + Math.pow((by - ball.getY()),2);
							closestdistsqy = Math.pow((bx - ball.getX()),2)
							     + Math.pow((by+vy - ball.getY()),2);
							if (closestdistsqx <= Math.pow(r + ball.getR(),2)){
								movex=false;
							}
							if(closestdistsqy <= Math.pow(r + ball.getR(),2)){
								movey=false;
							}
							if(movex==false||movey==false){
								double changex=bx-ball.getX();
						 		double changey=by-ball.getY();
						 		if(changex<=0){//ball to left
					 				//vx*=1-viscosity;
						 			vx+=-.1;
								}
								else if(changex>0){//ball to right
					    			//vx*=1-viscosity;
					    			vx+=.4;
					    		}
					    		if(changey<=0){//above
					    		//	vy*=1-viscosity;
					    			vy+=-.06-gp.getGravity();
					    		}
					    		else if(changey>0){//below
					    		//	vy*=1-viscosity;
					    		//	vy+=gp.getGravity()/5;//.02
								}
							}	
						}
						else{
							closestdistsqx = Math.pow((bx+vx - ball.getX()),2)
							     + Math.pow((by - ball.getY()),2);
							closestdistsqy = Math.pow((bx - ball.getX()),2)
							     + Math.pow((by+vy - ball.getY()),2);
							if (closestdistsqx <= Math.pow(r + ball.getR(),2)){
								movex=false;
							}
							if(closestdistsqy <= Math.pow(r + ball.getR(),2)){
								movey=false;
							}
							if(movex==false||movey==false){
								double changex=bx-ball.getX();
						 		double changey=by-ball.getY();
						 		if(changex<=0){//ball to left
					 				vx*=1-viscosity;
						 			vx+=-.1;
						 			ball.addV(-ball.getVX()*(viscosity)/3,0);
									ball.addV(Math.abs(ball.getVY())*.05,-Math.abs(ball.getVY())*.06);
								}
								else if(changex>0){//ball to right
					    			vx*=1-viscosity;
					    			vx+=.4;
					    			ball.addV(-ball.getVX()*(viscosity)/3,0);
					    			ball.addV(-Math.abs(ball.getVY())*.05,Math.abs(ball.getVY())*.06);
					    		}
					    		if(changey<=0){//above
					    			vy*=1-viscosity;
					    			vy+=-.06-gp.getGravity();
					    			ball.addV(0,-ball.getVY()*(viscosity)/3);
					    			ball.addV(0,-gp.getGravity()-Math.abs(ball.getVY()*(.01))+(50-ball.getMass())/(double)200);//-.8
					    		}
					    		else if(changey>0){//below
					    			vy*=1-viscosity;
					    			vy+=gp.getGravity()/5;//.02
					    			ball.addV(0,-ball.getVY()*(viscosity)/3);
					    			ball.addV(0,-gp.getGravity()-Math.abs(ball.getVY()*(.01))+(50-ball.getMass())/(double)200);//-.8
								}
							}
						}
						
					}
				}
			}
		}
		if(movey){
			by+=vy;
		}
		else{
			vy*=-1;
		}
		if(movex){
			bx+=vx;
		}
		else{
			vx*=-1;
		}
	}
	
	public void checkMove(ArrayList<ArrayList<ArrayList<waterBall>>> balls){
		double changex;
		double changey;
		double closestdistsq;
		for(int y=-sizer;y<=sizer;y++){
			for(int x=-sizer;x<=sizer;x++){
				if(x+(int)(bx/asize)>=0 && x+(int)(bx/asize)<=(1000/asize) && y+(int)(by/asize)>=0 && y+(int)(by/asize)<=(800/asize)){
					for(waterBall ball:balls.get(x+(int)(bx/asize)).get(y+(int)(by/asize))){
						closestdistsq = ((bx - ball.getX()) * (bx - ball.getX()))
					          + ((by - ball.getY()) * (by - ball.getY()));
					    if(closestdistsq < Math.pow(influence + ball.getInfluence(),2)){
					    	
					    	//if(closestdistsq<=Math.pow((int)r+(int)ball.getR(),2)){
					    		changex=bx-ball.getX();
					    		changey=by-ball.getY();
					    		if(changex<=0){//ball to left
					    			vx*=1-viscosity;
					    			vx+=-.1;
					    			ball.addV(.05,0);
					    		}
					    		else if(changex>0){//ball to right
					    			vx*=1-viscosity;
					    			vx+=.1;
					    			ball.addV(-.05,0);
					    		}
					    		if(changey<=0){//above
					    			vy*=1-viscosity;
					    			vy+=-.06-gp.getGravity();
					    			ball.addV(0,.06+gp.getGravity()/2);
					    		}
					    		else if(changey>0){//below
					    			vy*=1-viscosity;
					    			vy+=gp.getGravity()/5;//.02
					    			ball.addV(0,-gp.getGravity()/5*4);//-.8
					    		
					    	//	}
					    }
					    }
					}
				}
			}
		}
	}
	public Point closestPointOnLine(double lx1, double ly1, 
        double lx2, double ly2, double x0, double y0){ 
     	double a = ly2 - ly1; 
     	double b = lx1 - lx2; 
     	double c1 = (ly2 - ly1)*lx1 + (lx1 - lx2)*ly1; 
     	double c2 = -b*x0 + a*y0; 
     	double det = a*a - -b*b; 
     	double cx = 0; 
     	double cy = 0; 
     	if(det != 0){ 
    		cx = (double)((a*c1 - b*c2)/det); 
    		cy = (double)((a*c2 - -b*c1)/det); 
     	}
     	else{ 
     		cx = x0; 
     		cy = y0; 
     	} 
     	return new Point((int)cx, (int)cy); 
	}
	public void draw(Graphics2D g){
    	g.fillOval((int)bx-r*2,(int)by-r*2,r*4,r*4);
    }
    public void drawFancy(Graphics2D g){
    	g.fillOval((int)bx-r*4,(int)by-r*4,r*8,r*8);
    	//g.fillOval((int)bx-r*3,(int)by-r*3,r*6,r*6);
    	g.fillOval((int)bx-r*2,(int)by-r*2,r*4,r*4);
    }
	public void setViscosity(double v){
		viscosity=v;
	}
	
    public void addGravity(){
    	vy+=gp.getGravity();
    }
	public void setV(double x, double y){
		vx=x;
		vy=y;
	}
	public int getInfluence(){
		return influence;
	}
	public double  getVX(){
		return vx;
	}
	public double  getVY(){
		return vy;
	}
	public void addV(double x, double y){
		vx+=x;
		vy+=y;
	}
	public double getY(){
		return by;
	}
	public double getX(){
		return bx;
	}
	public int getR(){
		return r;
	}
}