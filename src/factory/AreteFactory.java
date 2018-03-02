package factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import psn.Arete;
import psn.Sommet;

public class AreteFactory {
	public static List<Arete> getInstances(Map<Integer, Sommet> sommets, int[][] matriceAdj) {
		List<Arete> listeAretes = new ArrayList<Arete>(); // Taille à modifier
		for (int i=0; i<matriceAdj.length; i++) {
			for (int j=i; j<matriceAdj.length; j++) {
				if (matriceAdj[i][j] == 1) {
					Arete a = new Arete(sommets.get(i+1), sommets.get(j+1));
					listeAretes.add(a);
				}
			}
		}
		return listeAretes;
	}
}
