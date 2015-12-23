package rules;
import java.util.ArrayList;

import view.ScacchiView;
import model.ScacchiModel;
import model.punto;


public class Pedone implements Pedina{
	
	int g,x,y;
	ScacchiModel m;
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	
	public Pedone(int g, ScacchiModel m){
		this.g=g;
		this.x=0;
		this.y=0;
		this.m=m;
	}

	public int getGiocatore(){
		return g;
	}
	
	public ArrayList<punto> preemption(){
		ArrayList<punto> p= new ArrayList<punto>(0);
		
			
			if( isValid(x+2, y) && g==0 && x==1 && m.get(x+2, y) == null){
				p.add(new punto(x+2, y));
			}
			if( isValid(x-2, y) && g==1 && x==6 && m.get(x-2, y) == null){
				p.add(new punto(x-2, y));
			}
			if( isValid( ( g==0) ? x+1 : x-1, y ) && m.get( ( g==0) ? x+1 : x-1, y)==null){
				p.add(new punto( ( g==0) ? x+1 : x-1, y));
			}
			
				
			if(isValid(x+1,y-1) && m.get( ( g==0) ? x+1 : x-1, y-1)!=null && m.get( ( g==0) ? x+1 : x-1, y-1).getGiocatore()!=g) 
				p.add(new punto( ( g==0) ? x+1 : x-1 ,y-1));
			if(isValid(x+1,y+1) && m.get( ( g==0) ? x+1 : x-1, y+1)!=null && m.get( ( g==0) ? x+1 : x-1, y+1).getGiocatore()!=g) 
				p.add(new punto( ( g==0) ? x+1 : x-1 ,y+1));
				
		
	
		return p;
	}

	public String getImg() {
		if(g==0)
			return "img/pedoneb.png";
		else 
			return "img/pedonen.png";
	}

	@Override
	public boolean isRe(int g) {
		
		return false;
	}
	@Override
	public Boolean hasRePreemption(punto g) {
		ArrayList<punto> p= preemptionRe(new punto(0,0));
		if( g!=null ){
		for(int i=0;i<p.size();i++)
				if(p.get(i).getX()==g.getX() && p.get(i).getY()==g.getY())
					return true;
		}
		return false;
		}
	
	@Override
	
	public ArrayList<punto> preemptionRe(punto k) {
		
		ArrayList<punto> p = new ArrayList<punto>(0);
		
		if(isValid(x+1,y-1) ) 
			p.add(new punto( ( g==0) ? x+1 : x-1 ,y-1));
		if(isValid(x+1,y+1) ) 
			p.add(new punto( ( g==0) ? x+1 : x-1 ,y+1));

		return p;
		
	}
	
	
	public static boolean isValid(int x, int y){
		
		if(isValid(x) && isValid(y) )
				return true;
		
		return false;	
		
	}
	
	//controlla che non si esca dai limiti della scacchiera
	public static boolean isValid(int x){
		
		if(x>=0 && x<=7)
			return true;
		return false;
	}



}
