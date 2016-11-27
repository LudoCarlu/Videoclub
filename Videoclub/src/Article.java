
public abstract class Article {
	
	private DescriptionArticle description;
	private int codeBarre;
	private int codeArticle;

	public int getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(int codeArticle) {
		this.codeArticle = codeArticle;
	}

	public DescriptionArticle getDescription() {
		return description;
	}

	public void setDescription(DescriptionArticle description) {
		this.description = description;
	}

	
}
