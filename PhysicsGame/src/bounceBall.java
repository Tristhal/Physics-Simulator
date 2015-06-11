import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;
import javax.swing.Timer;
class bounceBall{
	private double bx,by;
	private int br,mass;
	private double vx,vy;
	private Color colour;
	private boolean moved=false;
	private boolean movedx=false;
	private boolean movedy=false;
	private boolean collided=false;
	private boolean movement;
	private bounceBalls bballs;
	public static final int sizer=3;
	public static final int asize=20;
	ArrayList<bounceBall> collidedwith;
	public bounceBall(int x,int y,int radius,int mass,Color colour,bounceBalls bballs,boolean b){
		movement=b;
		bx=(double)x;
		by=(double)y;
		br=radius;
		this.bballs=bballs;
		this.colour=colour;
		vx=0;//Math.random()*2-1;
		vy=0;
		this.mass=mass;
		collidedwith=new ArrayList<bounceBall>();//balls collided by this
	}
	public boolean getMovement(){
		return movement;
	}
	private boolean movex;
	private boolean movey;
	private double closestdistsqx;
	private double closestdistsqy;
	public void checkBoundaries(){
		
		if((by+vy)>760-1*br){
			vy-=bballs.getGravity();
			vy*=-1;
			by=760-1*br;
		}
		else if((by+vy)<1*br){
			by=1*br;
		}
		if((bx+vx)>1000-br){
			vx*=-1;
			bx=1000-br;
		}
		else if((bx+vx)<br){
			vx*=-1;
			bx=br;			
		}
	}
	public void move(ArrayList<ArrayList<ArrayList<bounceBall>>> balls){
		if(movement){
			movex=true;
			movey=true;
			if((by+vy)>760-1*br){
				vy-=bballs.getGravity();
				vy*=-1;
				movey=false;
			}
			else if((by+vy)<1*br){
				vy-=bballs.getGravity();
				vy*=-1;
			}
			if((bx+vx)>1000-br){
				vx*=-.9;
			}
			else if((bx+vx)<br){
				vx*=-.9;			
			}
			for(int x=-sizer;x<=sizer;x++){
				for(int y=-sizer;y<=sizer;y++){
					if(x+(int)(bx/asize)>=0 && x+(int)(bx/asize)<=(1000/asize) && y+(int)(by/asize)>=0 && y+(int)(by/asize)<=(800/asize)){
						for(bounceBall ball:balls.get(x+(int)(bx/asize)).get(y+(int)(by/asize))){
							if(ball.getMoved()==false){
								closestdistsqx = Math.pow((bx+vx - ball.getX()),2)
							     + Math.pow((by - ball.getY()),2);
							    closestdistsqy = Math.pow((bx - ball.getX()),2)
							     + Math.pow((by+vy - ball.getY()),2);
								
							}
							else{
								closestdistsqx = Math.pow((bx+vx - ball.getX()+ball.getVX()),2)
							          + Math.pow((by - ball.getY()+ball.getVY()),2);
							    closestdistsqy = Math.pow((bx - ball.getX()+ball.getVX()),2)
							          + Math.pow((by+vy - ball.getY()+ball.getVY()),2);
							}
							closestdistsqx = Math.pow((bx+vx - ball.getX()),2)
							     + Math.pow((by - ball.getY()),2);
							    closestdistsqy = Math.pow((bx - ball.getX()),2)
							     + Math.pow((by+vy - ball.getY()),2);
							if (closestdistsqx <= Math.pow(br + ball.getR(),2)){
								movex=false;
							}
							if(closestdistsqy <= Math.pow(br + ball.getR(),2)){
								movey=false;
							}
						}
					}
				}
			}
			if(movex){
				moved=true;
				bx+=vx;
			}
			if (movey){
				moved=true;
				by+=vy;	
			}	
		}	
	}
	public double collissionloss=.99;
	double backdist;
	double movementvectorlength;
	double cx1;
	double cy1;
	double dist;
	double nx;
	double ny;
	double p;
	public void checkCollision(ArrayList<ArrayList<ArrayList<bounceBall>>> balls){
		if(movement==false){
			vx=0;
			vy=0;
			mass=0;
		}
		else{
		for(int y=-sizer;y<=sizer;y++){
			for(int x=-sizer;x<=sizer;x++){
				if(x+(int)(bx/asize)>=0 && x+(int)(bx/asize)<=(1000/asize) && y+(int)(by/asize)>=0 && y+(int)(by/asize)<=(800/asize)){
				for(bounceBall ball:balls.get(x+(int)(bx/asize)).get(y+(int)(by/asize))){
					double closestdistsq = ((bx+vx - ball.getX()) * (bx+vx - ball.getX()))
					          + ((by+vy - ball.getY()) * (by+vy - ball.getY()));
					if (closestdistsq < Math.pow(br + ball.getR(),2) && ball.collidedWith(this)==false){
						collided =true;
						Point d = closestPointOnLine(bx, by, bx + vx, by + vy, ball.getX(), ball.getY()); 
					    backdist = Math.sqrt(Math.pow(br + ball.getR(), 2) - closestdistsq); 
						movementvectorlength = Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2)); 
						cx1 = d.x - backdist * (vx / movementvectorlength); 
						cy1 = d.y - backdist * (vy / movementvectorlength);
						dist = Math.sqrt(Math.pow(cx1 - ball.getX(), 2) + Math.pow(cy1 - ball.getY(), 2)); 
						nx = (ball.getX() - cx1) / dist; 
						ny = (ball.getY() - cy1) / dist; 
						p = 2 * (vx * nx + vy * ny - ball.getVX() * nx - ball.getVY() * ny) / 
						        (mass + ball.getMass()); 
						//double vx1 = vx - p * mass * nx; 
						//double vy1 = vy - p * mass * ny; 
						//double vx2 = ball.getVX() + p * ball.getMass() * nx; 
					    //double vy2 = ball.getVY() + p * ball.getMass() * ny;
						vx=collissionloss*(vx - p * mass * nx);
						vy=collissionloss*(vy - p * mass * ny);
						ball.setV(collissionloss*(ball.getVX() + p * ball.getMass() * nx),collissionloss*(ball.getVY() + p * ball.getMass() * ny));
						ball.collideWith(this);
						break;
					}

				}
				}
			}
			}
		}
		
	}
	public void collideWith(bounceBall b){
		collidedwith.add(b);
		collided=true;
	}
	public void resetCollided(){
		collided=false;
		collidedwith=new ArrayList<bounceBall>();
	}
	public boolean collidedWith(bounceBall ball){
		return collidedwith.contains(ball);
	}
	public boolean getMoved(){
		return moved;
	}
	public boolean getMoveY(){
		return movey;
	}
	public boolean getMoveX(){
		return movex;
	}
	public void resetMove(){
		moved=false;
		movey=false;
		movex=false;
	}
	public void addV(double x, double y){
		vx+=x;
		vy+=y;
	}
	public void setV(double x, double y){
		vx=x;
		vy=y;
	}
	public void addGravity(){
		vy+=bballs.getGravity();
		if(movement==false){
			vx=0;
			vy=0;
		}
	}
	public int getMass(){
		return mass;
	}
	public double  getVX(){
		return vx;
	}
	public double  getVY(){
		return vy;
	}

	public double getY(){
		return by;
	}
	public double getX(){
		return bx;
	}
	public int getR(){
		return br;
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
	public void setMassMode(boolean b){
		massmode=b;
	}
	private boolean massmode=false;
	public void draw(Graphics2D g){
		g.setColor(colour);
		if(massmode&&movement){
			for(int i=0;i<5;i++){
				paint.drawAACircle(g,(int)bx,(int)by,br-(int)((double)br/4)*i,new Color(255,mass*4,0),.1f);
			}	
		}
		else{
			g.fillOval((int)bx-br,(int)by-br,br*2,br*2);
		}
		
		
		
		
	}
}