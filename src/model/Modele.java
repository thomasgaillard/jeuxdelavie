package model;

import java.awt.Color;
import java.util.Random;


public class Modele extends Observable {

	private boolean enMarche;
	private int t;
	private boolean[][] tabBool;
	private int tailleTab;

	public Modele(int n){
		this.tailleTab=n;
		this.tabBool=new boolean[n][n];
		this.enMarche=false;
		this.t=5000;
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

	public int getTailleTab() {
		return tailleTab;
	}

	public void setTailleTab(int tailleTab) {
		this.tailleTab = tailleTab;
	}

	public void run() {
		 while(true){
			 if(enMarche){
				 init();
				 notifyObservers();
			 }
			 Thread thread=new Thread();
			 try {
				thread.wait(t);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
	
	public void notifyObservers() {
		
	}
	
	public void init() {
		for (int i=0;i<this.tailleTab;i++){
			for (int j=0;i<this.tailleTab;j++){
				Random rand=new Random();
				this.tabBool[i][j]=rand.nextBoolean();
			}
		}
	}
	
	public void pause(){
		this.enMarche=false;
	}
	
	public void activ(){
		this.enMarche=true;
	}
}
