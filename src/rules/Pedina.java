package rules;

import java.util.ArrayList;

import model.ScacchiModel;
import model.Punto;

public interface Pedina {
	
	public ArrayList<Punto> preemption();

	public int getGiocatore();

	public String getImg();
	
	public boolean isRe(int g);
	
	public Boolean hasRePreemption( Punto g);
	
	public ArrayList<Punto> preemptionRe( Punto g);

	public void setX(int x);
	
	public void setY(int y);
	
}
