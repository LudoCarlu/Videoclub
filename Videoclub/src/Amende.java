import java.util.Date;

//Une amende est une opération c'est à dire qu'elle a une date et un montant
public class Amende extends Operation{
	
	/*
	 * Une amende connait son adhérent, la location à laquelle
	 * elle appartient
	 * Elle a le nombre de jours de retards
	 * Elle connait le prix à la semaine et à la journée du film sur lequel elle porte
	 */

	private Adherent ad; 
	private Location loc; 
	private long jrsRetard;
	private float prixSemaine; 
	private float prixJournee;
	private int indexLA;
	private String codeBarre;	
	
	
	/* Constructeur amende */
	/**
	* @param Location : 
	* Elle contient la ligne d'articles contenant le film sur lequel il y a un retard donc une amende
	* @param indexLigneArticles :
	* Index de la ligne d'articles où se trouvent le retard dans la location passée en paramètres
	* 
	* Le constructeur met à jour le montant de l'amende
	*/
	
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
		
		/* Le film n'a pas encore été retourné mais il est en retard
		 * On calcule le nombre de jour de retard entre la date due et aujourd'hui
		 */
		if(la.getDateRetour() == null) {
			this.jrsRetard = calculJourEntreDate(this.dateHeure,la.getDateDue());
			
		}
		// Sinon on calcule le nombre de jour de retard entre la date due et la date de retour
		else {
			this.jrsRetard = calculJourEntreDate(la.getDateDue(), la.getDateRetour());
		}
		 
		/*
		 * S'assure que le film est bien en retard. 
		 * Evite calcul avec 0 ou valeurs négative de jrsRetard
		 */
		if(jrsRetard > 0) { 
			DescriptionArticle desc = la.getDescriptionArticle();
			//Code écrit mais la nouveauté n'est pas implémenté dans le code
			//Si le film est nouveau il est forcement louer à la journée
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
				//Calcul la durée de location qui sera de 1 ou 7 jours
				long duree = calculJourEntreDate(la.getDateDue(),loc.getDateHeure());
				
				//Le film est perdu
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
	}

	
	// Getteurs / setteurs
	
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

	//Calcul le nombre de jour entre 2 dates
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

