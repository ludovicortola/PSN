package psn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graphe {

	private int[][] matriceAdj;
	private String[][] matriceOrientation;
	private boolean eulerien;
	private boolean connexe;
	private Sommet[] listeSommets;
	private ArrayList<Arete> listeAretes;

	public Graphe(String fichierFacteurs, String fichierOrientation) {

		try {
			this.matriceAdj = setMatAdjacence(fichierFacteurs);
			affiche(matriceAdj);
			this.matriceOrientation = setMatOrientation(fichierOrientation);
			affiche2(matriceOrientation);
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}

		setListeSommets();
		setListeAretes();
		setEulerien();
		setConnexe();
		Chemin C = fleury(this.matriceAdj, listeSommets[0]);
		C.affiche();
	}

	private void affiche2(String[][] matrice) {
		for (int i=0; i<matrice.length; i++) {
			for (int j=0; j<matrice.length; j++) {
				System.out.print(matrice[i][j]+"   ");
			}
			System.out.println();
		}
	}

	private String[][] setMatOrientation(String fileName) throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String s=br.readLine();
		String[] attributs = new String[6];
		String[][] matrice = new String[6][6];

		int i = 0;
		while (s!=null) {
			attributs=s.split(" ");
			for (int j=0; j<attributs.length; j++) {
				matrice[i][j] = attributs[j];
			}
			i++;
			s=br.readLine();
		}

		br.close();
		fr.close();

		return matrice;
	}

	private static void affiche(int[][] matrice) {
		for (int i=0; i<matrice.length; i++) {
			for (int j=0; j<matrice.length; j++) {
				System.out.print(matrice[i][j]+"   ");
			}
			System.out.println();
		}
	}

	// Niveau 0
	public static int[][] setMatAdjacence(String fileName) throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String s=br.readLine();
		String[] attributs;

		ArrayList<ArrayList<Integer>> fichier = new ArrayList<ArrayList<Integer>>();

		while (s!=null) {
			attributs=s.split(" ");
			int nbIteration = 0;
			if (nbIteration == 0 && attributs.length == 1) {
				int nbSommet = Integer.parseInt(attributs[0]);
				ArrayList<Integer> sommet = new ArrayList<Integer>();
				sommet.add(nbSommet);
				fichier.add(sommet);
			} else {
				ArrayList<Integer> ligne = new ArrayList<Integer>();
				for (int i=0; i<attributs.length; i++) {
					ligne.add(Integer.parseInt(attributs[i]));
				}
				fichier.add(ligne);
			}
			nbIteration++;
			s=br.readLine();
		}
		br.close();
		fr.close();

		int[][] matriceAdjacence = formalisationMatrice(fichier);

		return matriceAdjacence;
	}

	private static int[][] formalisationMatrice(ArrayList<ArrayList<Integer>> fichier) {
		int taille = fichier.get(0).get(0);
		fichier.remove(0);
		int[][] M = new int[taille][taille];

		for (int k=0; k<fichier.size(); k++) {
			int i = fichier.get(k).get(0) - 1;
			int j = fichier.get(k).get(1) - 1;
			M[i][j] = M[j][i] = 1;
		}

		return M;
	}

	public int[][] getMatAdj(){
		return this.matriceAdj;
	}

	public boolean isEulerien() {
		return eulerien;
	}

	public void setEulerien() {
		boolean eulerien = false;

		this.eulerien = eulerien;
	}

	public boolean isConnexe() {
		return connexe;
	}

	public void setConnexe() {
		boolean connexe = false;
		this.connexe = connexe;
	}

	// Niveau 1
	private Chemin fleury(int[][] matrice, Sommet sommetDepart) {
		Chemin C = new Chemin();
		C.ajouteSommet(sommetDepart);
		int[][] G = new int[matrice.length][matrice.length];
		for (int i=0; i<matrice.length; i++) {
			for (int j=0; j<matrice.length; j++) {
				G[i][j] = matrice[i][j];
			}
		}
		boolean matVide = isMatVide(G);
		Sommet x = sommetDepart;
		while (!matVide) {
			ArrayList<Integer> succ = x.getNumSuccesseurs();
			Sommet y = listeSommets[succ.get(0)-1];
			succ.remove(0);
			Arete xy = findArete(x, y);
			while (xy.isPont(G) && succ.size() != 0) {
				y = listeSommets[succ.get(0)-1];
				xy = findArete(x, y);
			}
			C.ajouteSommet(y);
			xy.removeArete(xy, G);
			x = y;
			matVide = isMatVide(G);
		}
		return C;
	}

	private Arete findArete(Sommet x, Sommet y) {
		Arete a = null;
		int i = 0;
		while (a == null && i<listeAretes.size()) {
			Arete arete = listeAretes.get(i);
			if (arete.getSommetDepart() == x) {
				if (arete.getSommetArrivee() == y) {
					a = listeAretes.get(i);
				}
			}
			i++;
		}
		i = 0;
		while (a == null && i<listeAretes.size()) {
			if (listeAretes.get(i).getSommetArrivee() == x) {
				if (listeAretes.get(i).getSommetDepart() == y) {
					a = listeAretes.get(i);
				}
			}
			i++;
		}

		return a;
	}

	private static boolean isMatVide(int[][] matAdj) {
		for (int i=0; i<matAdj.length; i++) {
			for (int j=0; j<matAdj.length; j++) {
				if (matAdj[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	public void setListeSommets() {
		Sommet[] listeSommets = new Sommet[matriceAdj.length]; // Taille à modifier
		for (int i=0; i<matriceAdj.length; i++) {
			int x = 0;
			int y = 0;
			if (i == 0) {
				x=50;
				y=95;
			}
			if (i == 1) {
				x=20;
				y=66;
			}
			if (i == 2) {
				x=80;
				y=66;
			}
			if (i == 3) {
				x=0;
				y=25;
			}
			if (i == 4) {
				x=33;
				y=25;
			}
			if (i == 5) {
				x=95;
				y=25;
			}
			listeSommets[i] = new Sommet(i+1, matriceAdj, x, y);

		}
		this.listeSommets = listeSommets;
	}

	public Sommet[] getListeSommets() {
		return listeSommets;
	}

	public void setListeAretes() {
		ArrayList<Arete> listeAretes = new ArrayList<Arete>(); // Taille à modifier
		for (int i=0; i<matriceAdj.length; i++){
			for (int j=i; j<matriceAdj.length; j++){
				if (matriceAdj[i][j] == 1) {
					Arete a = new Arete(this.listeSommets[i], this.listeSommets[j], matriceAdj);
					listeAretes.add(a);
				}
			}
		}
		this.listeAretes = listeAretes;
	}

	public ArrayList<Arete> getListeAretes() {
		return listeAretes;
	}

	public String[][] getMatriceOrientation() {
		return matriceOrientation;
	}

	public void setMatriceOrientation(String[][] matriceOrientation) {
		this.matriceOrientation = matriceOrientation;
	}


}
