import java.text.SimpleDateFormat;
import java.util.*;

public class Location extends Operation{
	
	private int idLoc;
	private Date dateDue;
	private Date dateRetour;
	private Adherent adherent;
	
	//Vu que chaque ligne d'articles est une amende potentielle il faut une liste d'amende
	private ArrayList<Amende> listeAmende = null;
	
	//Pour charger les locations depuis la database
	private String codeBarre;
	private String numAdherent;
	
	//Constructeur
	public Location(Calendar cal) {
		this.cal = cal;
		new SimpleDateFormat("yyyy-MM-dd HH:mm");
		this.dateHeure = cal.getTime();
	}
	

	//Constructeur utilisé pour charger les locations de la BDD
	public Location(int id, String numAdherent, String codeBarre, Date dateHeure, 
			Date dateDue, Date dateRetour, float montant) {
		this.idLoc = id;
		this.numAdherent = numAdherent;
		this.codeBarre = codeBarre;
		this.dateHeure = dateHeure;
		this.dateDue = dateDue;
		this.dateRetour = dateRetour;
		this.montant = montant;
	}
	
	//Constructeur pour charger correctement les locations dans le système après le chargement depuis la base
	//de données
	public Location(int id, Adherent ad, Date dateHeure, ArrayList<LigneArticle> l, float montant) {
		this.idLoc = id;
		this.adherent = ad;
		this.dateHeure = dateHeure;
		this.ligneArticle = l;
		this.montant = montant;
	}

	public void ajouterAdherent(Adherent ad) {
		this.adherent =  ad;
	}
	
	//On crée un ligne d'articles
	public void creerLigneArticles(Article a) {
		LigneArticle lar = new LigneArticle(a);
		this.ligneArticle.add(lar);
	}
	
	public void majMontant() {
		float montant=0;
		for (int i=0;i<this.ligneArticle.size();i++){
			long difference = Math.abs(this.ligneArticle.get(i).getDateDue().getTime() - dateHeure.getTime());
			long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
			long nombreJourLocation = (long)difference/MILLISECONDS_PER_DAY;
			montant=montant+this.ligneArticle.get(i).getPrixLocation()*nombreJourLocation;
		}
		this.setMontant(montant);
	}
	
	//Getteurs / Setteurs
	public Date getDateHeure() {
		return dateHeure;
	}
	public Date getDateDue() {
		return dateDue;
	}
	public Date getDateRetour() {
		return dateRetour;
	}
	public Adherent getAdherent() {
		return adherent;
	}
	public ArrayList<Amende> getAmende() {
		return this.listeAmende;
	}
	
	public void setIdLoc(int id) {
		this.idLoc = id;
	}
	
	public void setDateDue(int duree) {
		Calendar d = this.cal;
		d.add(Calendar.DATE,duree);
		this.ligneArticle.get(this.ligneArticle.size()-1).setDateDue(d.getTime());
		d.add(Calendar.DATE,-duree);
	}
	//Getteurs pour locations qui viennent de la database
	public int getIdLoc() {
		return idLoc;
	}
	public String getCodeBarre() {
		return codeBarre;
	}
	public String getNumAdherent() {
		return numAdherent;
	}
	// Fin
	

	@Override
	public String toString() {
		return "Location "+ this.idLoc
				+ "\nAdherent=" + adherent 
				+ "\ndateHeure = " + this.dateHeure
				+ "\n" + this.ligneArticle
				+ "]";
	}
	
	public void ajouterAmende(Amende am) {
		Videoclub v = Videoclub.instanceVideoclub();
		
		//Si il n'y a pas d'amende sur la location et on en ajoute une
		if(listeAmende == null) {
			listeAmende = new ArrayList<Amende>();
			listeAmende.add(am);
			v.getDB().insertAmende(am);
		}
		else {
			//On regarde si l'amende existe déjà et on l'a met à jour
			int co = 0;
			String codeArtAmende = am.getCodeBarre();
			for(int i=0; i<listeAmende.size(); i++) {
				if(listeAmende.get(i).getCodeBarre().equals(codeArtAmende) == true) {
					listeAmende.get(i).setMontant(am.getMontant());
					listeAmende.get(i).setDateHeure(am.getDateHeure());
					co++;
					v.getDB().udpateAmende(am);
				}
			}
			//Amende pas encore présente et il y a deja une autre amende
			if (co == 0) {
				listeAmende.add(am);
				v.getDB().insertAmende(am);
			}
		}
	}

}
