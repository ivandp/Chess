package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ScacchiController;
import model.Punto;
import rules.Torre;

public class CambioPedone extends JDialog {

	public CambioPedone(JFrame owner, ScacchiController c, Punto g, int giocatore) {
		super(owner, "scegli un pezzo", true);


		JButton torre = new JButton("TORRE");
		torre.addActionListener(event -> {
			c.scambioPedoneEstremi( 0, g, giocatore );
			setVisible(false); 
		});
		JButton alfiere = new JButton("ALFIERE");
		alfiere.addActionListener(event -> {
			c.scambioPedoneEstremi( 1, g, giocatore );
			setVisible(false);
		});
		JButton cavallo = new JButton("CAVALLO");
		cavallo.addActionListener(event -> {
			c.scambioPedoneEstremi( 2, g, giocatore );
			setVisible(false);
		});
		JButton regina = new JButton("REGINA");
		regina.addActionListener(event -> {
			c.scambioPedoneEstremi( 3, g, giocatore );
			setVisible(false);
		});

		JPanel panel = new JPanel();
		panel.add(torre);
		panel.add(alfiere);
		panel.add(cavallo);
		panel.add(regina);
		add(panel, BorderLayout.SOUTH);

		pack();
	}

	private static final long serialVersionUID = 1L;
}
