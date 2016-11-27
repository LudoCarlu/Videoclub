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
	
	public void updatedb(){
		
		for (int i=0;i<listeAcquisition.size();i++){
		Article art;
		art = listeAcquisition.get(i);	
		D.insertArticle(art.getDescription());
		}
	}
	public void ajouterFilm(String codeArticle, String titre, String genre, String description, float prixJournalier, 
			float prixHebdomadaire, float prixVente, boolean estNouveau){
		
		Article art = null;
		DescriptionArticle desc;
		
		
		desc = new DescriptionArticle(codeArticle, description, prixVente, 
				prixJournalier, titre, genre, estNouveau, prixHebdomadaire);
		
		art.setDescription(desc);
	}
	public void ajouterAutre(){
		
	}
	
}


