import java.util.Hashtable;

public class DescriptionArticle {
	
	private int id;
	private String codeArticle;
	private String description;
	private float prixVente;
	private float prixJournalier;
	private String titre;
	private String genre;
	private boolean estNouveau; /*Je trouve que le "Est" devrait être enlevé il est inutile est se répète dans la méthode
	 							* isEstNouveau() is=Est. Qu'est ce que vous en pensez?
	 							*/
	private float prixHebdomadaire;
	private Hashtable<String,Article> listeArticle = null;
	

	public DescriptionArticle(){
		//nécessaire pour créer un objet sans id
	}
	public DescriptionArticle (int id, String ca, String desc,float prixVente, 
			float prixJournalier, String titre, String genre, boolean estNouveau, float prixHebdomadaire) {
		this.id = id; //N'est pas créé ici
		this.codeArticle = ca;
		this.description = desc;
		this.prixVente = prixVente;
		this.prixJournalier = prixJournalier;
		this.titre = titre;
		this.genre = genre;
		this.estNouveau = estNouveau;
		this.prixHebdomadaire = prixHebdomadaire;
	}
	public int getUniqueid()	 {
		return this.id;
	}
	public String getCodeArticle() {
		return this.codeArticle;
	}
	public String getTitre() {
		return this.titre;
	}
	public void setTitre(String titre){
		this.titre = titre;
	}
	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}
	
	public String getDescription() {
		return description;
	}
	public void setGenre(String genre){
		this.genre = genre;
	}
	public String getGenre(){
		return this.genre;
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
	public void setPrixHebdomadaire(float prixH){
		this.prixHebdomadaire = prixH;
	}
	public float getPrixHebdomadaire(){
		return this.prixHebdomadaire;
	}
	public void setEstNouveau(boolean estNouveau){
		this.estNouveau = estNouveau;
	}
	public boolean getEstNouveau() {
		return this.estNouveau;
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
	
	// Reprendre ici
	public void setListeArticleLouable(Hashtable<String,Article> liste) {
		this.listeArticle = new Hashtable<String,Article>();
		String i;
		int co = 0;
		while (co < liste.size()) {
			i = liste.keys().nextElement();
			System.out.println(liste.get(i));
			if(liste.get(i).getCodeDescription().equals(this.codeArticle)) {
				this.listeArticle.put(this.codeArticle,liste.get(i));
			}
			co++;
		}

	}
	
	public Article getArticleLouable() {
		System.out.println(listeArticle);
		if(listeArticle.isEmpty() == false) {
			return this.listeArticle.get(listeArticle.size()-1);
		}
		return null;
	}
}
