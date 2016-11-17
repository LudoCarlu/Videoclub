
public class Videoclub {
	private String nom;
	private String adresse;
	private Controler controler;
	private Database D;
	
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
		this.D=new Database("test");
		this.controler=new Controler(D.genererAdherent());
	}
}
