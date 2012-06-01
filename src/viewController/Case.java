package viewController;

import java.awt.Color;
import javax.swing.JPanel;

public class Case extends JPanel {
	private boolean etatCellule;

	public Case() {
		super();
		this.etatCellule = false;
	}
	
	public Case(boolean etatCellule) {
		this.etatCellule = etatCellule;
	}
	
	public void changeEtat(boolean etatCellule){
		if(etatCellule)
			this.setBackground(Color.BLACK);
		else
			this.setBackground(Color.WHITE);
	}
}