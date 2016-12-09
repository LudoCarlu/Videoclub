import java.util.Scanner;

public class GestionMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Videoclub videoclub = Videoclub.instanceVideoclub();
		Controler controler = videoclub.getControler();
		JFrameGestionnaire frame=new JFrameGestionnaire(controler);

		//test acquisition
		
		controler.aquerirFilm("9873", "Logan", "Action", "Years after the epilogue of X-Men: Days of Future Past, some of the last mutants, an aging Logan—whose healing factor is starting to fail—and Charles Xavier, protect a young girl named Laura who is very similar"+
		" to Logan, and is being hunted by sinister forces.[23]", (float)4.0, (float)10.0, (float)20.0, true, 2);
		controler.acquerirAutre("pop1", "Popcorn", (float) 2.0, 5);
		
		//
		
		
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
