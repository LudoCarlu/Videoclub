
public class Videoclub {
	private String nom;
	private String adresse;
	private Controler controler;
	
	public String getNom() {
		return nom;
	}
	public String getAdresse() {
		return adresse;
	}
	
	public Controler getControler() {
		return controler;
	}

	public Videoclub(String nom){
		this.nom=nom;
		this.controler=new Controler();
	}
}
