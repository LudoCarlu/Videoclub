
public class Article {
	
	
	private String codeBarre; // un code barre unique à chacun des articles
	private String codeDescription; // le code de la description de cette article
	private boolean perdu = false; // l'article est-il perdu 
	private boolean loue = false; // l'article est-il loué
	private DescriptionArticle desc; // La description complète de l'article
	
	//Constructeur utilisé dans acquisition
	public Article() {
		
	}
	
	//Constructeur
	public Article(String codeBarre, String codeDescription,boolean loue,boolean perdu) {
		this.codeBarre = codeBarre;
		this.codeDescription = codeDescription;
		this.loue = loue;
		this.perdu = perdu;
		
	}
	
	//Getteurs / Setteurs 
	
	public String getCodeBarre() {
		return this.codeBarre;
	}
	public String getCodeDescription() {
		return this.codeDescription;
	}
	public boolean isPerdu() {
		return perdu;
	}
	public void setPerdu(boolean p) {
		this.perdu = p;
	}
	public boolean isLoue() {
		return loue;
	}
	public void setLoue(boolean l) {
		this.loue = l;
	}
	public DescriptionArticle getDescription() {
		return this.desc;
	}
	public void setDescription(DescriptionArticle desc) {
		this.desc = desc;
	}
	


	public String toString() {
		return "codeBarre = "+ codeBarre +
				"\ncodeDesc = "+ codeDescription +
				"\n estPerdu = "+ perdu+
				"\n estLoue = "+ loue +
				"\n desc: "+ desc.getTitre();
				
	}


	
}
