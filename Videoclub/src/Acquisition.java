import java.util.ArrayList;

public class Acquisition {

	private ArrayList<Article> listeArticle;
	private ArrayList<DescriptionArticle> listeDesc;
	private Database D;

	//Constructeur 
	public Acquisition(){
		this.D = Database.instanceDB();
	}

	//Getteur / Setteur
	public ArrayList<Article> getListeAcquisition() {
		return listeArticle;
	}
	public void setListeAcquisition(ArrayList<Article> listeAcquisition) {
		this.listeArticle = listeAcquisition;
	}

	//Methode qui creer un article, lui attribue une description et l'ajoute dans un liste d'article
	public void ajouterArticle(DescriptionArticle desc, int qte){
		Article art = new Article();
		art.setDescription(desc);
		if (listeArticle == null){
			listeArticle = new ArrayList<Article>();
		}

		for (int i=0; i<qte; i++){
			listeArticle.add(art);
		}

	}
	
	//Ajouter une description dans une liste
	public void ajouterDescriptionArticle(DescriptionArticle desc){
		if (listeDesc == null){
			listeDesc = new ArrayList<DescriptionArticle>();
		}		

		listeDesc.add(desc);

	}
	
	//Méthode utiliser pour insérer les nouveaux articles dans la base de données
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


