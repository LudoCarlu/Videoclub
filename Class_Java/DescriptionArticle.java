
public class DescriptionArticle {
	
	private int id;
	private String codeArticle;
	private String description;
	private float prixVente;
	private float prixJournalier;
	private String titre;
	private String genre;
	private boolean estNouveau;

	
	public DescriptionArticle (int id,String ca, String desc,float prixVente, 
			float prixJournalier, String titre, String genre, boolean estNouveau) {
		this.id = id;
		this.codeArticle = ca;
		this.description = desc;
		this.prixVente = prixVente;
		this.prixJournalier = prixJournalier;
		this.titre = titre;
		this.genre = genre;
		this.estNouveau = estNouveau;
	}
		
	public String getCodeArticle() {
		return this.codeArticle;
	}
	public String getTitre() {
		return this.titre;
	}
	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(float prix) {
		this.prixVente= prix;
	}
	public float getPrixJournalier() {
		return this.prixJournalier;
	}
	public void setPrixJournalier(float prixJ) {
		this.prixJournalier = prixJ;
	}
	
	
	public String toString() {
		return "DescriptionArticle : \n"
				+ "idDesc : " + this.id 
				+ "\ncodeArticle : " + this.codeArticle 
				+ "\nprixVente : " + this.prixVente
				+ "\nprixJournalier : " + this.prixJournalier
				+ "\ntitre : " + this.titre
				+ "\ngenre : " + this.genre
				+ "\nestNouveau : " + this.estNouveau
				+"\ndescription : " + this.description
				+ "\n";
				
	}
	
}
