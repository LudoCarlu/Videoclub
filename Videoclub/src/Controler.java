import java.text.*; // Pour les formats de date
import java.util.*;

public class Controler {
	private Hashtable<String,Adherent> listeMembre=null;
	private Catalogue catalogue = null;
	private Location loc = null;

	
	public Controler(Hashtable<String,Adherent> list,ArrayList<DescriptionArticle> listDesc){
		this.listeMembre=list;
		this.catalogue = new Catalogue(listDesc);
	}
	public Controler(Hashtable<String,Adherent> list,Hashtable<String,DescriptionArticle> listDesc){
		this.listeMembre=list;
		this.catalogue = new Catalogue(listDesc);
	}
	public Hashtable<String,Adherent> getListAdherent(){
		return this.listeMembre;
	}
	public Location getLocation(){
		return this.loc;
	}
	public void creerLocation() {
		System.out.println("Création de la location");
		Calendar aujourdhui = Calendar.getInstance();
		this.loc = new Location(aujourdhui);
	}
	
	public Adherent authentificationMembre(String pseudo,String mdp) {
		try{
			String num=this.listeMembre.get(pseudo).getNumeroTel();
			String test=this.listeMembre.get(pseudo).getCodeSecret();
			if(num.equals(pseudo) && test.equals(mdp)){
				System.out.println("Authentification");
					return this.listeMembre.get(pseudo);
				}
		}
		catch (NullPointerException e){
			System.out.print("pas de membre");
			return null;
		}
		return null;
	}
	
	/*public void MenuLocation() {
		creerLocation();
		Adherent ad = authentificationMembre("8192396454","1234");
		if(ad != null) {
			loc.ajouterAdherent(ad);
		}
	}*/
	public boolean saisirArticleLocation(String codeArticle, int quantite,int duree) {
		DescriptionArticle desc = catalogue.getDesc(codeArticle);
		
		//System.out.println("PJ " +desc.getPrixJournalier());
		//On peut louer l'article
		if (desc != null) {
			if (desc.getPrixJournalier() != -1) {
				loc.creerLigneArticles(desc,quantite);
		//		System.out.println(desc);
				System.out.println("durée"+duree);
				loc.setDateDue(duree);
				loc.majMontant();
				return true;
			}
			else {
				System.out.println("Pas à louer");
				return false;
			}
		}
		else {
			System.out.println("Code non trouvé");
			return false;
		}
	}
	
	public void terminerLocation () {
		loc.setEstTerminee(true);
		float montantFinal = loc.getMontant();
		Paiement p = new Paiement(montantFinal);
		loc.setPaiement(p);
		afficherMontant();
	}
	public float afficherMontant() {
		return loc.getMontant();
	}
	/**
	 * Pour l'ajout d'un film dédié en la location à l'inventaire
	 * @param codeArticle
	 * @param titre
	 * @param genre
	 * @param description : texte comprenant une brève description du film
	 * @param prixJournalier
	 * @param prixHebdomadaire
	 * @param prixVente : La valeur dédié à la revente du film ou si perdu
	 * @param estNouveau
	 * @param quantite : Le nombre d'articles identiques ajoutés
	 */
	public void aquerirFilm(String codeArticle, String titre, String genre, String description, float prixJournalier, 
			float prixHebdomadaire, float prixVente, boolean estNouveau, int quantite){
		
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
	
		Acquisition acq = new Acquisition();
		
		for (int i=1;i<=quantite;i++){
			acq.ajouterArticle(desc);
			catalogue.ajouterDesc(desc); 
			//Ajoute temporairement l'article au catalogue au lieu de le réinitialiser au complet après la mise à jour de la db
		}
		acq.updatedb();
	}
	/**
	 * Pour l'ajout de tout article autre qu'un film en location
	 * @param codeArticle
	 * @param description
	 * @param prixVente
	 * @param quantite : Le nombre d'articles identiques ajoutés
	 */
	public void acquerirAutre(String codeArticle, String description, float prixVente, int quantite){
		
		DescriptionArticle desc;
		desc = new DescriptionArticle();
		desc.setCodeArticle(codeArticle);
		desc.setDescription(description);
		desc.setTitre(description);
		desc.setPrixVente(prixVente);
		
		//desc.setTitre(null);
		//desc.setGenre(null);

		//desc.setPrixJournalier(null);
		//desc.setPrixHebdomadaire(null);
		//desc.setEstNouveau();
		
		Acquisition acq = new Acquisition();
		
		for (int i=1;i<=quantite;i++){
			acq.ajouterArticle(desc);
			catalogue.ajouterDesc(desc); 
			//Ajoute temporairement l'article au catalogue au lieu de le réinitialiser au complet après la mise à jour de la db
		}
		acq.updatedb();
	}
	
	public void Inscription(String nom, String prenom, String num, String cb, String adresse, String mdp) {
		Inscription ins = new Inscription(nom,prenom,num,cb,adresse,mdp);
		Adherent ad = ins.ajouterAdherent();
		this.listeMembre.put(num, ad);
	}
	
}
