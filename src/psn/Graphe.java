package psn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import factory.AreteFactory;
import factory.SommetFactory;
import utils.AreteUtils;
import utils.MathUtils;
import utils.SommetUtils;

public class Graphe {

	private int[][] matriceAdj;
	private String[][] matriceOrientation;
	private boolean eulerien;
	private boolean connexe;

	public Graphe(String fichierFacteurs, String fichierOrientation) {

		try {
			this.matriceAdj = genererMatAdjacence(fichierFacteurs);
			affiche(matriceAdj);
			this.matriceOrientation = setMatOrientation(fichierOrientation);
			affiche2(matriceOrientation);
		} catch (Throwable e) {
			throw new IllegalStateException(e);
		}

		setEulerien();
		setConnexe();
		Chemin c = fleury(this.matriceAdj);
		c.affiche();
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
	public static int[][] genererMatAdjacence(String fileName) throws FileNotFoundException, IOException {
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
	private Chemin fleury(int[][] matrice) {
		Chemin c = new Chemin();
		Map<Integer, Sommet> sommets = SommetFactory.getInstances(matriceAdj);
		List<Arete> listeAretes = AreteFactory.getInstances(sommets, matrice);
		Sommet sommetDepart = sommets.get(1);
		c.ajouteSommet(sommetDepart);
		
		int[][] m = new int[matrice.length][matrice.length];
		for (int i=0; i<matrice.length; i++) {
			for (int j=0; j<matrice.length; j++) {
				m[i][j] = matrice[i][j];
			}
		}
		
		boolean matVide = isMatVide(m);
		
		Sommet x = sommetDepart;
		while (!matVide) {
			Sommet y;
			Arete xy;
			List<Sommet> succ = x.getSuccesseurs();
			do {
				// Prendre le successeur suivant
				y = succ.get(0);
				xy = AreteUtils.findArete(listeAretes, x, y);
				xy.setPont(AreteUtils.isPont(sommets, xy, m));
				succ.remove(0);
			} while (xy.isPont() && !succ.isEmpty());
			
			c.ajouteSommet(y);
			x.affiche();
			y.affiche();
			AreteUtils.removeArete(sommets, xy, m);
			x = y;
			matVide = isMatVide(m);
		}
		return c;
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

	public String[][] getMatriceOrientation() {
		return matriceOrientation;
	}

	public void setMatriceOrientation(String[][] matriceOrientation) {
		this.matriceOrientation = matriceOrientation;
	}


}
