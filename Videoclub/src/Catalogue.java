import java.util.ArrayList;
import java.util.Hashtable;

public class Catalogue {
	private ArrayList<DescriptionArticle> listeDescription;
	private Hashtable<String,DescriptionArticle> list;
	
	
	public Catalogue(ArrayList<DescriptionArticle> liste) {
		this.listeDescription = liste;
	}
	public Catalogue(Hashtable<String,DescriptionArticle> liste) {
		this.list = liste;
	}
	
	
	public ArrayList<DescriptionArticle> getListeDescription() {
		return listeDescription;
	}
	public void setListeDescription(ArrayList<DescriptionArticle> listeDescription) {
		this.listeDescription = listeDescription;
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
		
		if (!listeDescription.isEmpty()){
			listeDescription.add(description);
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
