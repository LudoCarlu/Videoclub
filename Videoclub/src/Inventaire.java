import java.util.Hashtable;

public class Inventaire {

	private Hashtable<String,Article> inv;
	
	public Inventaire(Hashtable<String,Article> inventaire){
		this.inv = inventaire;
	}
	public Article getArticle(String codeBarre){
		return inv.get(codeBarre);
	}
	public DescriptionArticle getDescriptionArticle(String codeBarre){
		return inv.get(codeBarre).getDescription();
	}
	public void retirerArticle(String codeBarre){
		inv.remove(codeBarre);
	}
	public void ajouterArticle(Article article){
		inv.put(article.getCodeBarre(), article);
	}
}
