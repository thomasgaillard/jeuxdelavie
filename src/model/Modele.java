package model;

import java.awt.Color;
import java.util.Random;


public class Modele extends Observable {

	private boolean enMarche;
	int t;
	/**
	 * @param args
	 */
	public void run() {
		 while(true){
			 if(enMarche){
				 calcul();
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
	
	private void notifyObservers() {
		
	}
	
	private boolean calcul() {
		Random rand=new Random();
		return rand.nextBoolean();
		
	}
}
