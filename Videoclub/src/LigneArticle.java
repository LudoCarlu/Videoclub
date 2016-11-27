import java.util.Date;

public class LigneArticle {
	private int quantite;
	private DescriptionArticle desc;
	private Date dateDue;
	
	public LigneArticle(DescriptionArticle desc, int quantite) {
		this.desc = desc;
		this.quantite = quantite;
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
}
