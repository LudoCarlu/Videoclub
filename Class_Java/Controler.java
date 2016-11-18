import java.text.*; // Pour les formats de date
import java.util.*;

public class Controler {
	private ArrayList<Adherent> listeMembre=null;
	private Catalogue catalogue = null;
	private Location loc = null;

	
	public Controler(ArrayList<Adherent> list,ArrayList<DescriptionArticle> listDesc){
		this.listeMembre=list;
		this.catalogue = new Catalogue(listDesc);
	}
	public Controler(ArrayList<Adherent> list,Hashtable<String,DescriptionArticle> listDesc){
		this.listeMembre=list;
		this.catalogue = new Catalogue(listDesc);
	}
	
	public void creerLocation() {
		System.out.println("Création de la location");
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Calendar aujourdhui = Calendar.getInstance();
		//new SimpleDateFormat("yyyy/MM/dd HH:mm").format(aujourdhui.getTime());
		loc = new Location(aujourdhui);
	}
	
	public Adherent authentification(String numTel,String mdp) {
		for(int i = 0; i < listeMembre.size(); i++) {
			if(listeMembre.get(i).getNumeroTel() == numTel
				&& listeMembre.get(i).getCodeSecret() == mdp) {
					System.out.println("Authentification de l'adhérent OK");
					return listeMembre.get(i);
				}	
		}
		return null;
	}
	
	public void MenuLocation() {
		creerLocation();
		Adherent ad = authentification("8193296454","1234");
		
		if(ad != null) {
			loc.ajouterAdherent(ad);
		}
	}
	public void saisirArticleLocation(String codeArticle, int quantite,int duree) {
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
			}
			else {
				System.out.println("Pas à louer");
			}
		}
		else {
			System.out.println("Code non trouvé");
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
	
}
