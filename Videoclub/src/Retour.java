import java.util.Date;

public class Retour {

	private LigneArticle lar;
	
	public Retour(LigneArticle l){
		this.lar = l;
		this.lar.getArticle().setLoue(false);
		this.lar.setDateRetour(new Date());
	}
	
	
	public LigneArticle getLigneArticles() {
		return this.lar;
	}
	
	public boolean isEnRetard() {
		
		if(this.lar.getDateRetour().compareTo(this.lar.getDateDue()) >= 1) {
			return true;
		}
		
		return false;
	}
}
