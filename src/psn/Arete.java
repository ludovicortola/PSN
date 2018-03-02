package psn;

public class Arete {

	private Sommet sommetDepart;
	private Sommet sommetArrivee;
	private boolean pont;


	public Arete(Sommet sommetDepart, Sommet sommetArrivee) {
		this.sommetDepart = sommetDepart;
		this.sommetArrivee = sommetArrivee;
	}
	
	public void affiche() {
		System.out.println("Arete entre "+ this.sommetDepart.getId()+" et "+this.sommetArrivee.getId()+", pont : "+this.pont);
	}

	public Sommet getSommetDepart() {
		return sommetDepart;
	}

	public Sommet getSommetArrivee() {
		return sommetArrivee;
	}

	public boolean isPont() {
		return pont;
	}

	public void setPont(boolean pont) {
		this.pont = pont;
	}
}
