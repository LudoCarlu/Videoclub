
public class Employe {
	private int idEmploye;
	private String nom;
	private boolean gerant;
	private String mdp;
	
	public Employe(int idEmploye, String nom, boolean gerant, String mdp) {
		this.idEmploye = idEmploye;
		this.nom = nom;
		this.gerant = gerant;
		this.mdp = mdp;
	}

	public int getIdEmploye() {
		return idEmploye;
	}

	public String getNom() {
		return nom;
	}

	public boolean isGerant() {
		return gerant;
	}

	public String getMdp() {
		return mdp;
	}
	public String presentation(){
		String message=this.getNom();
		if (this.gerant==true){
			message=message.concat("\n (gérant)");
		}
		return message;
	}
	@Override
	public String toString() {
		return "Employe [idEmploye=" + idEmploye + ", nom=" + nom + ", gerant=" + gerant + ", mdp=" + mdp + "]";
	}
	
}
