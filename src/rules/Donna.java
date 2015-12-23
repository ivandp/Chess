package rules;
import java.util.ArrayList;
import model.ScacchiModel;
import model.punto;


public class Donna implements Pedina{

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

	public Donna(int g, ScacchiModel m){
		this.g=g;
		this.x=0;
		this.y=0;
		this.m=m;
	}
	public  boolean moveTo(int c, int x1, int y1, int x2, int y2) {
		int y=y1;
		
		for(int i=x1; i<8 && y<8; i++, y++){
			if(x1+i<8 && y1+i<8)
				if(x1+i==x2 && y1+i==y2)
					return true;
			if(x1+i<8 && y1-i>=0)
				if(x1+i==x2 && y1-i==y2)
					return true;
			if(x1-i>=0 && y1-i>=0)
				if(x1-i==x2 && y1-i==y2)
					return true;
			if(x1-i>=0 && y1+i<8)
				if(x1-i==x2 && y1+i==y2)
					return true;
		}
		
		if(x1==x2 && y1==y2)
			return false;
		if(x1==x2)
			return true;
		if(y1==y2)
			return true;
		
		return false;
		
	}
	
	public ArrayList<punto> preemption(){
		ArrayList<punto> p= new ArrayList<punto>(0);
		Alfiere a = new Alfiere(g,x,y,m);
		Torre t = new Torre(g,x,y,m);
		p.addAll(a.preemption());
		p.addAll(t.preemption());
	return p;
	}
	@Override
	public int getGiocatore() {
		return g;
	}

	public String getImg() {
		if(g==0)
			return "img/donnab.png";
		else 
			return "img/donnan.png";
	}
	@Override
	public boolean isRe(int g) {
		return false;
	}
	public Boolean hasRePreemption(punto g) {
		ArrayList<punto> p= preemption();
		if( g!=null ){
		for(int i=0;i<p.size();i++)
			if(p.get(i).getX()==g.getX() && p.get(i).getY()==g.getY())
				return true;
		}
						return false;
	}
	@Override
	public ArrayList<punto> preemptionRe(punto g) {
		if( x==g.getX() || y==g.getY() )
			return new Torre(this.g,x,y,m).preemptionRe(g);
		else
			return new Alfiere(this.g,x,y,m).preemptionRe(g);
		
	}

}
