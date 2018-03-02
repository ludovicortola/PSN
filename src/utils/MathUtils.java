package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import psn.Arete;
import psn.Sommet;

public class MathUtils {

	public static int[][] puissanceMatrice(int[][] mat, int p) {
		int[][] prod = new int[mat.length][mat.length];
		if (p == 0) {
			for (int i=0; i<mat.length; i++) { // Matrice identitée
				prod[i][i] = 1;
			}
		} else {
			if (p == 1) {
				prod = mat;
			} else {
				prod = mat;
				for (int l=1; l<p; l++){
					prod = produitMatrice(prod, mat);
				}
			}
		}
		return prod;
	}

	public static int[][] produitMatrice(int[][] mat1, int[][] mat2) {
		int[][] produit = new int[mat1.length][mat1.length];
		
		for (int i=0; i<mat1.length; i++) {
			for (int j=0; j<mat1.length; j++) {
				int sum = 0;
				for (int k=0; k<mat1.length; k++) {
					sum += mat1[i][k]*mat2[k][j];
				}
				produit[i][j] = sum;
			}
		}
		return produit;
	}
	

	public static int[][] sommematrice(int[][] mat1, int[][] mat2) {
		int[][] somme = new int[mat1.length][mat1.length];
		for (int i=0; i<mat1.length; i++) {
			for (int j=0; j<mat1.length; j++) {
				somme[i][j] = mat1[i][j] + mat2[i][j];
			}
		}
		return somme;
	}
}
