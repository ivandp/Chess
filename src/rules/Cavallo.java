package rules;
import java.util.ArrayList;
import model.ScacchiModel;
import model.Punto;



public class Cavallo implements Pedina{

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

	public Cavallo(int g, ScacchiModel m){
		this.g=g;
		this.x=0;
		this.y=0;
		this.m=m;
	}

	public ArrayList<Punto> preemption(){
		Punto[] arrayp = {new Punto(x+1,y-2), new Punto(x+1,y+2), 
				     new Punto(x+2,y-1), new Punto(x+2,y+1), 
				     new Punto(x+2,y-1), new Punto(x-1,y-2), 
				     new Punto(x-1,y+2), new Punto(x-2,y-1),
				     new Punto(x-2,y+1), new Punto(x-2,y-1)
		};
		
		ArrayList<Punto> arr = new ArrayList<Punto>(0);
		
		for(int i=0; i<arrayp.length; i++){
			if(isValid(arrayp[i].getX(), arrayp[i].getY() ) )
					arr.add(arrayp[i]);
		}
		
		ArrayList<Punto> p = new ArrayList<Punto>(0);
		
		for(int i=0; i < arr.size(); i++){
			if(m.get(arr.get(i).getX(),arr.get(i).getY())==null)
				p.add(new Punto(arr.get(i).getX(),arr.get(i).getY()));
			if(m.get(arr.get(i).getX(),arr.get(i).getY())!=null && m.get(arr.get(i).getX(),arr.get(i).getY()).getGiocatore()!=g){
				p.add(new Punto(arr.get(i).getX(),arr.get(i).getY()));
				
			}
			
		}
		
		return p;
	}


	@Override
	public int getGiocatore() {
		return g;
	}
	
	public String getImg() {
		if(g==0)
			return "img/cavallob.png";
		else 
			return "img/cavallon.png";
	}

	@Override
	public boolean isRe(int g) {
		return false;
	}
	public Boolean hasRePreemption(Punto g) {
		ArrayList<Punto> p= preemption();
		if( g!=null ){
		for(int i=0;i<p.size();i++)
			if(p.get(i).getX()==g.getX() && p.get(i).getY()==g.getY())
				return true;
		}
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
