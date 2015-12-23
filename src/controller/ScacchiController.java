package controller;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import rules.Alfiere;
import rules.Cavallo;
import rules.Donna;
import rules.Pedina;
import rules.Pedone;
import rules.Re;
import rules.Torre;
import model.ScacchiModel;
import model.Punto;
import view.CambioPedone;
import view.ScacchiView;
import view.ScaccoDialog;
import view.SolvedDialog;
import controller.*;



public class ScacchiController {
	private ScacchiModel m = null;
	private ScacchiView v = null;
	 int x1=0,y1=0,x2=0,y2=0,t=0,giocatore=0,motto=0;
	 private ArrayList<Punto> p;

	//alla creazione del controller vanno passati model e view
	public ScacchiController(ScacchiModel m, ScacchiView v){
		
		this.m = m;
		this.v = v;
		
	}

	public void add(int x, int y){
		if(t==0){
				if( m.get(x, y)!=null && m.get(x, y).getGiocatore() == giocatore ){
				//registro il primo click x e y
				x1=x;
				y1=y;
				Pedina pedina = m.get(x, y);
				t++;
				if( scacco(giocatore) ){
					ArrayList<Punto> prev;
					if(pedina instanceof Re){
						prev = intersezioneInv(pedina.preemption(), arrayListPreeTutti(giocatore==0 ? 1 : 0));
					}else{
						prev = intersezione(pedina.preemption(), arrayListScaccoRe(giocatore==0 ? 1 : 0));
					}
					if(prev!=null){
						v.colorizeRed(prev);
						t++;
					} else{
						v.setBackground(x, y);
						t=0;
					}
					
				} else{
					v.colorizeRed(pedina.preemption());
 				}
			}
		//SECONDO CLICK
		} else {	
			if( (suicidio(x1,y1,x,y,giocatore) && suicidio1(x1,y1,x,y,giocatore)) || (x1==x && y1==y) ){
				t=0;
				} else {

				
			
			ArrayList<Punto> pree;
			if( scacco(giocatore) ){
				
				Pedina pedina = m.get(x1, y1);
				if(pedina instanceof Re){
					pree = intersezioneInv(pedina.preemption(), arrayListPreeTutti(giocatore==0 ? 1 : 0));
				}else{
					pree = intersezione(pedina.preemption(), intersezione( arrayListScaccoRe(giocatore==0 ? 1 : 0), arrayListPreeTuttiNoRe(giocatore)) );
					

				}
				
				if( controlloPuntoArray(pree,new Punto(x,y)) ){
					
					m.mangia(x1,y1,x,y);
					t=0;
					if( (scaccoMatto(giocatore == 0 ? 1 : 0) || scaccoMatto(giocatore)) ){
						
						new SolvedDialog(v,this).setVisible(true);
						v.onConfigurationChange();
						return;
						
					}else{
						cambioTurno();
					}

				}
				else
					

					t=0;
			} else{
				
				Pedina pedina = m.get(x1, y1);
				if(pedina instanceof Re){
					pree = intersezioneInv(pedina.preemption(), arrayListPreeTutti(giocatore==0 ? 1 : 0));
				}else{
					pree = pedina.preemption();
					

				}
				if( controlloPuntoArray(pree,new Punto(x,y)) ){
					m.mangia(x1,y1,x,y);
					t=0;
					
					if( (scaccoMatto(giocatore == 0 ? 1 : 0) || scaccoMatto(giocatore)) ){
						
						new SolvedDialog(v,this).setVisible(true);
						v.onConfigurationChange();
						return;
						
					}else
						cambioTurno();
					
				} else{
					t=0;
				}
			}
			}
			
			controlloPedoni();
			v.onConfigurationChange();
			
		}
		
			
	}
		
	
	
	private void cambioTurno() {
		
		if(giocatore==0)
			giocatore++;
		else
			giocatore=0;
		
	}
	
	//scambia il pedone con il pezzo scelto quando si arriva all'estremità opposta della scacchiera
	public void scambioPedoneEstremi(int x, Punto p, int giocatore){
		
		Pedina o = null;
		switch (x){
			case 0: o = new Torre(giocatore,m); break;
			case 1: o = new Alfiere(giocatore,m);  break;
			case 2: o = new Cavallo(giocatore,m);  break;
			case 3: o = new Donna(giocatore, m); break;
		}
		o.setX(p.getX());
		o.setY(p.getY());
		m.set(p.getX(), p.getY(), o);
	}
	
