package viewController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class FenetrePrincipale extends JFrame {
 private JPanel panelPrincipal, panelCentre, panelDroite;
 private int n;
 
 public FenetrePrincipale(){
	 Toolkit k = Toolkit.getDefaultToolkit();
	    Dimension tailleEcran = k.getScreenSize();
	    int hauteurEcran = tailleEcran.height;
	    setTitle("Le jeux de la vie");
	    setSize(hauteurEcran/2+200, hauteurEcran/2);
	 n = 25;
	 panelPrincipal = new JPanel(new BorderLayout());
	 setContentPane(panelPrincipal);
	 panelDroite = new JPanel();

	 panelDroite.setLayout(new GridLayout(3, 1));
	 panelDroite.add(new JButton("Initialiser"));
	 panelDroite.add(new JButton("Pause"));
	 panelDroite.add(new JButton("Activité"));
	 
	 panelCentre = new JPanel();
	 
	 panelPrincipal.add(panelDroite, BorderLayout.EAST);
	 panelPrincipal.add(panelCentre, BorderLayout.CENTER);
	 panelCentre.setLayout(new GridLayout(n, n));
	 
	 this.initialize();
	 this.setResizable(false);
	 this.setVisible(true);
 }
 
 public void initialize(){
	 for(int i =0; i<n*n; i++){
		 Case cellule = new Case();
		 cellule.changeEtat();
		 panelCentre.add(cellule);
	 }
 }
 public static void main(String[] args) {
	 FenetrePrincipale test = new FenetrePrincipale();

	}
}
