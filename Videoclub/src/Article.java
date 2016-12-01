
public class Article {
	
	private String codeBarre;
	private String codeDescription;
	private boolean perdu = false;
	private boolean loue = false;
	
	private DescriptionArticle desc;
	
	public Article() {
		
	}
	
	//Constructeur
	public Article(String codeBarre, String codeDescription,boolean loue,boolean perdu) {
		this.codeBarre = codeBarre;
		this.codeDescription = codeDescription;
		this.loue = loue;
		this.perdu = perdu;
	}
	
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
	
	public void ajouterDescription (DescriptionArticle desc) {
		this.desc = desc;
	}

	public String toString() {
		return "codeBarre = "+ codeBarre +
				"\ncodeDesc = "+ codeDescription +
				"\n estPerdu = "+ perdu+
				"\n estLoue = "+ loue;
				
	}
	public void setDescription(DescriptionArticle desc) {
		this.desc = desc;
	}
	public DescriptionArticle getDescription() {
		return this.desc;
	}
	
}
