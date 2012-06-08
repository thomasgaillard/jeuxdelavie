package viewController;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Case extends JPanel {
	private boolean etatCellule;

	public boolean isEtatCellule() {
		return etatCellule;
	}

	public void setEtatCellule(boolean etatCellule) {
		this.etatCellule = etatCellule;
	}

	public Case() {
		super();
		this.etatCellule = false;
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
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