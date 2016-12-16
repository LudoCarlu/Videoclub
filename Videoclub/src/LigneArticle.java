import java.util.Date;

/*
 * Nous avons décidé qu'une ligne d'articles pouvait à la fois représenter
 * une ligne de vente et une ligne de location
 * Il y a donc 2 paramètres que la ligne de vente n'utilise pas et qui seront donc null
 * qui sont la date de retour et la date due
 * Ces paramètres sont implémentés pour la location seulement
 */
public class LigneArticle {
	private int quantite;
	private DescriptionArticle desc;
	private Date dateDue;
	private Article art;
	private Date dateRetour;
	
	
	public LigneArticle(DescriptionArticle desc, int quantite) {
		this.desc = desc;
		this.quantite = quantite;
	}
	//Constructeur pour une location
	public LigneArticle(Article a) {
		this.art = a;
		this.quantite = 1;
		this.desc = art.getDescription();
	}
	//Constructeur pour une vente
	public LigneArticle(Article a, int qte) {
		this.art = a;
		this.quantite = qte;
		this.desc = art.getDescription();
	}
	
	public LigneArticle(Article a, Date dateDue, Date dateRetour) {
		this.art = a;
		this.desc = a.getDescription();
		this.dateDue = dateDue;
		this.dateRetour = dateRetour;
		this.quantite = 1;
	}
	
	//Getteurs / Setteurs
	public int getQuantite() {
		return quantite;
	}
	public DescriptionArticle getDesc() {
		return desc;
	}
	public float getPrixLocation() {
		return this.quantite*desc.getPrixJournalier();
	}	
	public DescriptionArticle getDescriptionArticle(){
		return this.desc;
	}
	public float getPrixVente() {
		return this.quantite*desc.getPrixVente();
	}
	public void setDateDue(Date d) {
		this.dateDue = d;
	}
	public Date getDateDue() {
		return this.dateDue;
	}
	public void setDateRetour (Date d) {
		this.dateRetour = d;
	}
	public Date getDateRetour() {
		return this.dateRetour;
	}
	public String getCodeBarreArticle() {
		return this.art.getCodeBarre();
	}
	public Article getArticle() {
		return this.art;
	}
	
	public String toString() {
		return "Ligne Articles : "
				+ "\n Quantite : "+this.quantite 
				+ "\n Article : "+art.getCodeBarre()
				+ "\n item: " + desc.getDescription()
				+ "\n Date Due : " + dateDue
				+ "\n Date Retour : " + dateRetour
				+"\n prix vente: "+ desc.getPrixVente();
	}
	

	
	//Calcul le montant de la ligne d'articles
	public float getMontant(Date d) {
		float m = 0;
		
		long difference = Math.abs(this.dateDue.getTime()-d.getTime());
		long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
		long nombreJour = (long)difference/MILLISECONDS_PER_DAY;
		
		m = this.quantite * desc.getPrixJournalier()*nombreJour;
		
		return m;
	}
}
