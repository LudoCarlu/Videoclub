import java.text.SimpleDateFormat;
import java.util.*;

public class Location extends Operation{
	
	private int idLoc;
	private Date dateDue;
	private Date dateRetour;
	private Adherent adherent;
	private Amende amende;
	
	//Pour charger les locations
	private String codeBarre;
	private String numAdherent;
	
	public Location(Calendar cal) {
		this.cal = cal;
		new SimpleDateFormat("yyyy-MM-dd HH:mm");
		this.dateHeure = cal.getTime();
	}
	public Location(Date dateHeure, Date dateDue, Date dateRetour, Adherent adherent,float montant) {
		super();
		this.dateHeure = dateHeure;
		this.dateDue = dateDue;
		this.dateRetour = dateRetour; 
		
		this.adherent = adherent;
		this.montant=montant;
	}
	// La date de retour est seulement établie au moment du retour, ne devrait pas faire partie du constructeur
	public Location(Date dateHeure, Date dateDue, Date dateRetour, Adherent adherent,ArrayList<LigneArticle> list) {
		super();
		this.dateHeure = dateHeure;
		this.dateDue = dateDue;
		this.dateRetour = dateRetour;
		this.adherent = adherent;
		this.ligneArticle=list;
	}
	//Constructeurs utilisés pour charger les locations de la BDD
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
	public Location(int id, Adherent ad, Date dateHeure, ArrayList<LigneArticle> l, float montant) {
		this.idLoc = id;
		this.adherent = ad;
		this.dateHeure = dateHeure;
		this.ligneArticle = l;
		this.montant = montant;
	}
	
	
	
	public void ajouterAdherent(Adherent ad) {
		this.adherent =  ad;
		toString();
	}
	public void creerLigneArticles(DescriptionArticle desc, int quantite) {
		LigneArticle lar = new LigneArticle(desc,quantite);
		//System.out.println(lar);
		this.ligneArticle.add(lar);
	}
	public void creerLigneArticles(Article a) {
		LigneArticle lar = new LigneArticle(a);
		this.ligneArticle.add(lar);
	}
	
	public void majMontant() {
		float montant=0;
		//int duree=0;
		for (int i=0;i<this.ligneArticle.size();i++){
			long difference = Math.abs(this.ligneArticle.get(i).getDateDue().getTime() - dateHeure.getTime());
			long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
			long nombreJourLocation = (long)difference/MILLISECONDS_PER_DAY;
			System.out.println("JLOC "+nombreJourLocation);
			montant=montant+this.ligneArticle.get(i).getPrixLocation()*nombreJourLocation;
		}
		this.setMontant(montant);
	}
	
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
	public Amende getAmende() {
		return amende;
	}
	
	//Getteurs pour location qui vient de la bdd
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
	
	public void setDateDue(int duree) {
		Calendar d = this.cal;
		d.add(Calendar.DATE,duree);
		this.ligneArticle.get(this.ligneArticle.size()-1).setDateDue(d.getTime());
		d.add(Calendar.DATE,-duree);
		//this.dateDue = d.getTime();
	}
	

	@Override
	/*public String toString2() {
		return "Location "
				+ "\nAdherent=" + adherent 
				+ "\ndateHeure = " + this.dateHeure
				//+ "\ndateDue = " + ligneArticle.get(ligneArticle.size()-1).getDateDue()
				+ "\nDateRetour = " + dateRetour
				+ "\nAmende="+amende;
	}*/
	public String toString() {
		return "Location "+ this.idLoc
				+ "\nAdherent=" + adherent 
				+ "\ndateHeure = " + this.dateHeure
				+ "\n" + this.ligneArticle
				+ "]";
	}
	

}
