package viewController;

import java.awt.Color;
import java.util.Random;
import javax.swing.JPanel;

public class Case extends JPanel {
	private boolean etatCellule;

	public boolean getEtatCellule() {
		return etatCellule;
	}

	public void setEtatCellule(boolean etatCellule) {
		this.etatCellule = etatCellule;
	}

	public Case() {
		super();
		this.etatCellule = false;
	}
	
	public Case(boolean etatCellule) {
		this.etatCellule = etatCellule;
	}
	
	public void changeEtat(){
		Random rand=new Random();
		this.etatCellule=rand.nextBoolean();
		if(this.etatCellule)
			this.setBackground(Color.BLACK);
		else
			this.setBackground(Color.WHITE);

	}

}