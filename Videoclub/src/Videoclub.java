
public class Videoclub {
	private String nom;
	private String adresse;
	private Controler controler;
	private Database D;
	public static Videoclub instanceVideoclub = null;
	
	public String getNom() {
		return nom;
	}
	public String getAdresse() {
		return adresse;
	}
	
	public Controler getControler() {
		return controler;
	}
	public Database getDB() {
		return Database.instanceDB();
	}

	private Videoclub(String nom){
		this.nom=nom;
		this.D = Database.instanceDB();
		this.controler=new Controler(D.genererAdherent(),D.genererDescriptionArticle());
	}
	
	public static Videoclub instanceVideoclub() {
		if(instanceVideoclub == null) {
			instanceVideoclub = new Videoclub("Video");
		}
		return instanceVideoclub;
	}
}
