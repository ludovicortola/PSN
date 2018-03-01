package psn;

import java.util.ArrayList;

public class Sommet {
	
	private int x;
	private int y;
	private int numero;
	private int degre;
	private ArrayList<Integer> numSuccesseurs;
	
	Sommet(int num, int[][] matriceAdj, int x, int y) {
		this.x = x;
		this.y = y;
		this.numero = num;
		this.degre = setDegre(matriceAdj);
		setSuccessors(matriceAdj);
		//affiche();
	}
	
	public void setSuccessors(int[][] matriceAdj) {
		this.numSuccesseurs = new ArrayList<Integer>(); 
		for (int i=0; i<matriceAdj.length; i++) {
			if (matriceAdj[this.numero-1][i] == 1) {
				this.numSuccesseurs.add(i+1);
			}
		}
	}

	public void affiche() {
		System.out.println("Sommet numéro "+this.numero+" de degré "+this.degre);
	}

	private int setDegre(int[][] matriceAdj) {
		int degre = 0;
		for (int i=0; i<matriceAdj.length; i++) {
			if (matriceAdj[this.numero-1][i] == 1) {
				degre++;
			}
		}
		return degre;
	}

	public int getNumero() {
		return numero;
	}

	public int getDegre() {
		return degre;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public ArrayList<Integer> getNumSuccesseurs() {
		return this.numSuccesseurs;
	}
	
}
