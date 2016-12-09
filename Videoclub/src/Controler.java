import java.util.*;

public class Controler {
	private Hashtable<String,Adherent> listeMembre=null;
	private Hashtable <Integer,Employe> listeEmploye=null;
	private Hashtable <String,Article> listeArticle = null;
	private Catalogue catalogue = null;
	private Location loc = null;
	private Hashtable<Integer,Location> listeLocation = null;


	public Controler(Hashtable<String,Adherent> list,Hashtable<String,DescriptionArticle> listDesc,
			Hashtable<Integer,Employe> listEmploye, Hashtable<String,Article> listeArt){
		this.listeMembre=list;
		this.catalogue = new Catalogue(listDesc);
		this.listeEmploye=listEmploye;
		this.listeArticle = listeArt;
	}
	public Hashtable<String,Adherent> getListAdherent(){
		return this.listeMembre;
	}	

	/*Methodes pour l'authentification */
	public Adherent authentificationMembre(String pseudo,String mdp) {
		try{
			String num=this.listeMembre.get(pseudo).getNumeroTel();
			String test=this.listeMembre.get(pseudo).getCodeSecret();
			if(num.equals(pseudo) && test.equals(mdp)){
				System.out.println("Authentification Adherent");
				loc.ajouterAdherent(this.listeMembre.get(pseudo));
				return this.listeMembre.get(pseudo);
			}
		}
		catch (NullPointerException e){
			System.out.print("pas de membre");
			return null;
		}
		return null;
	}
	public Employe authentificationEmploye(String idEmploye,String mdp) {
		try{
			int id=this.listeEmploye.get(Integer.parseInt(idEmploye)).getIdEmploye();
			String password=this.listeEmploye.get(Integer.parseInt(idEmploye)).getMdp();
			if(Integer.parseInt(idEmploye)==id && password.equals(mdp)){
				System.out.println("Authentification Employé");
				return this.listeEmploye.get(id);
			}
		}
		catch (NullPointerException e){
			System.out.print(e);
			return null;
		}
		catch (NumberFormatException e){
			System.out.print(e);
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

	/*Methodes pour la location */

	public Location getLocation(){
		return this.loc;
	}
	public void creerLocation() {
		System.out.println("Création de la location");
		Calendar aujourdhui = Calendar.getInstance();
		this.loc = new Location(aujourdhui);
	}

	public boolean saisirArticleLocation(String codeArticle, int quantite,int duree) {
		if(this.loc.isTerminee()==false){
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
		return false;
	}
	//Le code article saisit est le code de la description de l'article
	public boolean saisirArticleLoc (String codeBarre,int duree) {
		if(this.loc.isTerminee() == false) {
			//On récupère l'article grace à son code barre
			Article art = listeArticle.get(codeBarre);

			//On cherche le code de sa description
			String codeArticle = art.getCodeDescription();

			/*On va chercher cette description dans le catalogue
			 * et on l'ajoute à l'article
			 */
			DescriptionArticle desc = catalogue.getDesc(codeArticle);
			art.ajouterDescription(desc);
			//desc.setListeArticleLouable(this.listeArticle);

			if(desc != null) {
				//Ce n'est pas des confiseries
				if(desc.getPrixJournalier() != -1) {
					System.out.println("pas une confiserie");
					loc.creerLigneArticles(art);
					//loc.creerLigneArticles(desc, 1);
					loc.setDateDue(duree);
					loc.majMontant();
					loc.toString();
					return true;
				}
				else {
					System.out.println("Article pas à louer");
					return false;
				}
			}
		}
		System.out.println("Code article non trouvé");
		return false;
	}

	public void terminerLocation () {
		loc.setEstTerminee(true);
		float montantFinal = loc.getMontant();
		Paiement p = new Paiement(montantFinal);
		loc.setPaiement(p);
		afficherMontant();
		Videoclub v = Videoclub.instanceVideoclub();
		Database D = v.getDB();
		D.insertLocation(loc);

	}
	public float afficherMontant() {
		return loc.getMontant();

	}

	/* Methodes pour l'acquisition d'un film */
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

		acq.ajouterArticle(desc, quantite);
		if (catalogue.getDesc(codeArticle) == null){
			acq.ajouterDescriptionArticle(desc);
			catalogue.ajouterDesc(desc); 
		}

		//Ajoute temporairement l'article au catalogue au lieu de le réinitialiser au complet après la mise à jour de la db
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

		Acquisition acq = new Acquisition();
		/*Ajoutera le nombre d'articles indiqués mais ajoutera seulement une nouvelle description si elle n'est
		 * pas déjà présente dans le catalogue
		 */
		acq.ajouterArticle(desc, quantite);
		
		if (catalogue.getDesc(codeArticle) == null){
			acq.ajouterDescriptionArticle(desc);
			catalogue.ajouterDesc(desc); 
		}

		//Ajoute temporairement l'article au catalogue au lieu de le réinitialiser au complet après la mise à jour de la db
		acq.updatedb();
	}

	/* Methode pour l'inscription */
	public void Inscription(String nom, String prenom, String num, String cb, String adresse, String mdp) {
		Inscription ins = new Inscription(nom,prenom,num,cb,adresse,mdp);
		Adherent ad = ins.ajouterAdherent();
		this.listeMembre.put(num, ad);
	}


	/*Methode pour charger les locations depuis la base de données */
	public void addListeLocation(ArrayList<Location> l) {
		this.listeLocation = new Hashtable<Integer,Location>();
	
		for(int i=0;i < l.size(); i++) {
			Adherent ad = listeMembre.get(l.get(i).getNumAdherent());
			Article a = listeArticle.get(l.get(i).getCodeBarre());
			DescriptionArticle desc = catalogue.getDesc(a.getCodeDescription());
			a.ajouterDescription(desc);
			ArrayList<LigneArticle> la = new ArrayList<LigneArticle>();
			la.add(new LigneArticle(a,l.get(i).getDateDue(),l.get(i).getDateRetour()));

			//Pour ajouter tous les articles dans la ligne Article de la location par rapport à son id
			for(int j = i+1; j < l.size(); j++) {
				if(l.get(i).getIdLoc() == l.get(j).getIdLoc()) {
					Article a2 = listeArticle.get(l.get(j).getCodeBarre());
					DescriptionArticle desc2 = catalogue.getDesc(a2.getCodeDescription());
					a2.ajouterDescription(desc2);
					la.add(new LigneArticle(a2,l.get(j).getDateDue(),l.get(j).getDateRetour()));
				}
			}
			if(listeLocation.containsKey(l.get(i).getIdLoc()) ==  false) {
				Location tmp = new Location(l.get(i).getIdLoc(),ad,l.get(i).getDateHeure(),la,l.get(i).montant);
				listeLocation.put(tmp.getIdLoc(),tmp);
				
			}
		}
		//System.out.println(listeLocation);
	}
	
	//Effectuer un retour
	public void effectuerUnRetour(String codeBarre) {
		Retour r;
		Videoclub v = Videoclub.instanceVideoclub();
		//System.out.println(this.listeLocation);
		for(int i=1; i <= this.listeLocation.size(); i++) {
			int taille = this.listeLocation.get(i).getListeLigneArticles().size();
			
			for(int j=0; j < taille; j++) {
				LigneArticle la = this.listeLocation.get(i).getListeLigneArticles().get(j);
				if(la.getCodeBarreArticle().equals(codeBarre) && la.getArticle().isLoue() == true) {
					r = new Retour(this.listeLocation.get(i).getListeLigneArticles().get(j));
					la = r.getLigneArticles();
					//this.listeLocation.get(i).getListeLigneArticles().get(j).getArticle().setLoue(false);
					//this.listeLocation.get(i).getListeLigneArticles().get(j).setDateRetour(la.getDateRetour());
					v.getDB().retour(this.listeLocation.get(i).getIdLoc(), la);
					
					if(r.isEnRetard() == true) {
						System.out.println("En retard");
						System.out.println(this.listeLocation.get(i).getListeLigneArticles().get(j));
						//Cas amende
					}
				}
			}
		}
	}
}
