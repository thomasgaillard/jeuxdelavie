package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import viewController.FenetrePrincipale;
import viewController.Observer;


public class Modele implements Observable,Runnable {

	private boolean enMarche;
	private int t;
	private boolean[][] tabBool;
	private int tailleTab;
	private Thread thread;
	/**
	 * nombre de voisines pour chaque cellules
	 */
	private	int	voisines[][];
	private ArrayList<Observer> tabObservers;

	public Modele(int n){
		this.tabObservers=new ArrayList<Observer>();
		this.tailleTab=n;
		this.tabBool=new boolean[n][n];
		this.voisines=new int[n][n];
		this.enMarche=false;
		this.t=1000;
		this.thread=new Thread(this);
		this.thread.start();
	}
	
	public boolean isEnMarche() {
		return enMarche;
	}

	public void setEnMarche(boolean enMarche) {
		this.enMarche = enMarche;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public boolean[][] getTabBool() {
		return tabBool;
	}

	public void setTabBool(boolean[][] tabBool) {
		this.tabBool = tabBool;
	}
	
	public void setTabBool_x_y(int x,int y,boolean bool) {
		this.tabBool[x][y] = bool;
	}

	public int getTailleTab() {
		return tailleTab;
	}

	public void setTailleTab(int tailleTab) {
		this.tailleTab = tailleTab;
	}

	public synchronized void run() {
	
		 while(true){	
			 synchronized (thread) {
				 if(enMarche){
					 this.init();
					 //this.update();
					 this.notifyObserver();
				 }
				 else{
					 try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
			 }
		 }
	}
	
	public void init() {
		for (int i=0;i<this.tailleTab;i++){
			for (int j=0;j<this.tailleTab;j++){
				Random rand=new Random();
				this.tabBool[i][j]=rand.nextBoolean();
			}
		}
		this.notifyObserver();
	}
	
	public void pause() throws InterruptedException{
		thread.wait();
		this.enMarche=false;
	}
	
	public void activ(){
		thread.notify();
		this.enMarche=true;
	}

	public void addObserver(Observer obs) {
		tabObservers.add(obs);	
	}

	public void removeObserver(Observer obs) {
		tabObservers.remove(obs);
	}
	
	public int nbVoisinesVivantes(int x,int y,boolean enVie)
	{
		int	nb=0;
		for(int yc=y-1;yc<=y+1;yc++)
		{
			for(int xc=x-1;xc<=x+1;xc++)
			{
				if ((xc!=x || yc!=y) && xc>=0 && yc>=0 && xc<this.tailleTab && yc<this.tailleTab)
				{
					boolean etatCellule=this.getTabBool()[xc][yc]; //this.getCellule(xc,yc);
					if (etatCellule)
						nb++;
					
					if (enVie && nb>=4)
						return nb;
				}
			}
		}
		return nb;
	}

	public synchronized void update()
	{
		//Lois pour une cellule :
		//	-Une cellule vivante meure si elle a moins de 2 cellules ou plus de 4 cellules voisines vivantes
		// 	-Une cellule morte reviens à la vie si elle a 3 cellules voisines vivantes
		
		//calcul du nombre de voisines
		for(int x=0;x<this.tailleTab;x++)
		{
			for(int y=0;y<this.tailleTab;y++)
			{
				voisines[x][y]=nbVoisinesVivantes(x,y,this.getTabBool()[x][y]);
			}
		}
		
		//mise a jour des cellules
		for(int x=0;x<this.tailleTab;x++)
		{
			for(int y=0;y<this.tailleTab;y++)
			{
				//int nb=nbVoisinesVivantes(x,y);
				boolean etatCellule=this.getTabBool()[x][y];
				int nb=voisines[x][y];
				if (etatCellule)
				{
					if (nb<2 || nb>=4) //1ere loi
					{
						this.setTabBool_x_y(x,y,false);	//meure 
					
					}
				}else
				{
					if (nb==3)	//2eme loi
					{
						this.setTabBool_x_y(x,y,true);	//meure 
						
					}
				}
			}
		}
		notifyObserver();
	}
	
	@Override
	public void notifyObserver() {
		for(Observer tab : tabObservers){
			tab.Notify();
		}
		
	}
}
