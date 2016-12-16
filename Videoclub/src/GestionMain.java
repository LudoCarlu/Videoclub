import java.util.Scanner;

public class GestionMain {

	public static void main(String[] args) {

		Videoclub videoclub = Videoclub.instanceVideoclub();
		Controler controler = videoclub.getControler();
		JFrameGestionnaire frame=new JFrameGestionnaire(controler);
		
		//System.out.println(controler.instanceInventaire().getArticle("126").toString());
		/*test acquisition
		*/
		//controler.acquerirFilm("9873", "Logan", "Action", "Years after the epilogue of X-Men: Days of Future Past, some of the last mutants, an aging Logan—whose healing factor is starting to fail—and Charles Xavier, protect a young girl named Laura who is very similar"+
	//	" to Logan, and is being hunted by sinister forces.[23]", (float)4.0, (float)10.0, (float)20.0, true, 2);
	//	controler.acquerirAutre("pop1", "Popcorn", (float) 2.0, 5);
		

	}

}
