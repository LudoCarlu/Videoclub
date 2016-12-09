import java.util.ArrayList;

public class Acquisition {
	
	private ArrayList<Article> listeArticle;
	private ArrayList<DescriptionArticle> listeDesc;
	private Database D;
	
	public Acquisition(){
		this.D = Database.instanceDB();
	}
	public ArrayList<Article> getListeAcquisition() {
		return listeArticle;
	}
	public void setListeAcquisition(ArrayList<Article> listeAcquisition) {
		this.listeArticle = listeAcquisition;
	}
	public void ajouterArticle(DescriptionArticle desc, int qte){
		Article art = new Article();
		art.setDescription(desc);
		if (listeArticle == null){
			listeArticle = new ArrayList<Article>();
		}		
		
		for (int i = 0;i<qte;i++){

		listeArticle.add(art);
		}

	}
	public void ajouterDescriptionArticle(DescriptionArticle desc){
		if (listeDesc == null){
			listeDesc = new ArrayList<DescriptionArticle>();
		}		

		listeDesc.add(desc);
		
	}
	public void updatedb(){
		try {
		D.insertArticle(listeArticle);
		} catch (NullPointerException e) {
            System.out.print("liste d'article non initialisé"); 
		}
		
		try {
			D.insertDescriptionArticle(listeDesc);
		} catch (NullPointerException e) {
            System.out.print("liste de description non initialisé. Description déjà présente possible"); 
		}
		
	}
}


