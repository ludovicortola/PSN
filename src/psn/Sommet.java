package psn;

import java.util.ArrayList;
import java.util.List;

public class Sommet {
	
	private final int x;
	private final int y;
	private final int id;
	private List<Sommet> successeurs;
	
	public Sommet(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public void affiche() {
		System.out.println("Sommet numéro "+this.id+" de degré "+this.getDegre());
	}

	
	public int getId() {
		return id;
	}

	public int getDegre() {
		return successeurs == null ? 0 : successeurs.size();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public List<Sommet> getSuccesseurs() {
		return successeurs;
	}
	
	public void setSuccesseurs(List<Sommet> successeurs) {
		this.successeurs = successeurs;
	}
	
}
