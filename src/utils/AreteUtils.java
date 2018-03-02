package utils;

import java.util.List;
import java.util.Map;

import psn.Arete;
import psn.Sommet;

public class AreteUtils {

	public static Arete findArete(List<Arete> aretes, Sommet x, Sommet y) {
		Arete a = null;
		int i = 0;
		while (a == null && i<aretes.size()) {
			Arete arete = aretes.get(i);
			if (arete.getSommetDepart() == x) {
				if (arete.getSommetArrivee() == y) {
					a = aretes.get(i);
				}
			}
			i++;
		}
		i = 0;
		while (a == null && i<aretes.size()) {
			if (aretes.get(i).getSommetArrivee() == x) {
				if (aretes.get(i).getSommetDepart() == y) {
					a = aretes.get(i);
				}
			}
			i++;
		}

		return a;
	}
	
	public static boolean isPont(Map<Integer, Sommet> sommets, Arete arete, int[][] matrice) {
		boolean pont;
		int[][] m = ArrayUtils.cloneSquareMatrix(matrice);
		removeArete(sommets, arete, m);
		
		int[][] n = new int[matrice.length][matrice.length];
		for (int k=0; k<matrice.length; k++) {
			int[][] P = MathUtils.puissanceMatrice(m, k);
			n = MathUtils.sommematrice(n, P);
		}
		if (n[arete.getSommetDepart().getId()-1][arete.getSommetArrivee().getId()-1] == 0) {
			pont = true;
		} else {
			pont = false;
		}
		return pont;
	}
	
	public static void removeArete(Map<Integer, Sommet> sommets, Arete arete, int[][] matrice) {
		matrice[arete.getSommetDepart().getId()-1][arete.getSommetArrivee().getId()-1] = 0;
		matrice[arete.getSommetArrivee().getId()-1][arete.getSommetDepart().getId()-1] = 0;
		SommetUtils.computeSuccessors(arete.getSommetDepart(), sommets, matrice);
		SommetUtils.computeSuccessors(arete.getSommetArrivee(), sommets, matrice);
	}
}
