import java.util.*;

public class Inscription {

	private String nom;
	private String prenom;
	private String num;
	private String emprunteCB;
	private String adresse;
	private String mdp;
	
	public Inscription(String nom,String prenom,String num,String cb, String adresse, String mdp) {
		this.nom = nom;
		this.prenom = prenom;
		this.num = num;
		this.emprunteCB = cb;
		this.adresse = adresse;
		this.mdp = mdp;
	}
	
	public Adherent ajouterAdherent() {
		
		Adherent ad = new Adherent(this.num,this.mdp,this.nom,this.prenom,this.adresse,this.emprunteCB);
		Database d = Videoclub.instanceVideoclub().getDB();
		d.insertAdherent(ad);
		return ad;
	}
}
