

import java.awt.EventQueue;
import javax.swing.JFrame;
import view.ScacchiView;

public class Main {

	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new ScacchiView();
				
			}
		});
	}
}