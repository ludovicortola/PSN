package factory;

import java.util.HashMap;
import java.util.Map;

import psn.Sommet;
import utils.SommetUtils;

public class SommetFactory {

	public static Map<Integer, Sommet> getInstances(int[][] matriceAdj) {
		Map<Integer, Sommet> sommets = new HashMap<>(); // Taille à modifier
		for (int i = 0; i < matriceAdj.length; i++) {
			int x = 0;
			int y = 0;
			switch (i) {
			case 0:
				x=50;
				y=95;
				break;
			case 1:
				x=20;
				y=66;
				break;
			case 2:
				x=80;
				y=66;
				break;
			case 3:
				x=0;
				y=25;
				break;
			case 4:
				x=33;
				y=25;
				break;
			case 5:
				x=95;
				y=25;
				break;
			}
			sommets.put(i+1, new Sommet(i+1, x, y));
		}
		computeSommets(sommets, matriceAdj);
		return sommets;
	}

	private static void computeSommets(Map<Integer, Sommet> sommets, int[][] matriceAdj) {
		sommets.values().forEach((s) -> {
			SommetUtils.computeSuccessors(s, sommets, matriceAdj);
		});
	}
}
