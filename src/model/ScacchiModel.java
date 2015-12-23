package model;
import javax.swing.JFrame;

import rules.*;
import view.ScacchiView;


public class ScacchiModel {
	
	ScacchiView view;
	
	//creazione e inizializzazione scacchiera
	//al costruttore dei pezzi viene passato il numero del giocatore 0 corrisponde ai bianchi 1 ai neri
	
	public Pedina[][] Scacchiera = { {new Torre(0, this),new Cavallo(0,this),new Alfiere(0, this),new Donna(0, this),new Re(0, this),new Alfiere(0, this),new Cavallo(0,this),new Torre(0, this)},
		       {new Pedone(0, this),new Pedone(0, this),new Pedone(0, this),new Pedone(0,this),new Pedone(0, this),new Pedone(0, this),new Pedone(0, this),new Pedone(0, this)},
		       {null,null,null,null,null,null,null,null},
		       {null,null,null,null,null,null,null,null},
		       {null,null,null,null,null,null,null,null},
		       {null,null,null,null,null,null,null,null},
		       {new Pedone(1, this),new Pedone(1, this),new Pedone(1, this),new Pedone(1, this),new Pedone(1, this),new Pedone(1, this),new Pedone(1, this),new Pedone(1, this)},
		       {new Torre(1, this),new Cavallo(1,this), new Alfiere(1, this),new Donna(1, this),new Re (1,this),new Alfiere(1, this),new Cavallo(1,this),new Torre(1, this)} };
	
	//la var giocatore tiene il turno, viene inizializzata a 0 perchè i bianchi muovono per primi
	static int giocatore = 0;
	
	public ScacchiModel(){
		
		//assegno le coordinate alle pedine della scacchiera
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(Scacchiera[i][j]!=null){
					Scacchiera[i][j].setX(i);
					Scacchiera[i][j].setY(j);
				}
			}
		}
		
	}
	
	public Pedina get(int x, int y){
		return Scacchiera[x][y];
	}
	
	
	//inizializzazione per nuova partita
	public void initialize(){
		Pedina[][] s = { {new Torre(0, this),new Cavallo(0,this),new Alfiere(0, this),new Donna(0, this),new Re(0, this),new Alfiere(0, this),new Cavallo(0,this),new Torre(0, this)},
			       {new Pedone(0, this),new Pedone(0, this),new Pedone(0, this),new Pedone(0,this),new Pedone(0, this),new Pedone(0, this),new Pedone(0, this),new Pedone(0, this)},
			       {null,null,null,null,null,null,null,null},
			       {null,null,null,null,null,null,null,null},
			       {null,null,null,null,null,null,null,null},
			       {null,null,null,null,null,null,null,null},
			       {new Pedone(1, this),new Pedone(1, this),new Pedone(1, this),new Pedone(1, this),new Pedone(1, this),new Pedone(1, this),new Pedone(1, this),new Pedone(1, this)},
			       {new Torre(1, this),new Cavallo(1,this), new Alfiere(1, this),new Donna(1, this),new Re (1,this),new Alfiere(1, this),new Cavallo(1,this),new Torre(1, this)} };
		
		for(int x=0;x<8;x++)
			for(int y=0; y<8; y++)
				Scacchiera[x][y]=s[x][y];
		
	
		
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(Scacchiera[i][j]!=null){
					Scacchiera[i][j].setX(i);
					Scacchiera[i][j].setY(j);
				}
			}
		}
	
		
	}
	
	
	public void set(int x, int y, Pedina p) {
		
		if( x>=0 && x<=7 && y>=0 && y<=7 ){
			
			Scacchiera[x][y]=p;
			if(p!=null){
			Scacchiera[x][y].setX(x);
			Scacchiera[x][y].setY(y);
			}
		}
		
	}
	public void mangia(int x1, int y1, int x, int y) {
		Scacchiera[x][y]=Scacchiera[x1][y1];
		Scacchiera[x1][y1]=null;
		if(Scacchiera[x][y]!=null){
			Scacchiera[x][y].setX(x);
			Scacchiera[x][y].setY(y);
		}
		
	}

	
	public void scambio(int x1, int y1, int x, int y) {
		Pedina p = Scacchiera[x][y];
		Scacchiera[x][y]=Scacchiera[x1][y1];
		Scacchiera[x1][y1]=p;

		
	}
	
}
