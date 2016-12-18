import java.util.Hashtable;
import java.util.Set;

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
		
		Database d = Videoclub.instanceVideoclub().getDB();
		d.removeArticle(inv.get(codeBarre));
		
		inv.remove(codeBarre);
	}
	
	public void retirerArticle(Article art){
		
		Database d = Videoclub.instanceVideoclub().getDB();
		d.removeArticle(art);
		
		inv.remove(art.getCodeBarre());
	}
	public void ajouterArticle(Article article){
		inv.put(article.getCodeBarre(), article);
	}
	public int count(){
		return inv.size();
	}
	public Hashtable<String,Article> getInventaire() {
		return this.inv;
	}
	
	public void reconcile(Catalogue c){
		Set<String> keys = inv.keySet();
        for(String key: keys){
        	DescriptionArticle desc = c.getDesc(inv.get(key).getCodeDescription());
        	inv.get(key).setDescription(desc);
        
		}
	}
	
}
