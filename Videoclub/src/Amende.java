import java.util.Date;


public class Amende extends Operation{
	private Adherent ad;
	private Location loc;
	private long jrsRetard;
	private float prixSemaine; 
	private float prixJournee;
	private int indexLA;
	private String codeBarre;	
	
	public Amende(Location loc,int indexLA) {
		this.dateHeure = new Date();
		float montantAmende = 0;
		this.indexLA = indexLA;
		this.loc = loc;
		this.ad = loc.getAdherent();
		LigneArticle la = loc.ligneArticle.get(indexLA);
		prixJournee = la.getDescriptionArticle().getPrixJournalier();
		prixSemaine = la.getDescriptionArticle().getPrixJournalier()*7;
		this.codeBarre = this.loc.getListeLigneArticles().get(indexLA).getCodeBarreArticle();
		
		if(la.getDateRetour() == null) {
			System.out.println(la.getDateDue());
			this.jrsRetard = calculJourEntreDate(this.dateHeure,la.getDateDue());
			
		}
		//calcule le nbr de jours en retard entre la date due et la date de retour de la location
		else {
			this.jrsRetard = calculJourEntreDate(la.getDateDue(), la.getDateRetour());
		}
		 
		//System.out.println(jrsRetard);
		/*
		 * S'assure que le film est bien en retard. 
		 * Evite calcul avec 0 ou valeurs négative de jrsRetard
		 */
		if(jrsRetard > 0) { 
			DescriptionArticle desc = la.getDescriptionArticle();
			//Si le film est nouveau forcement louer à la journee
			if(desc.getEstNouveau() == true) {
				//Film perdu
				if(jrsRetard > 10) {
					montantAmende = montantAmende + desc.getPrixVente()+20/*+taxes*/;
					la.getArticle().setPerdu(true);
				}
				else {
					montantAmende =  montantAmende + prixJournee*jrsRetard;
				}
			}
			
			//Si le film est régulier
			if(desc.getEstNouveau() == false) {
				System.out.println("non nouveau");
				long duree = calculJourEntreDate(la.getDateDue(),loc.getDateHeure());
				System.out.println("Duree " + duree);
				if(jrsRetard > 60) {
					montantAmende =  montantAmende + desc.getPrixVente()+20/*+taxes*/;
					la.getArticle().setPerdu(true);
				}
				else {
					//Location journaliere
					if(duree == 1) {
						
						long jourSupplementaire = jrsRetard/duree;
						montantAmende =  montantAmende + prixJournee*jourSupplementaire;
					}
					//Location hebdomadaire
					if (duree == 7) {
						long jourSupplementaire = jrsRetard%duree;
						long semaineSupplementaire = 0;
						
						if(jourSupplementaire == 0) {
							semaineSupplementaire = jrsRetard/duree;
							montantAmende =  montantAmende + prixSemaine*semaineSupplementaire;
						}
						else {
							montantAmende =  montantAmende + prixSemaine*semaineSupplementaire+1;
						}
					}
				}

			}
			
		}
		
		this.montant = montantAmende;
		//System.out.println("Montant amende = "+this.montant);
	}

	
	
	public Adherent getAd() {
		return this.ad;
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
	public int getIndexLA() {
		return this.indexLA;
	}
	public String getCodeBarre() {
		return this.codeBarre;
	}

	private long calculJourEntreDate (Date d1, Date d2) {
		long diff = Math.abs(d1.getTime()-d2.getTime());
		long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
		return (long)diff/MILLISECONDS_PER_DAY;
		 
	}


	
	public String toString() {
		return "AMENDE \n "+
				"Location "+loc.getIdLoc()+"\n"+
				ad+"\n"+
				"Jour de retard : "+ jrsRetard +
				"\nMontant : "+ this.montant+"\n";
	}

	
}

