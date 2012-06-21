package viewController;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Forme;
import model.Modele;

public class FenetrePrincipale extends JFrame {

	private JPanel panelPrincipal, panelCentre, panelDroite,panelBas;
	private int n;
	public static int hauteurEcran;
	public static int largeurEcran;
	private Modele modele;
	private boolean mousePressed=false;
	private double hauteurCentrale;
	private double largeurCentrale;
	private int forme;

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
		panelDroite = new JPanel(new GridLayout(6,1));
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

		JComboBox listeForme = new JComboBox(); 
		listeForme.addItem("Carré 1x1"); 
		listeForme.addItem("Barre horizontale");  
		listeForme.addItem("Carré 2x2"); 
		listeForme.addItem("Vaisseau"); 
		listeForme.addItem("Alien"); 
		panelDroite.add(listeForme);

		listeForme.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				switch (((JComboBox)arg0.getSource()).getSelectedIndex()) {
				case 1:
					forme=1;
					break;
				case 2:
					forme=2;
					break;
				case 3:
					forme=3;
					break;
				case 4:
					forme=4;
					break;
				default:
					forme=0;
					break;
				}
			}
		});

		//cellules
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Case cellule = new Case(i,j);
				// Ajouter un tableau de case
				cellule.setHauteur((int)hauteurCentrale);
				cellule.setLargeur((int)largeurCentrale);
				panelCentre.add(cellule);

				cellule.addMouseListener(new MouseListener() {
                    public void mousePressed(MouseEvent e) {
                        Case maCellule = (Case)e.getSource(); //récupération de la case sur lequel l'utilisateur a cliqué
                        if(forme == 0){
                             mousePressed = true;
                             modele.setTabBool_x_y(maCellule.getXCase(), maCellule.getYCase(),true); //modification de l'état de la cellule dans notre modele
                        }
                        else
                            modele.setForme(maCellule.getXCase(), maCellule.getYCase(), forme); //chargement de la figure
                    }
                     
                     public void mouseEntered(MouseEvent e) {
                         Case maCellule = (Case)e.getSource(); //récupération de la case sur lequel la souris de l'utilisateur est pointée
                         if(mousePressed){
                             if(forme == 0)
                                     modele.setTabBool_x_y(maCellule.getXCase(), maCellule.getYCase(),true); //modification de l'état de la cellule dans notre modele
                         }
                         else                         
                           affichageFigure(maCellule.getXCase(), maCellule.getYCase(), forme);
                         
                     }
                     
                     public void mouseExited(MouseEvent e) {
                         if(mousePressed == false){
                                 modele.notifyObserver();
                         }
                     }
                     
                     public void mouseReleased(MouseEvent e) {
                        mousePressed = false;
                     }

					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
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
	   public void affichageFigure(int posX, int posY, int index){
	        int x1=Forme.FORMES[index][0][0];
	        int x2=Forme.FORMES[index][0][1];
	        for(int i=1;i<=x2;i++){
	            for(int j=0;j<x1;j++){
	                if(Forme.FORMES[index][i][j] == 1){
	                    if((posX+i+1)<this.n && (posY+j)<this.n){
	                        Case c = ((Case) panelCentre.getComponent((posX+i-1)*n+(posY+j))); //récupération de notre case (classe d'affichage de la vue)
	                        if(c.isEtatCellule() != true) //Si l'état de la cellule de notre modele est différente de celle de la vue, on modifie l'affichage de la vue
	                            c.changeEtat(true); //Changement de l'affichage de notre vue (changement de l'état)
	                    }
	                }  
	            }
	        }
	   }

	public static void main(String[] args) {
		FenetrePrincipale ui = new FenetrePrincipale();
	}



}