	private void controlloPedoni(){
		for(int i=0; i<8;i++){
			if(m.get(0, i) instanceof Pedone ) {
				new CambioPedone(v, this, new Punto (0,i), 1 ).setVisible(true);
			}
			if( m.get(7, i) instanceof Pedone ){
				new CambioPedone(v, this, new Punto (7,i), 0 ).setVisible(true);
			}
				
		}
				
	}

	public Boolean scaccoMatto(int g){
		if( scacco(g) ){
		ArrayList <Punto> p = new ArrayList();
		Boolean test1= true, test2=true;
		Punto rePunto = getRe(g);
		Pedina re = returnRe(g);
		p=re.preemption();
		for(int i=0;i<p.size();i++){
			
			if (suicidio( rePunto.getX(), rePunto.getY(), p.get(i).getX(), p.get(i).getY(), g ) == false){
				test1 = false;
				
			}
		}if(intersezione( arrayListScaccoRe(g==0 ? 1 : 0) , arrayListPreeTuttiNoRe(g)).size()!=0)
			test2=false;
		
		if((test1 && test2)==true){
			
			return true;
		} else
			return test1 && test2;
		}
		return false;
	}
		
	
	//ritorna vero quando il re è sotto scacco da un qualsiasi pezzo avversario
		public Boolean scacco(int g){
			
			if( (arrayListScaccoRe(g==0 ? 1 : 0)).size() != 0 ){
				return true;
			}
			else{
				return false;
			}
			
		}
		
		
		/* 
		 *ritorna la preemption dei pezzi che mettono sotto scacco il re(solo le caselle tra re e pezzo, 
		 *non tutte le direzioni della preemption del pezzo che mette sotto scacco)
		 */
		
		private  ArrayList<Punto> arrayListScaccoRe(int s){
			
			ArrayList<Punto> p = new ArrayList<Punto>();
			//r=posizione del re avversario
			Punto r =  getRe( (s==0)?1:0 );
			//preemption del re avversario
			ArrayList<Punto> rePree;
			if(returnRe( (s==0)?1:0 )!=null)
				rePree = returnRe( (s==0)?1:0 ).preemption();
			 
			Pedina ped;
			
			//scorro la scacchiera, per ogni pezzo del giocatore s(non avversario)
			for(int i=0;i<8;i++){
				for(int y=0; y<8; y++){
					ped=m.get(y,i);
					if(ped!=null && ped.getGiocatore() == s ) {
						//controllo se nella previsione del pezzo è presente il re avversario
							if( !(ped instanceof Re) &&ped.hasRePreemption( r )){
								//se il re è presente aggiungo l'array ritornatomi da preemptionre()
								p.addAll( ped.preemptionRe(r) );
								p.add( new Punto(y,i) );
							}
							
							
					}
							
				}
			}
			
		
			
			for(int i=0;i<p.size();i++)
				if( p.get(i).getX() == r.getX() && p.get(i).getY() == r.getY() )
					p.remove(i);
			
			return p;
		}
		
		
		private  ArrayList<Punto> arrayListPreeTutti(int s){
			
			ArrayList<Punto> p = new ArrayList<Punto>();
			//r=posizione del re avversario
			Punto r =  getRe( s );
			//preemption del re avversario
			
			
			Pedina corr;
			
			//scorro la scacchiera, per ogni pezzo del giocatore s(non avversario)
			for(int i=0;i<8;i++){
				for(int y=0; y<8; y++){
					corr=m.get(i,y);
					if(corr!=null && corr.getGiocatore() == s  ) {
						
						if(corr instanceof Pedone){
							p.addAll( corr.preemptionRe(null) );
							p.add( new Punto(i,y) );
							
						}else{
							p.addAll( corr.preemption() );
							p.add( new Punto(i,y) );
								
							}
						}
							
							
							
					}
							
				}
			
			return p;
		}
		
		
		private  ArrayList<Punto> arrayListPreeTuttiNoRe(int s){
			
			ArrayList<Punto> p = new ArrayList<Punto>();
			//r=posizione del re avversario
			Punto r =  getRe( s );
			//preemption del re avversario
			
			
			Pedina corr;
			
			//scorro la scacchiera, per ogni pezzo del giocatore s(non avversario)
			for(int i=0;i<8;i++){
				for(int y=0; y<8; y++){
					corr=m.get(i,y);
					if(corr!=null && corr.getGiocatore() == s && !(corr instanceof Re) ) {
						
						if(corr instanceof Pedone){
							p.addAll( corr.preemption( ) );
							p.add( new Punto(i,y) );
							
						}else{
							p.addAll( corr.preemption() );
							p.add( new Punto(i,y) );
								
							}
						}
							
							
							
					}
							
				}
			
			return p;
		}
		
		
		private  ArrayList<Punto> intersezione( ArrayList<Punto> p2, ArrayList<Punto> o ) {
			
			ArrayList<Punto> p = new ArrayList<Punto>(0);
			for( int i=0; i<p2.size(); i++ )
				for( int h=0; h<o.size(); h++ )
					if( p2.get(i).getX() == o.get(h).getX() && p2.get(i).getY() == o.get(h).getY() )
						
						p.add(new Punto( p2.get(i).getX(),  p2.get(i).getY()) );
			
			return p;
		}
		
		
		private boolean suicidio(int x1, int y1,int x2, int y2, int g ){
			
			Boolean test = false;
			Pedina p = m.get(x1,y1);
			Pedina p1 = m.get(x2, y2);
			m.scambio(x2,y2,x1,y1);
		
			if( scacco(g) ){
				test = true;
			}
			m.set(x1,y1,p);
			m.set(x2,y2,p1);
			m.toString();
			return test;
			
		}
		
