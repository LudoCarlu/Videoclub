import java.util.Scanner;

public class GestionMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Videoclub videoclub = new Videoclub("Video");
		Controler controler= videoclub.getControler();
		JFrameGestionnaire frame=new JFrameGestionnaire(controler);

		/*test acquisition
		
		controler.acquerirAutre("909", "Skittles", (float) 1.7, 1);
		controler.acquerirAutre("901", "Snikers", (float) 2.0, 1);
		
		Fonctionne!
		*
		*/
		
		//System.out.println("Location");
		//controler.MenuLocation();

		//boolean b = true;
		String c="oui";
		/*while (c=="oui") {
			System.out.print("Saississez le code de l'article : ");
			String ca = sc.next();

			System.out.print("Quantite ? ");
			int qte = sc.nextInt();

			System.out.print("Duree (1 ou 7) ? ");
			int dur = sc.nextInt();
			controler.saisirArticleLocation(ca, qte,dur);
			System.out.println(" ");
			System.out.println("Montant actuel = " + controler.afficherMontant());

			System.out.print("Encore des articles (oui ou non) ? ");
			c = sc.next();
			System.out.println(c);

			if(c=="non") {
				System.out.println("test");
				//b=false;
				
				break;
			}*/
		//}
		//controler.terminerLocation();

	}

}
