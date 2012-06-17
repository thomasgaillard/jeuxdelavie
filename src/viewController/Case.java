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
	private int x;
	private int y;
	private int largeur;
	private int hauteur;

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public boolean isEtatCellule() {
		return etatCellule;
	}

	public void setEtatCellule(boolean etatCellule) {
		this.etatCellule = etatCellule;
	}

	public Case() {
		super();
		this.etatCellule = false;
		this.setXCase(0);
		this.setYCase(0);
		this.setLargeur(0);
		this.setHauteur(0);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public Case(boolean etatCellule) {
		this.setXCase(0);
		this.setYCase(0);
		this.etatCellule = etatCellule;
	}
	
	public Case(int x, int y) {
		super();
		this.etatCellule = false;
		this.setXCase(x);
		this.setYCase(y);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.black));	}

	public void changeEtat(boolean etatCellule){
		if(etatCellule)
			this.setBackground(Color.BLACK);
		else
			this.setBackground(Color.WHITE);
	}

	public void setXCase(int x) {
		this.x = x;
	}

	public int getXCase() {
		return x;
	}

	public void setYCase(int y) {
		this.y = y;
	}

	public int getYCase() {
		return y;
	}
}