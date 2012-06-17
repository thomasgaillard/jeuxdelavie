package viewController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	
	private JPanel panelPrincipal, panelCentre, panelDroite,panelBas;
	private int n;
	public static int hauteurEcran;
	public static int largeurEcran;
	private Modele modele;
	private boolean mousePressed=false;
	private double zoom=1;
	private double hauteurCentrale;
	private double largeurCentrale;

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
		panelDroite = new JPanel(new FlowLayout());
		panelCentre = new JPanel(new GridLayout(n, n));
		panelBas = new JPanel(new FlowLayout());
		panelPrincipal.add(panelDroite, BorderLayout.EAST);
		panelPrincipal.add(panelCentre, BorderLayout.CENTER);
		panelPrincipal.add(panelBas,BorderLayout.SOUTH);
		
		//buttons
		JButton init = new JButton("Initialiser");
		panelDroite.add(init);
		JButton pause = new JButton("Pause");
		panelDroite.add(pause);
		JButton activ = new JButton("Activité");
		panelDroite.add(activ);
		JButton vitessePlus = new JButton("Vitesse +");
		panelDroite.add(vitessePlus);
		JButton vitesseMoins = new JButton("Vitesse -");
		panelDroite.add(vitesseMoins);
		
		JButton zoomPlus = new JButton("Zoom +");
		zoomPlus.setSize(10,10);
		panelBas.add(zoomPlus);
		JButton zoomMoins = new JButton("Zoom -");
		panelBas.add(zoomMoins);
		
		//cellules
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Case cellule = new Case(i,j);
				cellule.setHauteur((int)hauteurCentrale);
				cellule.setLargeur((int)largeurCentrale);
				panelCentre.add(cellule);

				cellule.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						mousePressed=false;
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						mousePressed=true;
						int x=((Case)e.getSource()).getXCase();
						int y=((Case)e.getSource()).getYCase();
						modele.setTabBool_x_y(x, y, !((Case)e.getSource()).isEtatCellule());
						((Case)e.getSource()).changeEtat(!((Case)e.getSource()).isEtatCellule());
					}
					
					@Override
					public void mouseExited(MouseEvent e) {}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						if(mousePressed){
							int x=((Case)e.getSource()).getXCase();
							int y=((Case)e.getSource()).getYCase();
							modele.setTabBool_x_y(x, y, !((Case)e.getSource()).isEtatCellule());
							((Case)e.getSource()).changeEtat(!((Case)e.getSource()).isEtatCellule());
						}
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {}
				});
			}
		}
		largeurCentrale=panelCentre.getPreferredSize().getWidth();
		hauteurCentrale=panelCentre.getPreferredSize().getHeight();

		//buttons actionListener
		init.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.init();
			}
		});
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.pause();
			}
		});
		activ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.activ();
			}
		});
		vitessePlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.vitessePlus();
			}
		});
		vitesseMoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.vitesseMoins();
			}
		});
		this.validate();
		this.repaint();
	}

	public static void main(String[] args) {
		FenetrePrincipale ui = new FenetrePrincipale();
	}
	


}