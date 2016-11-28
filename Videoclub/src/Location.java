import java.text.SimpleDateFormat;
import java.util.*;

public class Location extends Operation{
	
	private Date dateDue;
	private Date dateRetour;
	private Adherent adherent;
	private Amende amende;
	
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
	
	public void ajouterAdherent(Adherent ad) {
		this.adherent =  ad;
	}
	public void creerLigneArticles(DescriptionArticle desc, int quantite) {
		LigneArticle lar = new LigneArticle(desc,quantite);
		//System.out.println(lar);
		this.ligneArticle.add(lar);
	}
	
	public void majMontant() {
		float montant=0;
		int duree=0;
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
	
	public void setDateDue(int duree) {
		Calendar d = this.cal;
		d.add(Calendar.DATE,duree);
		this.ligneArticle.get(this.ligneArticle.size()-1).setDateDue(d.getTime());
		d.add(Calendar.DATE,-duree);
		//this.dateDue = d.getTime();
		
	}

	@Override
	public String toString() {
		return "Location [dateDue=" + dateDue + ", dateRetour=" + dateRetour + ", adherent=" + adherent + ", amende="
				+ amende + "]";
	}
	

}
