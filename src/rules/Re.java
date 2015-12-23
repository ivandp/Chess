package rules;
import java.util.ArrayList;


import model.ScacchiModel;
import model.Punto;
import view.ScacchiView;


public class Re implements Pedina{
	 int g;
	int x;
	int y;
	
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

	public Re(int g,ScacchiModel m){
		this.g=g;
		this.x=0;
		this.y=0;
		this.m=m;
	}


	
	public  ArrayList<Punto> preemption(){
		return a();
	}
	
	public  ArrayList<Punto> a(){
		ArrayList<Punto> p= new ArrayList<Punto>(0);
		int x1=x-1;
		int y1=y-1;
		
		for(int a=0; a<3; a++)
			
			for(int i=0; i<3; i++)
				
				if(isValid(x1+a, y1+i) && !(x1+a==x && y1+i==y))
					
					if(  (m.get(x1+a,y1+i)==null || m.get(x1+a,y1+i).getGiocatore()!=g) )
						
						
							p.add(new Punto(x1+a,y1+i));
	
		return p;
				
		
	}
	
	
	public Boolean hasRePreemption(Punto g){
		ArrayList<Punto> p = preemption();
		for(int i=0;i<p.size();i++)
			if(p.get(i).getX()==g.getX() && p.get(i).getY()==g.getY())
				return true;
						return false;
	}
	
	@Override
	public int getGiocatore() {
		return g;
	}
	
	public String getImg() {
		if(g==0)
			return "img/reb.png";
		else 
			return "img/ren.png";
	}

	@Override
	public boolean isRe(int g) {
	
		if (this.g==g)
			return true;
		else
			return false;
	}
	@Override
	public ArrayList<Punto> preemptionRe(Punto g) {
		
		ArrayList<Punto> p = new ArrayList<Punto>(0);
		p.add( new Punto(g.getX(), g.getY()) );
		return p;
		
	}
	


	public  boolean isValid(int x, int y){
		
		if(isValid(x) && isValid(y) )
				return true;
		
		return false;	
		
	}
	//controlla che non si esca dai limiti della scacchiera
	public  boolean isValid(int x){
		
		if(x>=0 && x<=7)
			return true;
		return false;
	}
	
}
