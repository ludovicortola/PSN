package psn;

public class Arete {

	private Sommet sommetDepart;
	private Sommet sommetArrivee;
	private boolean pont;

	public Arete(Sommet sommetDepart, Sommet sommetArrivee, int[][] matriceAdj) {
		this.sommetDepart = sommetDepart;
		this.sommetArrivee = sommetArrivee;
		this.pont = setPont(matriceAdj);
		//affiche();
	}
	
	public void affiche() {
		System.out.println("Arete entre "+this.sommetDepart.getNumero()+" et "+this.sommetArrivee.getNumero()+", pont : "+this.pont);
	}

	public boolean setPont(int[][] matrice){
		boolean pont;
		int[][] A = new int[matrice.length][matrice.length];
		for (int i=0; i<matrice.length; i++) {
			for (int j=0; j<matrice.length; j++) {
				A[i][j] = matrice[i][j];
			}
		}
		A = removeArete(this, A);
		int[][] S = new int[matrice.length][matrice.length];
		for (int k=0; k<matrice.length; k++) {
			int[][] P = puissanceMatrice(A, k);
			S = sommematrice(S, P);
		}
		if (S[this.sommetDepart.getNumero()-1][this.sommetArrivee.getNumero()-1] == 0) {
			pont = true;
		} else {
			pont = false;
		}
		return pont;
	}

	private int[][] sommematrice(int[][] mat1, int[][] mat2) {
		int[][] somme = new int[mat1.length][mat1.length];
		for (int i=0; i<mat1.length; i++) {
			for (int j=0; j<mat1.length; j++) {
				somme[i][j] = mat1[i][j] + mat2[i][j];
			}
		}
		return somme;
	}

	private int[][] puissanceMatrice(int[][] mat, int p) {
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

	private int[][] produitMatrice(int[][] mat1, int[][] mat2) {
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

	public int[][] removeArete(Arete arete, int[][] matrice){
		matrice[arete.getSommetDepart().getNumero()-1][arete.getSommetArrivee().getNumero()-1] = 0;
		matrice[arete.getSommetArrivee().getNumero()-1][arete.getSommetDepart().getNumero()-1] = 0;
		arete.getSommetDepart().setSuccessors(matrice);
		arete.getSommetArrivee().setSuccessors(matrice);
		return matrice;
	}

	public Sommet getSommetDepart() {
		return sommetDepart;
	}

	public Sommet getSommetArrivee() {
		return sommetArrivee;
	}

	public boolean isPont(int[][] matrice) {
		this.pont = setPont(matrice);
		return this.pont;
	}

}
