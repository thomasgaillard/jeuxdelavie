package viewController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Modele;

public class FenetrePrincipale extends JFrame {
	
	private JPanel panelPrincipal, panelCentre, panelDroite;
	private int n;
	public static int hauteurEcran;
	public static int largeurEcran;
	private Modele modele;

	public FenetrePrincipale() {
		//init
		n = 50;
		modele = new Modele(n);
		//implémentation de l'Observer en classe anonyme
		modele.addObserver(new Observer(){
			//fonction de notification
			public void Notify(){
				int inc = 0;
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						((Case)(panelCentre.getComponent(inc))).changeEtat(modele.getTabBool()[i][j]);
						inc ++;
					}
				}
			}
		});
		
		//fenetre
		Toolkit k = Toolkit.getDefaultToolkit();
		Dimension tailleEcran = k.getScreenSize();
		int hauteur = tailleEcran.height;
		hauteurEcran=hauteur / 2 ;
		largeurEcran=hauteurEcran/2+ 200;
		this.setTitle("Le jeux de la vie");
		setSize(hauteurEcran, largeurEcran);
		setVisible(true);

		//panels
		panelPrincipal = new JPanel(new BorderLayout());
		setContentPane(panelPrincipal);
		panelDroite = new JPanel(new GridLayout(4, 1));
		panelCentre = new JPanel(new GridLayout(n, n));
		panelPrincipal.add(panelDroite, BorderLayout.EAST);
		panelPrincipal.add(panelCentre, BorderLayout.CENTER);
		
		//buttons
		JButton init = new JButton("Initialiser");
		panelDroite.add(init);
		JButton pause = new JButton("Pause");
		panelDroite.add(pause);
		JButton activ = new JButton("Activité");
		panelDroite.add(activ);
		JButton jeu = new JButton("Génération");
		panelDroite.add(jeu);
		
		//cellules
		for (int i = 0; i < n*n; i++) {
			Case cellule = new Case();
			panelCentre.add(cellule);
		}
		
		//buttons actionListener
		init.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.init();
			}
		});
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modele.pause();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		activ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.activ();
			}
		});
		jeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.update();
			}
		});
		this.validate();
		this.repaint();
	}

	public static void main(String[] args) {
		FenetrePrincipale ui = new FenetrePrincipale();
	}
}