
public class GestionMain {

	public static void main(String[] args) {
		Videoclub videoclub = new Videoclub("Video");
		Controler controler= videoclub.getControler();
		JFrameGestionnaire frame=new JFrameGestionnaire(controler);
		
	}

}
