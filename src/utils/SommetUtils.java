package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import psn.Sommet;

public class SommetUtils {
	public static void computeSuccessors(Sommet s, Map<Integer, Sommet> sommets, int[][] matriceAdj) {
		List<Sommet> successors = new ArrayList<>();
		
		for (int i = 0; i < matriceAdj.length; i++) {
			if (matriceAdj[s.getId() - 1][i] == 1) {
				successors.add(sommets.get(i+1));
			}
		}
		s.setSuccesseurs(successors);
	}
	
}
