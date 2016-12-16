//Classe Main du programme c'est la qu'on lance le code

public class GestionMain {

	public static void main(String[] args) {

		Videoclub videoclub = Videoclub.instanceVideoclub();
		Controler controler = videoclub.getControler();
		JFrameGestionnaire frame=new JFrameGestionnaire(controler);
		

	}

}
