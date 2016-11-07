import java.util.ArrayList;

public class Adherent extends Client{
	
	private int numeroTel;
	private int codeSecret;
	private  String adresse;
	private int numeroCB;
	private String nom;
	private String prenom;
	private ArrayList<Operation> listeOperation;
	
	public ArrayList<Operation> getListeOperation() {
		return listeOperation;
	}
	public void setListeOperation(ArrayList<Operation> listeOperation) {
		this.listeOperation = listeOperation;
	}
	public int getNumeroTel() {
		return numeroTel;
	}
	public void setNumeroTel(int numeroTel) {
		this.numeroTel = numeroTel;
	}
	public int getCodeSecret() {
		return codeSecret;
	}
	public void setCodeSecret(int codeSecret) {
		this.codeSecret = codeSecret;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getNumeroCB() {
		return numeroCB;
	}
	public void setNumeroCB(int numeroCB) {
		this.numeroCB = numeroCB;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
}
