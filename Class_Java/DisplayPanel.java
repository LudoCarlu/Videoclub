import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DisplayPanel extends JPanel implements ActionListener{
	private JTextField textField;
	private JTextField textField_1;
	private JButton valider;
	JFrameGestionnaire fenetre;

	public DisplayPanel(int choix,JFrameGestionnaire F){
		setBackground(Color.WHITE);
		this.fenetre=F;
		
		if (choix==0){
			JLabel titre = new JLabel("Authentifcation");
			add(titre);
			
			textField = new JTextField();
			add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			add(textField_1);
			textField_1.setColumns(10);
			this.valider= new JButton("Valider");
			add(this.valider);
			this.valider.addActionListener(this);
			
		}
		if(choix==1){
			JLabel titre = new JLabel("Location");
			add(titre);
		}
		if (choix==2){
			JLabel titre = new JLabel("Vente");
			add(titre);
			
		}
		if (choix==3){
			JLabel titre = new JLabel("Inscription");
			add(titre);
			
		}
		if (choix==4){
			JLabel titre = new JLabel("Retours");
			add(titre);
			
		}
		if (choix==5){
			JLabel titre = new JLabel("Gerer les retards");
			add(titre);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== this.valider){
			this.fenetre.setControlPanel();
			this.fenetre.setDisplayPanel(new DisplayPanel(1,this.fenetre));
		}
		
	}

}