		private boolean suicidio1(int x1, int y1,int x2, int y2, int g ){
		
			Boolean test = false;
			Pedina p = m.get(x1,y1);
			Pedina p1 = m.get(x2, y2);
			m.mangia(x2,y2,x1,y1);
			
			if( scacco(g) ){
				test = true;
			}
			m.set(x1,y1,p);
			m.set(x2,y2,p1);
			
			return test;
			
		}
		
		
		//tutti i punti di p2 che non appaiono mai in o
		//scorre tutto p2 se c'è un elemento contenuto anche in o, lo rimuovere dal risultato p, se nella preemption p2
		//è presente un pezzo avversario non difeso, questo pezzo verrà aggiunto alla preemption.
		private  ArrayList<Punto> intersezioneInv(ArrayList<Punto> p2, ArrayList<Punto> o) {
			
			ArrayList<Punto> p = new ArrayList<Punto>(0);
			Boolean test= false;
			for( int i=0; i<p2.size(); i++ ){
				test=false;
				for( int h=0; h<o.size(); h++ ){
					if( p2.get(i).getX() == o.get(h).getX() && p2.get(i).getY() == o.get(h).getY() )
						test=true;
					Pedina ped =  m.get( p2.get(i).getX(), p2.get(i).getY() );
					if( (h==o.size()-1 && test==false) || (ped!= null && ped.getGiocatore()!=giocatore) )
						p.add( new Punto( p2.get(i).getX(),  p2.get(i).getY()) );
				}
			}
			return p;
		}
		
		private  Boolean controlloPuntoArray( ArrayList<Punto> p2, Punto g ) {
			
			ArrayList<Punto> p = new ArrayList<Punto>(0);
			for( int i=0; i<p2.size(); i++ )
				
					if( p2.get(i).getX() == g.getX() && p2.get(i).getY() == g.getY() )
						return true;
			
			return false;
		}
		
		
		private  Re returnRe(int g) {
			
			for(int i=0;i<8;i++){
				for(int y=0; y<8; y++){
					if(m.get(y,i)!=null && ( m.get(y,i).isRe(g)) )
						return (Re) m.get(y,i);
						
				}
			}
			return null;
		    
		}
		
		private  Punto getRe(int g) {
			
			for(int i=0;i<8;i++){
				for(int y=0; y<8; y++){
					
					if(m.get(y,i)!=null && ( m.get(y,i).isRe(g)) )
						return new Punto(y,i);
						
				}
			}
			return null;
		    
		}
		
		public void initialize(){
			
			x1=0;y1=0;x2=0;y2=0;t=0;giocatore=0;
			m.initialize();
			v.onConfigurationChange();
			
		}
	
}