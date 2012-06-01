package viewController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Modele;

public class FenetrePrincipale extends JFrame {
	private JPanel panelPrincipal, panelCentre, panelDroite;
	private int n;
	private Modele modele;

	public FenetrePrincipale() {
		//init
		n = 25;
		modele = new Modele(n);
		
		//fenetre
		Toolkit k = Toolkit.getDefaultToolkit();
		Dimension tailleEcran = k.getScreenSize();
		int hauteurEcran = tailleEcran.height;
		setTitle("Le jeux de la vie");
		setSize(hauteurEcran / 2 + 200, hauteurEcran / 2);

		//panels
		panelPrincipal = new JPanel(new BorderLayout());
		setContentPane(panelPrincipal);
		panelDroite = new JPanel();
		panelDroite.setLayout(new GridLayout(3, 1));
		
		//buttons
		JButton init = new JButton("Initialiser");
		panelDroite.add(init);
		JButton pause = new JButton("Pause");
		panelDroite.add(pause);
		JButton activ = new JButton("Activité");
		panelDroite.add(activ);

		panelCentre = new JPanel();

		panelPrincipal.add(panelDroite, BorderLayout.EAST);
		panelPrincipal.add(panelCentre, BorderLayout.CENTER);
		panelCentre.setLayout(new GridLayout(n, n));

		this.setResizable(false);
		this.setVisible(true);
		modele.run();
		
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
				modele.pause();
			}
		});
		activ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.activ();
			}
		});

		//
		public void Notify(){
			int inc = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					Case(panelCentre.getComponent(inc)).changeEtat(modele.getTabBool()[i][j]);
					inc ++;
				}
			}
		}
		
	}

	public static void main(String[] args) {
		FenetrePrincipale test = new FenetrePrincipale();
	}
}