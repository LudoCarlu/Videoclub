import java.util.ArrayList;
import java.util.Hashtable;

public class Catalogue {
	private Hashtable<String,DescriptionArticle> list;
	

	public Catalogue(Hashtable<String,DescriptionArticle> liste) {
		this.list = liste;
	}
	
	public Hashtable<String,DescriptionArticle> getList(){
		return this.list;
	}
	

	public DescriptionArticle getDesc (String codeArticle) {
		//System.out.println(list.toString());
		return list.get(codeArticle);
		
		
		/*for(int i = 0; i < this.listeDescription.size() ;i++) {
			//System.out.println(listeDescription.get(i));
			if(listeDescription.get(i).getCodeArticle() == codeArticle) {
				System.out.println("ON TROUVE");
				return listeDescription.get(i);
			}
			
		}
		return null;*/
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
