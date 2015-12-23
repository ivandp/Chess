package rules;

import java.util.ArrayList;

import model.ScacchiModel;
import model.punto;

public interface Pedina {
	
	public ArrayList<punto> preemption();

	public int getGiocatore();

	public String getImg();
	
	public boolean isRe(int g);
	
	public Boolean hasRePreemption( punto g);
	
	public ArrayList<punto> preemptionRe( punto g);

	public void setX(int x);
	
	public void setY(int y);
	
}
