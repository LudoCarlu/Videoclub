
public class Adherent{
	
	private String numeroTel;
	private String codeSecret;
	private String nom;
	private String prenom;
	private String adresse;
	private String numeroCB;
	
	//Constructeur 
	public Adherent(String numeroTel, String codeSecret, String nom, String prenom, String adresse, String numeroCB) {
		super();
		this.numeroTel = numeroTel;
		this.codeSecret = codeSecret;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numeroCB = numeroCB;
	}

	//Getteurs / Setteurs
	public String getNumeroTel() {
		return numeroTel;
	}
	public String getCodeSecret() {
		return codeSecret;
	}
	public String getAdresse() {
		return adresse;
	}
	public String getNumeroCB() {
		return numeroCB;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setNumeroTel(String n) {
		this.numeroTel = n;
	}
	
	
	@Override
	public String toString() {
		return "Adherent [numeroTel=" + numeroTel + ", codeSecret=" + codeSecret + ", nom=" + nom + ", prenom=" + prenom
				+ ", adresse=" + adresse + ", numeroCB=" + numeroCB + "]";
	}
	
	//Méthode qui permet de payer une amende et qui insère modifie l'amende dans la base de données
	//Pour signaler qu'elle est terminée et payée
	
	public void payerAmende(Location l,String codeBarre) {

		Database d = Videoclub.instanceVideoclub().getDB();
		int taille =  l.getAmende().size();
		for(int i = 0; i < taille; i++) {
			Amende am = l.getAmende().get(i);
			if(am.getCodeBarre().equals(codeBarre) == true) {
				am.setEstTerminee(true);
				am.setPaiement(new Paiement(am.getMontant()));
				d.udpateAmende(am);
			}
		}
	}
	
	
}
