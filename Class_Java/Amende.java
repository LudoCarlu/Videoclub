
public class Amende {
	private Adherent ad;
	private Location loc;
	private float montant;
	private float tauxSemaine; //valeur à assigner
	private float tauxJournée; //valeur à assigner
	
	/* Les opérations du constructeur sont pour l'instant que du pseudo code. 
	Les méthodes de la classe Location doivent être implémentés
	*/
	public void Amende(Location loc)
		
		this.loc = loc;
		this.ad = loc.getAdherent();
			
	If(loc.jrsRetard>0){// s'assure que le film est bien en retard. Evite calcul avec 0 ou valeurs négative de jrsRetard
		
		if(loc.jrsRetard()<60 && loc.isAuJour(){ //si le retard est moins de 60 jours et le film a été loué à la journée
			montant = tauxJournée*loc.jrsRetard;
		}
		else if(loc.jrsRetard<60 && loc.isSemaine()){
			montant = ((1+loc.jrsRetard/7)%1)*tauxSemaine; //calcul montant pour chaque nouvelle semaine en retard.
		}							// le premier jour étant une nouvelle semaine.
		else if(loc.jrsRetard>60){
			loc.film.isPerdu();//pseudo-code: le film loué est déclaré perdu
			montant = loc.film.getCoutAchat()+20; //plus taxes, mais on ajoute ce montant ou?
		}
		else {
			System.out.Println("Le film n'est pas en retard");
		}	   
	}	
	public Adherent getAd() {
		return ad;
	}
	public void setAd(Adherent ad) {
		this.ad = ad;
	}
	public Location getLoc() {
		return loc;
	}
	public void setLoc(Location loc) {
		this.loc = loc;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	
}
