import java.util.Hashtable;

/*
 * Le catalogue contient la liste de toutes les descriptions d'articles
 */
public class Catalogue {
	private Hashtable<String,DescriptionArticle> list;


	public Catalogue(Hashtable<String,DescriptionArticle> liste) {
		this.list = liste;
	}

	public Hashtable<String,DescriptionArticle> getList(){
		return this.list;
	}


	public DescriptionArticle getDesc (String codeArticle) {
		return list.get(codeArticle);
	}

	public void ajouterDesc(DescriptionArticle description) {

		try {

			if (!list.isEmpty()){
				list.put(description.getCodeArticle(),description);
			}
		}
		catch (NullPointerException e) {	
		}

		try {
			if (!list.isEmpty()){
				list.put(description.getCodeArticle(), description);
			}
		}
		catch (NullPointerException e) {	
		}

	}
}
