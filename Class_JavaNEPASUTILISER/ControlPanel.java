import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements ActionListener{
	private JFrameGestionnaire gestionnaire;
	private JButton retard=null;
	private JButton location=null;
	private JButton vente=null;
	private JButton inscription=null;
	private JButton retours=null;
	private JButton acquisition=null;
	private int choix=0;
	
	public ControlPanel(JFrameGestionnaire fenetre){
		this.gestionnaire=fenetre;
		this.location = new JButton("Location");
		add(this.location);

		this.vente = new JButton("Vente");
		add(this.vente);
		
		this.inscription = new JButton("Inscription");
		add(this.inscription);

		this.retours = new JButton("Retours");
		add(this.retours);
		
		this.retard= new JButton("Retards");
		add(this.retard);
		this.acquisition = new JButton("Acquisition");
		add(this.acquisition);
		
		this.retard.addActionListener(this);
		this.location.addActionListener(this);
		this.vente.addActionListener(this);
		this.retours.addActionListener(this);
		this.inscription.addActionListener(this);
		this.acquisition.addActionListener(this);
		
		
	}
	
	public int getChoix(){
		return this.choix;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.location){
			this.gestionnaire.setDisplayPanel(new DisplayPanel(7,this.gestionnaire));
			this.gestionnaire.getController().creerLocation();
		}
		if (e.getSource()==this.vente){
			this.gestionnaire.setDisplayPanel(new DisplayPanel(2,this.gestionnaire));
		}
		if (e.getSource()==this.inscription){
			this.gestionnaire.setDisplayPanel(new DisplayPanel(3,this.gestionnaire));
		}
		if (e.getSource()==this.retours){
			this.gestionnaire.setDisplayPanel(new DisplayPanel(4,this.gestionnaire));
		}
		if (e.getSource()==this.retard){
			this.gestionnaire.setDisplayPanel(new DisplayPanel(5,this.gestionnaire));
		}
		if (e.getSource()==this.acquisition){
			this.gestionnaire.setDisplayPanel(new DisplayPanel(6,this.gestionnaire));
		}
		
	}
}
