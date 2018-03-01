package psn;

import java.util.ArrayList;

public class Chemin {
	
	private ArrayList<Sommet> listeSommet;
	private int nbSommet;
	private Sommet sommetDepart;
	private Sommet sommetArrivee;
	
	Chemin() {
		this.listeSommet = new ArrayList<Sommet>();
	}
	
	public void affiche() {
		System.out.print("Chemin : ");
		for (int i=0; i<this.listeSommet.size(); i++) {
			System.out.print(listeSommet.get(i).getNumero()+" ");
		}
	}
	
	public void ajouteSommet(Sommet x) {
		this.listeSommet.add(x);
		this.nbSommet ++;
		if (this.listeSommet.size() == 0) {
			this.sommetDepart = x;
		} else {
			this.sommetArrivee = x;
		}
	}
	
	public ArrayList<Sommet> getListeSommet() {
		return listeSommet;
	}
	
	public int getNbSommet() {
		return this.nbSommet;
	}

	public Sommet getSommetDepart() {
		return sommetDepart;
	}

	public Sommet getSommetArrivee() {
		return sommetArrivee;
	}
	
}
