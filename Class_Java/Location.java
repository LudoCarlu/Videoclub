import java.util.ArrayList;
import java.util.Date;

public class Location extends Operation{
	
	private Date dateDue;
	private Date dateRetour;
	private Adherent adherent;
	private Amende amende;
	
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
	public Location(Date dateHeure, Date dateDue, Date dateRetour, Adherent adherent,float montant) {
		super();
		this.dateHeure = dateHeure;
		this.dateDue = dateDue;
		this.dateRetour = dateRetour;
		this.adherent = adherent;
		this.montant=montant;
	}
	public Location(Date dateHeure, Date dateDue, Date dateRetour, Adherent adherent,ArrayList<LigneArticle> list) {
		super();
		this.dateHeure = dateHeure;
		this.dateDue = dateDue;
		this.dateRetour = dateRetour;
		this.adherent = adherent;
		this.ligneArticle=list;
	}
	@Override
	public String toString() {
		return "Location [dateDue=" + dateDue + ", dateRetour=" + dateRetour + ", adherent=" + adherent + ", amende="
				+ amende + "]";
	}
	

}
