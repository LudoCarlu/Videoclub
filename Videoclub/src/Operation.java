import java.util.*;

public abstract class Operation {
	protected int id;
	protected Calendar cal;
	protected Date dateHeure;
	protected float montant = 0;
	protected ArrayList<LigneArticle> ligneArticle = new ArrayList<LigneArticle>();
	protected boolean estTerminee = false;
	protected Paiement pay;
	
	public int getId() {
		return this.id;
	}
	public boolean isTerminee(){
		return this.estTerminee;
	}
	public void setMontant(float m) {
		this.montant =m;
	}
	
	public void setEstTerminee(boolean t) {
		this.estTerminee = t;
	}
	
	public float getMontant() {
		return this.montant;
	}
	
	public void setPaiement(Paiement p) {
		this.pay = p;
	}
	
	public ArrayList<LigneArticle> getListeLigneArticles() {
		return this.ligneArticle;
	}
}
