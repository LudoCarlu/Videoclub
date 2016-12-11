import java.util.Date;

public class LigneArticle {
	private int quantite;
	private DescriptionArticle desc;
	private Date dateDue;
	private Article art;
	private Date dateRetour;
	
	private Amende amende = null;
	
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
	
	public String toString() {
		return "Ligne Articles : "
				+ "\n Quantite : "+this.quantite 
				+ "\n Article : "+art.getCodeBarre()
				+ "\n item: " + desc.getDescription()
				+ "\n Date Due : " + dateDue
				+ "\n Date Retour : " + dateRetour
				+"\n prix vente: "+ desc.getPrixVente();
	}
	
	public String getCodeBarreArticle() {
		return this.art.getCodeBarre();
	}
	
	public Article getArticle() {
		return this.art;
	}
	
	public float getMontant(Date d) {
		float m = 0;
		
		long difference = Math.abs(this.dateDue.getTime()-d.getTime());
		long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
		long nombreJour = (long)difference/MILLISECONDS_PER_DAY;
		
		m = this.quantite * desc.getPrixJournalier()*nombreJour;
		
		return m;
	}
	public void setAmende(Amende am) {
		this.amende = am;
	}
}
