
public class Vente extends Operation{
	
	public Vente(){
		
	}
	
	public void ajouterLigneArticles(Article a, int qte) {
		LigneArticle lar = new LigneArticle(a,qte);

		this.ligneArticle.add(lar);
	}
	public void majMontant(){
		this.montant=0;
		for (int i=0;i<ligneArticle.size();i++){
		this.montant += ligneArticle.get(i).getPrixVente()*ligneArticle.get(i).getQuantite();
		}
		
	}
}
