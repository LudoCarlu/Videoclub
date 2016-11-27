
public class Article {
	
	private DescriptionArticle description;
	//private int codeBarre;
	//private String codeArticle;

	public String getCodeArticle() {
		return description.getCodeArticle();
	}
/*
	public void setCodeArticle(int codeArticle) {
		this.codeArticle = codeArticle;
	}
*/
	public DescriptionArticle getDescription() {
		return description;
	}

	public void setDescription(DescriptionArticle description) {
		this.description = description;
	}

	
}
