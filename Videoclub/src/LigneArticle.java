import java.util.Date;

public class LigneArticle {
	private int quantite;
	private DescriptionArticle desc;
	private Date dateDue;
	private Article art;
	
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
	public String toString() {
		return "LIGNE ARTICLES : \n + Quantite : "+this.quantite + "\n" + desc;
	}
	
	public String getCodeBarreArticle() {
		return this.art.getCodeBarre();
	}
}
