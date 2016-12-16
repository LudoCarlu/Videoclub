import java.util.*;

/* 
 * Operation est une classe abstraite
 * Une opération est soit une location, soit une vente, soit une amende
 * Elle contient une arraylist de ligne d'articles
 * 
 * Cette ligne d'articles sera donc ;
 *  - soit un ligne d'articles correspondant à une ligne d'articles de location
 * 	- soit un ligne d'articles correspondant à une ligne d'articles de vente
 * 
 * Elle contient un paiement : Dans cette application le paiement n'est pas vraiment implémenté
 * on crée juste un objet Paiement contenant un montant
 * 
 * Elle contient une date utilisé notamment dans les locations
 * et un montant qui est commun à l'amende, la vente et la location
 * 
 */
public abstract class Operation {
	protected int id;
	protected Calendar cal;
	protected Date dateHeure;
	protected float montant = 0;
	protected ArrayList<LigneArticle> ligneArticle = new ArrayList<LigneArticle>();
	protected boolean estTerminee = false;
	protected Paiement pay;
	
	//Getteurs / Setteurs
	public int getId() {
		return this.id;
	}
	public boolean isTerminee(){
		return this.estTerminee;
	}
	public void setMontant(float m) {
		this.montant=m;
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
	
	public Date getDateHeure() {
		return this.dateHeure;
	}
	public void setDateHeure(Date d) {
		this.dateHeure = d;
	}
	
}
