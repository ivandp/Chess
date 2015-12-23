package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ScacchiController;



public class SolvedDialog extends JDialog {

	public SolvedDialog(JFrame owner, ScacchiController c ) {
		super(owner, "Game Solved", true);

		add(new JLabel("<html><h1>VITTORIA!</h1></html>"));

		JButton ok = new JButton("GIOCA ANCORA");
		ok.addActionListener(event -> {
			c.initialize();
			setVisible(false);
		});

		JPanel panel = new JPanel();
		panel.add(ok);
		add(panel, BorderLayout.SOUTH);

		pack();
	}

	private static final long serialVersionUID = 1L;
}
