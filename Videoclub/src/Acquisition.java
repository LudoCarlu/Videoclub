import java.util.ArrayList;

public class Acquisition {
	
	private ArrayList<Article> listeAcquisition;
	private Database D;
	
	public Acquisition(){
		this.D = Database.instanceDB();
	}
	public ArrayList<Article> getListeAcquisition() {
		return listeAcquisition;
	}
	public void setListeAcquisition(ArrayList<Article> listeAcquisition) {
		this.listeAcquisition = listeAcquisition;
	}
	public void ajouterArticle(DescriptionArticle desc){
		Article art = new Article();
		art.setDescription(desc);
		if (listeAcquisition == null){
			listeAcquisition = new ArrayList<Article>();
		}
		listeAcquisition.add(art);
	}
	public void updatedb(){
		
		for (int i=0;i<listeAcquisition.size();i++){
		Article art;
		art = listeAcquisition.get(i);	
		D.insertArticle(art.getDescription());
		}
	}
	/* ces méthodes ont été ajouter au controler
	public void ajouterFilm(String codeArticle, String titre, String genre, String description, float prixJournalier, 
			float prixHebdomadaire, float prixVente, boolean estNouveau){
		
		DescriptionArticle desc;
		desc = new DescriptionArticle();
		desc.setCodeArticle(codeArticle);
		desc.setTitre(titre);
		desc.setGenre(genre);
		desc.setDescription(description);
		desc.setPrixJournalier(prixJournalier);
		desc.setPrixHebdomadaire(prixHebdomadaire);
		desc.setPrixVente(prixVente);
		desc.setEstNouveau(estNouveau);
	
		Article art = new Article();
		art.setDescription(desc);
		
		listeAcquisition.add(art);
	}
	
	public void ajouterAutre(String codeArticle, String description, float prixVente){
		
		DescriptionArticle desc;
		desc = new DescriptionArticle();
		desc.setCodeArticle(codeArticle);
		desc.setDescription(description);
		desc.setTitre(description);
		desc.setPrixVente(prixVente);
		
		Article art = new Article();
		art.setDescription(desc);
		
		listeAcquisition.add(art);
	}
	*/
}


