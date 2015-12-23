package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import controller.ScacchiController;
import rules.*;
import model.ScacchiModel;
import model.punto;


public class ScacchiView extends JFrame {
	
	private final ScacchiModel model = new ScacchiModel();
	private final ScacchiController controller;
	
	Ascoltatore listener = new Ascoltatore();
	ScacchiModel s;
	
	JFrame win = new JFrame();
	
	int coloreSfondo=0;
	JButton[][] b = new JButton[8][8];
	Icon imgPedina;
	private ArrayList<punto> p;
	JFrame frame;
	
	
	public ScacchiView(){
		
		controller = new ScacchiController(model,this);
		
		//settiamo a win una griglia 8x8
		win.setLayout(new GridLayout(8, 8));
		Container c = win.getContentPane();	
		 
		//creo i bottoni per ogni cella della scacchiera 
		for (int x = 0; x < 8; x++){
			
			for (int y = 0; y < 8; y++)
				
				c.add(b[x][y] = mkButton(x, y, model.get(x,y)));
			
		}
		colourButton();
		win.setSize(500,500);
		win.setDefaultCloseOperation
		(JFrame.EXIT_ON_CLOSE);
		win.setVisible(true);
	}
	
	private  JButton mkButton(int x, int y, Pedina value) {
		//creo un bottone
		
		
		JButton button = new JButton();
		if(value!= null){
			//imposto immagine corrispondente a value
			imgPedina = new ImageIcon(value.getImg());
			button = new JButton(imgPedina);
		}
		
		//setto l'evento click del bottone che richiamerà il controller(funzione add)
		button.addActionListener(event -> {
			
				controller.add(x, y);
				
		});
		
		//reset grafico dell'opacità e del bordo
		button.setOpaque(true);
		button.setBorderPainted(false);
		
		return button;
	}
	
	//funzione che colora lo sfondo dei bottoni della scacchiera
	public void colourButton(){
		for (int a = 0; a < 8; a++)
			for (int c = 0; c < 8; c++)
				if(Math.abs(a-c)%2==0)
					b[a][c].setBackground(new Color(255, 206, 164));
				else
					b[a][c].setBackground(new Color(210, 139, 83));
			
	}

	//colora di rosso la previsione delle mosse possibili
	public void colorizeRed(ArrayList<punto> p){
		
		for(int i=0;i<p.size();i++){
			
			punto p1 = p.get(i);
		
			b[p1.getX()][p1.getY()].setBackground(Color.RED);
			
		}
	}
	
	public void colorizeG(ArrayList<punto> p){
		for(int i=0;i<p.size();i++){
			punto p1 = p.get(i);
		
			b[p1.getX()][p1.getY()].setBackground(Color.GREEN);
		}
	}
		
	
	//funzione che aggiorna la scacchiera dopo ogni mossa
	public void onConfigurationChange() {
		colourButton();
		
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++){
				if(model.get(x,y)!=null){
					imgPedina = new ImageIcon(model.get(x,y).getImg());
					b[x][y].setIcon(imgPedina);
				} else
					b[x][y].setIcon(null);
				
			}
	}

	public void setBackground(int x, int y) {
		b[x][y].setBackground(Color.RED);
		
	}
}
