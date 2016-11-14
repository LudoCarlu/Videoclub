import java.util.Date;

public class Location extends Operation{
	
	private Date dateDue;
	private Date dateRetour;
	private Adherent adherent;
	private Amende amende;
	
	
	public Location(Date dateDue, Date dateRetour, Adherent adherent, Amende amende) {
		super();
		this.dateDue = dateDue;
		this.dateRetour = dateRetour;
		this.adherent = adherent;
		this.amende = amende;
	}
	public Date getDateDue() {
		return dateDue;
	}
	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}
	public Date getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	public Adherent getAdherent() {
		return adherent;
	}
	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}
	public Amende getAmende() {
		return amende;
	}
	public void setAmende(Amende amende) {
		this.amende = amende;
	}
	
	

}
